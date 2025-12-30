package com.example.presentation.theme.color

import androidx.compose.ui.graphics.Color

/**
 * ParkEasy 앱의 모든 색상 정의
 * 데모 화면을 기준으로 한 실제 사용 색상들
 *
 * 디자인 컨셉:
 * - Primary: 신뢰감을 주는 블루 (#2196F3 기반)
 * - Secondary: 주차 가능 상태를 나타내는 그린 (#4CAF50 기반)
 * - Tertiary: 경고/주의를 나타내는 오렌지 (#FF9800 기반)
 * - Error: 위험/불가능 상태를 나타내는 레드 (#F44336 기반)
 */

// =========================
// 시드 컬러 (브랜드 기본 색상)
// =========================
val ParkEasyBlue = Color(0xFF2196F3)      // 메인 브랜드 컬러 (로그인 버튼, 헤더 등)
val ParkEasyGreen = Color(0xFF4CAF50)     // 주차 가능 상태 (마커, 잔여자리 등)
val ParkEasyOrange = Color(0xFFFF9800)    // 경고 상태 (제한적 주차장 등)
val ParkEasyRed = Color(0xFFF44336)       // 위험/불가능 상태 (만차, 에러 등)

// =========================
// Light Theme Primary Colors
// =========================
val LightPrimary = Color(0xFF1976D2)                    // 진한 블루 (버튼, 액센트)
val LightOnPrimary = Color(0xFFFFFFFF)                  // 흰색 (Primary 위 텍스트)
val LightPrimaryContainer = Color(0xFFE3F2FD)           // 연한 블루 (Primary 컨테이너)
val LightOnPrimaryContainer = Color(0xFF0D47A1)         // 매우 진한 블루 (컨테이너 위 텍스트)

// =========================
// Light Theme Secondary Colors
// =========================
val LightSecondary = Color(0xFF388E3C)                  // 진한 그린 (주차 가능 상태)
val LightOnSecondary = Color(0xFFFFFFFF)                // 흰색 (Secondary 위 텍스트)
val LightSecondaryContainer = Color(0xFFE8F5E8)         // 연한 그린 (가능 상태 배경)
val LightOnSecondaryContainer = Color(0xFF1B5E20)       // 매우 진한 그린 (컨테이너 위 텍스트)

// =========================
// Light Theme Tertiary Colors
// =========================
val LightTertiary = Color(0xFFF57C00)                   // 진한 오렌지 (경고 상태)
val LightOnTertiary = Color(0xFFFFFFFF)                 // 흰색 (Tertiary 위 텍스트)
val LightTertiaryContainer = Color(0xFFFFF3E0)          // 연한 오렌지 (경고 배경)
val LightOnTertiaryContainer = Color(0xFFE65100)        // 매우 진한 오렌지 (컨테이너 위 텍스트)

// =========================
// Light Theme Error Colors
// =========================
val LightError = Color(0xFFD32F2F)                      // 진한 레드 (에러 상태)
val LightOnError = Color(0xFFFFFFFF)                    // 흰색 (Error 위 텍스트)
val LightErrorContainer = Color(0xFFFFEBEE)             // 연한 레드 (에러 배경)
val LightOnErrorContainer = Color(0xFFB71C1C)           // 매우 진한 레드 (컨테이너 위 텍스트)

// =========================
// Light Theme Neutral Colors
// =========================
val LightBackground = Color(0xFFFEFBFF)                 // 메인 배경색 (거의 흰색)
val LightOnBackground = Color(0xFF1A1C1E)               // 배경 위 텍스트 (거의 검은색)
val LightSurface = Color(0xFFFEFBFF)                    // 카드, 시트 배경
val LightOnSurface = Color(0xFF1A1C1E)                  // Surface 위 텍스트
val LightSurfaceVariant = Color(0xFFE7E0EC)             // 대체 Surface (입력필드 등)
val LightOnSurfaceVariant = Color(0xFF49454F)           // SurfaceVariant 위 텍스트

// =========================
// Light Theme Outline Colors
// =========================
val LightOutline = Color(0xFF79747E)                    // 경계선 (비활성 상태)
val LightOutlineVariant = Color(0xFFCAC4D0)             // 약한 경계선

// =========================
// Light Theme Surface Container Colors (Material 3)
// =========================
val LightSurfaceDim = Color(0xFFDDD8E1)                 // 어두운 Surface
val LightSurfaceBright = Color(0xFFFEFBFF)              // 밝은 Surface
val LightSurfaceContainerLowest = Color(0xFFFFFFFF)     // 가장 낮은 elevation
val LightSurfaceContainerLow = Color(0xFFF7F2FA)        // 낮은 elevation
val LightSurfaceContainer = Color(0xFFF1ECF4)           // 기본 elevation
val LightSurfaceContainerHigh = Color(0xFFECE6F0)       // 높은 elevation
val LightSurfaceContainerHighest = Color(0xFFE6E0E9)    // 가장 높은 elevation

// =========================
// Light Theme Inverse Colors
// =========================
val LightInverseSurface = Color(0xFF2F3033)             // 다크 Surface (스낵바 등)
val LightInverseOnSurface = Color(0xFFF1F0F4)           // InverseSurface 위 텍스트
val LightInversePrimary = Color(0xFF90CAF9)             // Inverse Primary

// =========================
// Dark Theme Primary Colors
// =========================
val DarkPrimary = Color(0xFF90CAF9)                     // 연한 블루 (다크 모드)
val DarkOnPrimary = Color(0xFF003C8F)                   // 진한 블루 (Primary 위 텍스트)
val DarkPrimaryContainer = Color(0xFF0D47A1)            // 진한 블루 (컨테이너)
val DarkOnPrimaryContainer = Color(0xFFE3F2FD)          // 연한 블루 (컨테이너 위 텍스트)

// =========================
// Dark Theme Secondary Colors
// =========================
val DarkSecondary = Color(0xFFA5D6A7)                   // 연한 그린 (다크 모드)
val DarkOnSecondary = Color(0xFF2E7D32)                 // 진한 그린 (Secondary 위 텍스트)
val DarkSecondaryContainer = Color(0xFF1B5E20)          // 진한 그린 (컨테이너)
val DarkOnSecondaryContainer = Color(0xFFE8F5E8)        // 연한 그린 (컨테이너 위 텍스트)

// =========================
// Dark Theme Tertiary Colors
// =========================
val DarkTertiary = Color(0xFFFFCC02)                    // 연한 옐로우 (다크 모드 오렌지 대체)
val DarkOnTertiary = Color(0xFFFF8F00)                  // 오렌지 (Tertiary 위 텍스트)
val DarkTertiaryContainer = Color(0xFFE65100)           // 진한 오렌지 (컨테이너)
val DarkOnTertiaryContainer = Color(0xFFFFF3E0)         // 연한 오렌지 (컨테이너 위 텍스트)

// =========================
// Dark Theme Error Colors
// =========================
val DarkError = Color(0xFFEF5350)                       // 연한 레드 (다크 모드)
val DarkOnError = Color(0xFFFFFFFF)                     // 흰색 (Error 위 텍스트)
val DarkErrorContainer = Color(0xFFB71C1C)              // 진한 레드 (컨테이너)
val DarkOnErrorContainer = Color(0xFFFFEBEE)            // 연한 레드 (컨테이너 위 텍스트)

// =========================
// Dark Theme Neutral Colors
// =========================
val DarkBackground = Color(0xFF10131C)                  // 다크 배경 (매우 어두운 블루)
val DarkOnBackground = Color(0xFFE6E0E9)                // 배경 위 텍스트 (연한 회색)
val DarkSurface = Color(0xFF10131C)                     // 다크 Surface
val DarkOnSurface = Color(0xFFE6E0E9)                   // Surface 위 텍스트
val DarkSurfaceVariant = Color(0xFF49454F)              // 대체 Surface
val DarkOnSurfaceVariant = Color(0xFFCAC4D0)            // SurfaceVariant 위 텍스트

// =========================
// Dark Theme Outline Colors
// =========================
val DarkOutline = Color(0xFF938F99)                     // 다크 경계선
val DarkOutlineVariant = Color(0xFF49454F)              // 다크 약한 경계선

// =========================
// Dark Theme Surface Container Colors
// =========================
val DarkSurfaceDim = Color(0xFF10131C)                  // 어두운 Surface
val DarkSurfaceBright = Color(0xFF373A42)               // 밝은 Surface (다크 모드에서)
val DarkSurfaceContainerLowest = Color(0xFF0B0E17)      // 가장 낮은 elevation
val DarkSurfaceContainerLow = Color(0xFF181B24)         // 낮은 elevation
val DarkSurfaceContainer = Color(0xFF1C1F28)            // 기본 elevation
val DarkSurfaceContainerHigh = Color(0xFF272A33)        // 높은 elevation
val DarkSurfaceContainerHighest = Color(0xFF32353E)     // 가장 높은 elevation

// =========================
// Dark Theme Inverse Colors
// =========================
val DarkInverseSurface = Color(0xFFE6E0E9)              // 라이트 Surface (다크 모드에서 반전)
val DarkInverseOnSurface = Color(0xFF2F3033)            // InverseSurface 위 텍스트
val DarkInversePrimary = Color(0xFF1976D2)              // Inverse Primary

// =========================
// 앱별 특화 색상 (데모 화면 기준)
// =========================

/**
 * 주차장 상태별 색상
 * 데모에서 "45자리 이용가능", "23자리 이용가능" 등에 사용
 */
object ParkingStatus {
    val Available = Color(0xFF4CAF50)                    // 주차 가능 (초록)
    val AvailableContainer = Color(0xFFE8F5E8)           // 주차 가능 배경
    val OnAvailableContainer = Color(0xFF1B5E20)         // 주차 가능 텍스트

    val Limited = Color(0xFFFF9800)                      // 제한적 주차 (주황)
    val LimitedContainer = Color(0xFFFFF3E0)             // 제한적 배경
    val OnLimitedContainer = Color(0xFFE65100)           // 제한적 텍스트

    val Full = Color(0xFFF44336)                         // 주차 불가 (빨강)
    val FullContainer = Color(0xFFFFEBEE)                // 만차 배경
    val OnFullContainer = Color(0xFFB71C1C)              // 만차 텍스트
}

/**
 * 지도 관련 색상
 * 데모의 지도 화면에서 사용되는 마커, 위치 등
 */
object MapColors {
    val CurrentLocation = Color(0xFFE91E63)              // 현재 위치 핀 (분홍)
    val ParkingMarker = Color(0xFF4CAF50)                // 주차장 마커 (초록)
    val RouteColor = Color(0xFF2196F3)                   // 경로 색상 (파랑)
    val MapBackground = Color(0xFFE3F2FD)                // 지도 배경색 (연한 파랑)
    val MapOverlay = Color(0x80000000)                   // 지도 오버레이 (반투명 검정)
}

/**
 * 로그인 화면 그라데이션 색상
 * 데모의 로그인 화면 배경에 사용
 */
object LoginColors {
    val GradientStart = Color(0xFF667EEA)                // 그라데이션 시작 (보라)
    val GradientEnd = Color(0xFF764BA2)                  // 그라데이션 끝 (자주)
    val AppIconBackground = Color(0xFFFFFFFF)            // 앱 아이콘 배경 (흰색)
    val GoogleButtonBackground = Color(0xFFFFFFFF)       // Google 버튼 배경
    val GoogleButtonBorder = Color(0xFFDADCE0)           // Google 버튼 테두리
}

/**
 * 금액/가격 관련 색상
 * 데모의 "시간당 2,000원" 등 가격 표시에 사용
 */
object PriceColors {
    val Normal = Color(0xFF1976D2)                       // 일반 가격 (파랑)
    val Discount = Color(0xFF4CAF50)                     // 할인 가격 (초록)
    val Premium = Color(0xFFFF9800)                      // 프리미엄 가격 (주황)
    val Expensive = Color(0xFFF44336)                    // 비싼 가격 (빨강)
}

/**
 * 상태 표시 색상
 * 온라인/오프라인, 처리중 등 상태 표시
 */
object StatusColors {
    val Online = Color(0xFF4CAF50)                       // 온라인 상태 (초록)
    val Offline = Color(0xFF9E9E9E)                      // 오프라인 상태 (회색)
    val Processing = Color(0xFF2196F3)                   // 처리 중 (파랑)
    val Warning = Color(0xFFFF9800)                      // 경고 (주황)
    val Success = Color(0xFF4CAF50)                      // 성공 (초록)
    val Error = Color(0xFFF44336)                        // 오류 (빨강)
}

/**
 * 폼/입력 관련 색상
 * 차량등록, 결제수단 등록 화면의 입력 필드
 */
object FormColors {
    val InputBorder = Color(0xFFE0E0E0)                  // 입력 필드 기본 테두리
    val InputBorderFocused = Color(0xFF2196F3)           // 포커스된 입력 필드 테두리
    val InputBackground = Color(0xFFFFFFFF)              // 입력 필드 배경
    val LabelText = Color(0xFF333333)                    // 라벨 텍스트
    val PlaceholderText = Color(0xFF9E9E9E)              // 플레이스홀더 텍스트
    val HelperText = Color(0xFF666666)                   // 도움말 텍스트
    val ErrorText = Color(0xFFF44336)                    // 오류 텍스트
}

/**
 * 오버레이 및 반투명 색상
 * 로딩, 다이얼로그 배경 등에 사용
 */
object OverlayColors {
    val Light = Color(0x4DFFFFFF)                        // 30% 흰색 오버레이
    val Dark = Color(0x80000000)                         // 50% 검은색 오버레이
    val Scrim = Color(0x52000000)                        // Material scrim (32% 검정)
    val LoadingBackground = Color(0xCCFFFFFF)            // 로딩 배경 (80% 흰색)
}

/**
 * 마이페이지 메뉴 아이콘 색상
 * 데모의 마이페이지 메뉴 아이템들
 */
object MenuIconColors {
    val CarRegistration = Color(0xFF4CAF50)              // 차량 등록 아이콘 (초록)
    val PaymentMethod = Color(0xFF2196F3)                // 결제수단 아이콘 (파랑)
    val ReservationHistory = Color(0xFFFF9800)           // 예약 내역 아이콘 (주황)
    val Settings = Color(0xFF9C27B0)                     // 설정 아이콘 (보라)
    val Logout = Color(0xFFF44336)                       // 로그아웃 버튼 (빨강)
}

/**
 * 알파값 상수
 * 투명도 처리에 사용
 */
object AlphaValues {
    const val Disabled = 0.38f                          // 비활성 상태
    const val Pressed = 0.12f                           // 눌린 상태
    const val Hover = 0.08f                             // 호버 상태
    const val Focus = 0.24f                             // 포커스 상태
    const val Drag = 0.16f                              // 드래그 상태
}