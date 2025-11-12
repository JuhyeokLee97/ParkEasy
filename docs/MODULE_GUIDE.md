# ParkEasy 모듈 가이드

## 📦 개요

ParkEasy는 멀티 모듈 아키텍처를 채택하여 각 기능을 독립적인 모듈로 분리했습니다. 이 문서는 각 모듈의 역할, 구조, 그리고 사용법을 상세히 설명합니다.

## 🏗 모듈 구조

```
ParkEasy/
├── app/                    # 메인 애플리케이션 모듈
├── core/
│   ├── domain/            # 비즈니스 로직 및 모델
│   ├── data/              # 데이터 레이어
│   └── ui/                # 공통 UI 컴포넌트
└── feature/
    ├── home/              # 홈 화면 기능
    ├── around/            # 주변 주차장 기능
    └── mypage/            # 마이페이지 기능
```

## 📱 App 모듈

### 역할
- 애플리케이션의 진입점
- 전체 네비게이션 설정
- Hilt 애플리케이션 클래스
- 모든 feature 모듈 통합

### 주요 파일

#### `ParkEasyApp.kt`
```kotlin
@HiltAndroidApp
class ParkEasyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        // 앱 초기화 로직
    }
}
```

#### `navigation/NavHost.kt`
```kotlin
@Composable
fun ParkEasyNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = "home",
        modifier = modifier
    ) {
        // Feature 모듈의 네비게이션 그래프
        homeScreen(
            onNavigateToAround = { navController.navigate("around") }
        )
        aroundScreen(
            onNavigateBack = { navController.popBackStack() }
        )
        myPageScreen()
    }
}
```

#### `navigation/Destinations.kt`
```kotlin
object Destinations {
    const val HOME = "home"
    const val AROUND = "around"
    const val MY_PAGE = "myPage"
    const val PARKING_DETAIL = "parking/{parkingId}"
}
```

### 의존성
```kotlin
dependencies {
    // Core modules
    implementation(project(":core:domain"))
    implementation(project(":core:data"))
    implementation(project(":core:ui"))

    // Feature modules
    implementation(project(":feature:home"))
    implementation(project(":feature:around"))
    implementation(project(":feature:mypage"))

    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
}
```

### 테마 및 스타일

#### `ui/theme/Theme.kt`
전체 앱의 Material3 테마 정의

```kotlin
@Composable
fun ParkEasyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        darkColorScheme(...)
    } else {
        lightColorScheme(...)
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
```

---

## 🧱 Core 모듈

Core 모듈은 앱 전체에서 공유되는 핵심 기능을 제공합니다.

### core:domain

**역할**: 비즈니스 로직과 도메인 모델 정의

**특징**:
- 다른 모듈에 의존하지 않음
- 순수 Kotlin 코드 (Android 의존성 없음)
- 인터페이스 정의

#### 디렉토리 구조
```
core/domain/
├── model/
│   ├── ParkingLot.kt
│   ├── Location.kt
│   └── Result.kt
├── repository/
│   ├── ParkingLotRepository.kt
│   ├── LocationRepository.kt
│   └── AuthRepository.kt
└── usecase/
    ├── GetParkingLotsNearbyUseCase.kt
    └── GetCurrentLocationUseCase.kt
```

#### 주요 클래스

##### Model
```kotlin
// model/ParkingLot.kt
data class ParkingLot(
    val id: String,
    val name: String,
    val pricePerHour: Int,
    val address: String,
    val availableStartTime: String,
    val availableEndTime: String,
    val availablePlace: Int,
    val imageUrl: String,
    val latitude: Double,
    val longitude: Double
)

// model/Result.kt
sealed interface Result<out T> {
    data class Success<T>(val data: T) : Result<T>
    data class Error(val message: String) : Result<Nothing>
    object Loading : Result<Nothing>
}
```

##### Repository Interface
```kotlin
// repository/ParkingLotRepository.kt
interface ParkingLotRepository {
    suspend fun getParkingLotsNearby(
        latitude: Double,
        longitude: Double,
        radiusInKm: Double
    ): Result<List<ParkingLot>>

    suspend fun getParkingLotById(id: String): Result<ParkingLot>
}
```

##### Use Case
```kotlin
// usecase/GetParkingLotsNearbyUseCase.kt
class GetParkingLotsNearbyUseCase @Inject constructor(
    private val parkingLotRepository: ParkingLotRepository
) {
    suspend operator fun invoke(
        latitude: Double,
        longitude: Double,
        radiusInKm: Double = 5.0
    ): Result<List<ParkingLot>> {
        return parkingLotRepository.getParkingLotsNearby(
            latitude,
            longitude,
            radiusInKm
        )
    }
}
```

#### 의존성
```kotlin
dependencies {
    // Kotlin
    implementation(libs.kotlin.stdlib)

    // Coroutines
    implementation(libs.kotlinx.coroutines.core)

    // Hilt
    implementation(libs.hilt.core)
}
```

---

### core:data

**역할**: 데이터 소스 관리 및 Repository 구현

**특징**:
- `core:domain`의 Repository 인터페이스 구현
- 데이터 소스 통합 (Remote, Local)
- 의존성 주입 모듈

#### 디렉토리 구조
```
core/data/
├── repository/
│   ├── ParkingLotRepositoryImpl.kt
│   ├── LocationRepositoryImpl.kt
│   └── AuthRepositoryImpl.kt
├── model/
│   ├── ParkingLotDto.kt
│   └── Mapper.kt
└── di/
    ├── DataModule.kt
    └── FirebaseModule.kt
```

#### 주요 클래스

##### Repository Implementation
```kotlin
// repository/ParkingLotRepositoryImpl.kt
class ParkingLotRepositoryImpl @Inject constructor(
    private val firebaseDataSource: FirebaseDataSource
) : ParkingLotRepository {

    override suspend fun getParkingLotsNearby(
        latitude: Double,
        longitude: Double,
        radiusInKm: Double
    ): Result<List<ParkingLot>> = withContext(Dispatchers.IO) {
        try {
            val parkingLots = firebaseDataSource.fetchParkingLots()
                .filter { parkingLot ->
                    calculateDistance(
                        latitude, longitude,
                        parkingLot.latitude, parkingLot.longitude
                    ) <= radiusInKm
                }
                .map { it.toDomain() }

            Result.Success(parkingLots)
        } catch (e: Exception) {
            Result.Error(e.message ?: "Unknown error")
        }
    }
}
```

##### Dependency Injection
```kotlin
// di/DataModule.kt
@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideParkingLotRepository(
        firebaseDataSource: FirebaseDataSource
    ): ParkingLotRepository {
        return ParkingLotRepositoryImpl(firebaseDataSource)
    }

    @Provides
    fun provideGetParkingLotsNearbyUseCase(
        repository: ParkingLotRepository
    ): GetParkingLotsNearbyUseCase {
        return GetParkingLotsNearbyUseCase(repository)
    }
}

// di/FirebaseModule.kt
@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = Firebase.auth
}
```

#### 의존성
```kotlin
dependencies {
    // Core modules
    implementation(project(":core:domain"))

    // Firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth)

    // Coroutines
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.coroutines.play.services)

    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
}
```

---

### core:ui

**역할**: 재사용 가능한 UI 컴포넌트 라이브러리

**특징**:
- 앱 전체에서 사용되는 공통 컴포넌트
- 디자인 시스템 일관성 유지
- Compose 기반

#### 디렉토리 구조
```
core/ui/
└── components/
    ├── Button.kt
    ├── Dialog.kt
    ├── AppBar.kt
    ├── Icon.kt
    └── LoadingIndicator.kt
```

#### 주요 컴포넌트

##### BaseDialog
```kotlin
// components/Dialog.kt
@Composable
fun BaseDialog(
    onDismissRequest: () -> Unit,
    title: String,
    content: String,
    confirmText: String = "확인",
    onConfirm: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = { Text(title) },
        text = { Text(content) },
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text(confirmText)
            }
        },
        modifier = modifier
    )
}
```

##### Custom AppBar
```kotlin
// components/AppBar.kt
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ParkEasyAppBar(
    title: String,
    onNavigationClick: (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {},
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(title) },
        navigationIcon = {
            onNavigationClick?.let {
                IconButton(onClick = it) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "뒤로가기"
                    )
                }
            }
        },
        actions = actions,
        modifier = modifier
    )
}
```

#### 의존성
```kotlin
dependencies {
    // Compose
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.ui)
    implementation(libs.compose.material3)
    implementation(libs.compose.ui.tooling.preview)

    // Icons
    implementation(libs.compose.material.icons.extended)
}
```

---

## 🎯 Feature 모듈

Feature 모듈은 각 화면의 독립적인 기능을 담당합니다.

### feature:home

**역할**: 홈 화면 - 지도 및 주변 주차장 표시

#### 디렉토리 구조
```
feature/home/
├── HomeScreen.kt
├── HomeViewModel.kt
├── HomeUiState.kt
└── component/
    ├── ParkingMapView.kt
    └── QuickActionButton.kt
```

#### HomeScreen.kt
```kotlin
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onNavigateToAround: () -> Unit,
    onNavigateToFavorites: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val currentLocation by viewModel.currentLocation.collectAsState()

    Scaffold(
        topBar = {
            ParkEasyAppBar(title = "ParkEasy")
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Google Map
            ParkingMapView(
                currentLocation = currentLocation,
                parkingLots = (uiState as? HomeUiState.Success)?.parkingLots,
                modifier = Modifier.weight(1f)
            )

            // Quick Actions
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                QuickActionButton(
                    text = "주변 주차장 찾기",
                    onClick = onNavigateToAround,
                    modifier = Modifier.weight(1f)
                )
                QuickActionButton(
                    text = "즐겨찾기",
                    onClick = onNavigateToFavorites,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }

    // Service Preparing Dialog
    if (viewModel.showServicePreparingDialog) {
        ServicePreparingDialog(
            onDismiss = { viewModel.hideServicePreparingDialog() }
        )
    }
}
```

#### HomeViewModel.kt
```kotlin
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getParkingLotsNearbyUseCase: GetParkingLotsNearbyUseCase,
    private val locationRepository: LocationRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private val _currentLocation = MutableStateFlow<Location?>(null)
    val currentLocation: StateFlow<Location?> = _currentLocation.asStateFlow()

    var showServicePreparingDialog by mutableStateOf(false)
        private set

    init {
        loadCurrentLocation()
        loadParkingLots()
    }

    private fun loadCurrentLocation() {
        viewModelScope.launch {
            locationRepository.getCurrentLocation().collect { location ->
                _currentLocation.value = location
            }
        }
    }

    fun loadParkingLots() {
        viewModelScope.launch {
            _uiState.value = HomeUiState.Loading

            val location = _currentLocation.value ?: return@launch

            when (val result = getParkingLotsNearbyUseCase(
                latitude = location.latitude,
                longitude = location.longitude
            )) {
                is Result.Success -> {
                    _uiState.value = HomeUiState.Success(result.data)
                }
                is Result.Error -> {
                    _uiState.value = HomeUiState.Error(result.message)
                }
            }
        }
    }

    fun hideServicePreparingDialog() {
        showServicePreparingDialog = false
    }
}
```

#### 의존성
```kotlin
dependencies {
    // Core modules
    implementation(project(":core:domain"))
    implementation(project(":core:ui"))

    // Compose & Navigation
    implementation(libs.compose.navigation)
    implementation(libs.hilt.navigation.compose)

    // Google Maps
    implementation(libs.maps.compose)
    implementation(libs.play.services.location)

    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
}
```

---

### feature:around

**역할**: 주변 주차장 리스트 화면

#### 주요 파일
- `AroundScreen.kt`: 주변 주차장 리스트 UI
- `AroundViewModel.kt`: 5km 반경 주차장 로드 및 상태 관리
- `component/ParkingLotListItem.kt`: 주차장 아이템 컴포넌트

#### 특징
- LazyColumn으로 효율적인 리스트 렌더링
- 주차장 상세 정보 표시 (요금, 운영시간, 주소)
- 상세 화면으로 네비게이션

---

### feature:mypage

**역할**: 사용자 프로필 및 설정 화면

#### 주요 기능
- 사용자 프로필 표시
- 차량 등록 버튼
- 결제수단 등록 버튼
- 예약 내역 조회 버튼
- 설정 버튼

---

## 📚 참고 자료

- [Guide to Android app modularization](https://developer.android.com/topic/modularization)
- [Now in Android - Multi-module example](https://github.com/android/nowinandroid)

---

모듈 구조는 프로젝트의 성장에 따라 진화할 수 있습니다. 새로운 요구사항이 생기면 이 가이드도 함께 업데이트됩니다.
