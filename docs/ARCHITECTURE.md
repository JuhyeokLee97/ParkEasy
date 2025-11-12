# ParkEasy 아키텍처 가이드

## 📐 개요

ParkEasy는 **Clean Architecture**와 **MVVM(Model-View-ViewModel)** 패턴을 기반으로 설계하려는 Android 애플리케이션입니다. 
이 문서는 프로젝트의 아키텍처, 설계 원칙, 그리고 각 계층의 역할을 상세히 설명합니다.

## 🏛 아키텍처 원칙

### Clean Architecture

Clean Architecture는 다음과 같은 이점을 제공합니다:

- **독립성**: UI, 데이터베이스, 프레임워크로부터 비즈니스 로직 분리
- **테스트 용이성**: 각 계층을 독립적으로 테스트 가능
- **유지보수성**: 변경사항이 다른 계층에 미치는 영향 최소화
- **확장성**: 새로운 기능 추가가 용이

### 의존성 규칙

```
Presentation → Domain ← Data
```

- **Presentation Layer**는 **Domain Layer**에 의존
- **Data Layer**는 **Domain Layer**에 의존
- **Domain Layer**는 어떤 계층에도 의존하지 않음 (순수한 Kotlin/Java 코드)

## 📦 계층별 상세 설명

### 1. Presentation Layer (표현 계층)

**위치**: `feature/` 모듈들

**역할**: 사용자 인터페이스와 상호작용 처리

#### 구성 요소

##### Composables (UI)
- Jetpack Compose를 사용한 선언형 UI
- 화면별로 분리된 Composable 함수
- UI 상태에 따라 자동으로 재구성

예시:
```kotlin
// feature/home/HomeScreen.kt
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onNavigateToAround: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    when (uiState) {
        is HomeUiState.Loading -> LoadingScreen()
        is HomeUiState.Success -> HomeContent(...)
        is HomeUiState.Error -> ErrorScreen(...)
    }
}
```

##### ViewModels
- UI 상태 관리 및 비즈니스 로직 호출
- StateFlow를 통한 반응형 상태 관리
- Hilt를 통한 의존성 주입

```kotlin
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getParkingLotsNearbyUseCase: GetParkingLotsNearbyUseCase,
    private val locationRepository: LocationRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun loadParkingLots() {
        viewModelScope.launch {
            // Use case 호출
        }
    }
}
```

##### UI State
- Sealed class로 정의된 UI 상태
- Loading, Success, Error 등 명확한 상태 구분

```kotlin
sealed interface HomeUiState {
    object Loading : HomeUiState
    data class Success(val parkingLots: List<ParkingLot>) : HomeUiState
    data class Error(val message: String) : HomeUiState
}
```

### 2. Domain Layer (도메인 계층)

**위치**: `core/domain/`

**역할**: 비즈니스 로직과 규칙 정의

#### 구성 요소

##### Models (도메인 모델)
순수 Kotlin 데이터 클래스로 앱의 핵심 비즈니스 객체 표현

```kotlin
// core/domain/model/ParkingLot.kt
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
```

##### Repository Interfaces
데이터 접근을 위한 인터페이스 정의 (구현은 Data Layer에서)

```kotlin
// core/domain/repository/ParkingLotRepository.kt
interface ParkingLotRepository {
    suspend fun getParkingLotsNearby(
        latitude: Double,
        longitude: Double,
        radiusInKm: Double
    ): Result<List<ParkingLot>>
}
```

##### Use Cases
단일 비즈니스 로직을 캡슐화한 클래스

```kotlin
// core/domain/usecase/GetParkingLotsNearbyUseCase.kt
class GetParkingLotsNearbyUseCase @Inject constructor(
    private val parkingLotRepository: ParkingLotRepository
) {
    suspend operator fun invoke(
        latitude: Double,
        longitude: Double,
        radiusInKm: Double = 5.0
    ): Result<List<ParkingLot>> {
        return parkingLotRepository.getParkingLotsNearby(
            latitude, longitude, radiusInKm
        )
    }
}
```

**Use Case 설계 원칙**:
- 하나의 Use Case는 하나의 비즈니스 로직만 처리
- `operator fun invoke()` 사용으로 함수처럼 호출 가능
- 재사용 가능하고 테스트하기 쉬운 구조

### 3. Data Layer (데이터 계층)

**위치**: `core/data/`

**역할**: 데이터 소스 관리 및 Repository 구현

#### 구성 요소

##### Repository Implementations
Domain Layer의 Repository 인터페이스 구현

```kotlin
// core/data/repository/ParkingLotRepositoryImpl.kt
class ParkingLotRepositoryImpl @Inject constructor(
    private val firebaseDataSource: FirebaseDataSource,
    private val localDataSource: LocalDataSource
) : ParkingLotRepository {

    override suspend fun getParkingLotsNearby(
        latitude: Double,
        longitude: Double,
        radiusInKm: Double
    ): Result<List<ParkingLot>> {
        return try {
            // 데이터 소스에서 데이터 가져오기
            // 도메인 모델로 변환
            Result.Success(parkingLots)
        } catch (e: Exception) {
            Result.Error(e.message ?: "Unknown error")
        }
    }
}
```

##### Data Sources
- **Remote Data Source**: Firebase, REST API 등 원격 데이터
- **Local Data Source**: Room Database, SharedPreferences 등 로컬 데이터

##### Data Models
네트워크/데이터베이스 모델과 도메인 모델 간 변환

```kotlin
// Data Model (DTO)
data class ParkingLotDto(
    val id: String?,
    val name: String?,
    // ...
)

// Mapper
fun ParkingLotDto.toDomain(): ParkingLot {
    return ParkingLot(
        id = id ?: "",
        name = name ?: "",
        // ...
    )
}
```

## 🔧 의존성 주입 (Dependency Injection)

### Hilt 모듈 구조

#### DataModule
Repository 및 Use Case 제공

```kotlin
@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideParkingLotRepository(
        // dependencies
    ): ParkingLotRepository {
        return ParkingLotRepositoryImpl(...)
    }

    @Provides
    fun provideGetParkingLotsNearbyUseCase(
        repository: ParkingLotRepository
    ): GetParkingLotsNearbyUseCase {
        return GetParkingLotsNearbyUseCase(repository)
    }
}
```

#### FirebaseModule
Firebase 관련 인스턴스 제공

```kotlin
@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth {
        return Firebase.auth
    }
}
```

## 🧩 멀티 모듈 아키텍처

### 모듈 분리 전략

#### Core 모듈
- **core:domain**: 비즈니스 로직 (다른 모듈에 의존하지 않음)
- **core:data**: 데이터 처리 (domain에만 의존)
- **core:ui**: 공통 UI 컴포넌트 (domain에 의존 가능)

#### Feature 모듈
- **feature:home**: 홈 화면
- **feature:around**: 주변 주차장 리스트
- **feature:mypage**: 마이페이지

각 feature 모듈은:
- `core:domain`, `core:ui`에 의존
- 다른 feature 모듈에 의존하지 않음
- 독립적으로 개발 및 테스트 가능

### 모듈 간 의존성 그래프

```
       ┌──────────────────┐
       │       app        │
       └─────────┬────────┘
                 │
       ┌─────────┴──────────┐
       │                    │
┌──────▼──────┐      ┌──────▼──────┐
│   feature   │      │    core     │
│   modules   │◄─────┤   modules   │
└─────────────┘      └─────────────┘
  - home                - domain
  - around              - data
  - mypage              - ui
```

## 📚 참고 자료

- [Android Architecture Guide](https://developer.android.com/topic/architecture)
- [Clean Architecture by Robert C. Martin](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)
- [Jetpack Compose Documentation](https://developer.android.com/jetpack/compose)
- [Hilt Documentation](https://developer.android.com/training/dependency-injection/hilt-android)

---

이 아키텍처는 지속적으로 개선되고 있으며, 프로젝트의 요구사항에 따라 진화할 수 있습니다.