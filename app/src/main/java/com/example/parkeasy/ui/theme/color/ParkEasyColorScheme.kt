package com.example.parkeasy.ui.theme.color

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import kotlin.math.pow

/**
 * ParkEasy 앱의 Material 3 ColorScheme 정의
 *
 * 디자인 철학:
 * - 신뢰성: 안정적인 주차 서비스를 위한 블루 기반 팔레트
 * - 직관성: 주차 상태를 쉽게 구분할 수 있는 의미있는 색상
 * - 접근성: WCAG 2.1 AA 기준을 충족하는 대비율
 * - 일관성: Material 3 가이드라인 준수
 */

/**
 * Light Theme ColorScheme
 * 주간 모드에서 사용되는 색상 스킴
 */
val ParkEasyLightColorScheme = lightColorScheme(
    // Primary Colors - 메인 브랜드 컬러 (파킹 관련 신뢰성 표현)
    primary = LightPrimary,
    onPrimary = LightOnPrimary,
    primaryContainer = LightPrimaryContainer,
    onPrimaryContainer = LightOnPrimaryContainer,

    // Secondary Colors - 성공/가능 상태 (녹색 계열)
    secondary = LightSecondary,
    onSecondary = LightOnSecondary,
    secondaryContainer = LightSecondaryContainer,
    onSecondaryContainer = LightOnSecondaryContainer,

    // Tertiary Colors - 경고/주의 상태 (주황색 계열)
    tertiary = LightTertiary,
    onTertiary = LightOnTertiary,
    tertiaryContainer = LightTertiaryContainer,
    onTertiaryContainer = LightOnTertiaryContainer,

    // Error Colors - 오류/위험 상태
    error = LightError,
    onError = LightOnError,
    errorContainer = LightErrorContainer,
    onErrorContainer = LightOnErrorContainer,

    // Background Colors
    background = LightBackground,
    onBackground = LightOnBackground,

    // Surface Colors
    surface = LightSurface,
    onSurface = LightOnSurface,
    surfaceVariant = LightSurfaceVariant,
    onSurfaceVariant = LightOnSurfaceVariant,

    // Surface Container Colors (Material 3의 새로운 토큰들)
    surfaceDim = LightSurfaceDim,
    surfaceBright = LightSurfaceBright,
    surfaceContainerLowest = LightSurfaceContainerLowest,
    surfaceContainerLow = LightSurfaceContainerLow,
    surfaceContainer = LightSurfaceContainer,
    surfaceContainerHigh = LightSurfaceContainerHigh,
    surfaceContainerHighest = LightSurfaceContainerHighest,

    // Outline Colors
    outline = LightOutline,
    outlineVariant = LightOutlineVariant,

    // Inverse Colors
    inverseSurface = LightInverseSurface,
    inverseOnSurface = LightInverseOnSurface,
    inversePrimary = LightInversePrimary,

    // Scrim
    scrim = Color.Black,
)

/**
 * Dark Theme ColorScheme
 * 야간 모드에서 사용되는 색상 스킴
 */
val ParkEasyDarkColorScheme = darkColorScheme(
    // Primary Colors
    primary = DarkPrimary,
    onPrimary = DarkOnPrimary,
    primaryContainer = DarkPrimaryContainer,
    onPrimaryContainer = DarkOnPrimaryContainer,

    // Secondary Colors
    secondary = DarkSecondary,
    onSecondary = DarkOnSecondary,
    secondaryContainer = DarkSecondaryContainer,
    onSecondaryContainer = DarkOnSecondaryContainer,

    // Tertiary Colors
    tertiary = DarkTertiary,
    onTertiary = DarkOnTertiary,
    tertiaryContainer = DarkTertiaryContainer,
    onTertiaryContainer = DarkOnTertiaryContainer,

    // Error Colors
    error = DarkError,
    onError = DarkOnError,
    errorContainer = DarkErrorContainer,
    onErrorContainer = DarkOnErrorContainer,

    // Background Colors
    background = DarkBackground,
    onBackground = DarkOnBackground,

    // Surface Colors
    surface = DarkSurface,
    onSurface = DarkOnSurface,
    surfaceVariant = DarkSurfaceVariant,
    onSurfaceVariant = DarkOnSurfaceVariant,

    // Surface Container Colors
    surfaceDim = DarkSurfaceDim,
    surfaceBright = DarkSurfaceBright,
    surfaceContainerLowest = DarkSurfaceContainerLowest,
    surfaceContainerLow = DarkSurfaceContainerLow,
    surfaceContainer = DarkSurfaceContainer,
    surfaceContainerHigh = DarkSurfaceContainerHigh,
    surfaceContainerHighest = DarkSurfaceContainerHighest,

    // Outline Colors
    outline = DarkOutline,
    outlineVariant = DarkOutlineVariant,

    // Inverse Colors
    inverseSurface = DarkInverseSurface,
    inverseOnSurface = DarkInverseOnSurface,
    inversePrimary = DarkInversePrimary,

    // Scrim
    scrim = Color.Black,
)

/**
 * Extended Color Scheme
 * Material 3 표준 컬러 외에 ParkEasy 앱에서 사용하는 확장 색상들
 */
@Immutable
data class ExtendedColorScheme(
    val parkingAvailable: Color,
    val onParkingAvailable: Color,
    val parkingAvailableContainer: Color,
    val onParkingAvailableContainer: Color,

    val parkingLimited: Color,
    val onParkingLimited: Color,
    val parkingLimitedContainer: Color,
    val onParkingLimitedContainer: Color,

    val parkingFull: Color,
    val onParkingFull: Color,
    val parkingFullContainer: Color,
    val onParkingFullContainer: Color,

    val currentLocationMarker: Color,
    val parkingMarker: Color,
    val routeColor: Color,

//    val priceColor: Color,
//    val discountColor: Color,
//    val premiumColor: Color,

    val statusOnline: Color,
    val statusOffline: Color,
    val statusProcessing: Color,
    val statusWarning: Color,

//    val overlayLight: Color,
//    val overlayDark: Color,
//    val scrim: Color,
)

/**
 * Light Extended Colors
 */
val LightExtendedColors = ExtendedColorScheme(
    parkingAvailable = ParkingStatus.Available,
    onParkingAvailable = Color.White,
    parkingAvailableContainer = ParkingStatus.AvailableContainer,
    onParkingAvailableContainer = ParkingStatus.OnAvailableContainer,

    parkingLimited = ParkingStatus.Limited,
    onParkingLimited = Color.White,
    parkingLimitedContainer = ParkingStatus.LimitedContainer,
    onParkingLimitedContainer = ParkingStatus.OnLimitedContainer,

    parkingFull = ParkingStatus.Full,
    onParkingFull = Color.White,
    parkingFullContainer = ParkingStatus.FullContainer,
    onParkingFullContainer = ParkingStatus.OnFullContainer,

    currentLocationMarker = MapColors.CurrentLocation,
    parkingMarker = MapColors.ParkingMarker,
    routeColor = MapColors.RouteColor,

//    priceColor = FinancialColors.Price,
//    discountColor = FinancialColors.Discount,
//    premiumColor = FinancialColors.Premium,

    statusOnline = StatusColors.Online,
    statusOffline = StatusColors.Offline,
    statusProcessing = StatusColors.Processing,
    statusWarning = StatusColors.Warning,

//    overlayLight = AlphaColors.OverlayLight,
//    overlayDark = AlphaColors.OverlayDark,
//    scrim = AlphaColors.Scrim,
)

/**
 * Dark Extended Colors
 */
val DarkExtendedColors = ExtendedColorScheme(
    parkingAvailable = ParkingStatus.Available,
    onParkingAvailable = Color.Black,
    parkingAvailableContainer = ParkingStatus.OnAvailableContainer,
    onParkingAvailableContainer = ParkingStatus.AvailableContainer,

    parkingLimited = ParkingStatus.Limited,
    onParkingLimited = Color.Black,
    parkingLimitedContainer = ParkingStatus.OnLimitedContainer,
    onParkingLimitedContainer = ParkingStatus.LimitedContainer,

    parkingFull = ParkingStatus.Full,
    onParkingFull = Color.Black,
    parkingFullContainer = ParkingStatus.OnFullContainer,
    onParkingFullContainer = ParkingStatus.FullContainer,

    currentLocationMarker = MapColors.CurrentLocation,
    parkingMarker = MapColors.ParkingMarker,
    routeColor = MapColors.RouteColor,

//    priceColor = FinancialColors.Price,
//    discountColor = FinancialColors.Discount,
//    premiumColor = FinancialColors.Premium,

    statusOnline = StatusColors.Online,
    statusOffline = StatusColors.Offline,
    statusProcessing = StatusColors.Processing,
    statusWarning = StatusColors.Warning,

//    overlayLight = AlphaColors.OverlayLight,
//    overlayDark = AlphaColors.OverlayDark,
//    scrim = AlphaColors.Scrim,
)

/**
 * CompositionLocal for Extended Colors
 * Compose에서 확장 컬러에 접근하기 위한 CompositionLocal
 */
val LocalExtendedColors = staticCompositionLocalOf { LightExtendedColors }

/**
 * Extension property to access extended colors from MaterialTheme
 * 사용법: MaterialTheme.extendedColors.parkingAvailable
 */
val androidx.compose.material3.MaterialTheme.extendedColors: ExtendedColorScheme
    @Composable
    get() = LocalExtendedColors.current

/**
 * Utility functions for dynamic color generation
 * 동적 색상 생성을 위한 유틸리티 함수들
 */
object ColorUtils {

    /**
     * 주차 가능한 자리 수에 따른 색상 반환
     * @param availableSpots 사용 가능한 주차 자리 수
     * @param totalSpots 전체 주차 자리 수
     * @return 상태에 따른 색상
     */
    @Composable
    fun getParkingStatusColor(availableSpots: Int, totalSpots: Int): Color {
        val ratio = availableSpots.toFloat() / totalSpots.toFloat()
        return when {
            ratio > 0.3f -> MaterialTheme.extendedColors.parkingAvailable
            ratio > 0.1f -> MaterialTheme.extendedColors.parkingLimited
            else -> MaterialTheme.extendedColors.parkingFull
        }
    }

    /**
     * 주차 요금에 따른 색상 반환
     * @param price 시간당 주차 요금
     * @return 요금 수준에 따른 색상
     */
//    @Composable
//    fun getPriceColor(price: Int): Color {
//        return when {
//            price < 2000 -> MaterialTheme.extendedColors.discountColor
//            price > 3000 -> MaterialTheme.extendedColors.premiumColor
//            else -> MaterialTheme.extendedColors.priceColor
//        }
//    }

    /**
     * 색상에 알파값 적용
     * @param color 원본 색상
     * @param alpha 알파값 (0.0f ~ 1.0f)
     * @return 알파가 적용된 색상
     */
    fun Color.withAlpha(alpha: Float): Color {
        return this.copy(alpha = alpha)
    }
}

/**
 * 색상 접근성 체크를 위한 확장 함수들
 */
object AccessibilityColors {

    /**
     * 두 색상 간의 대비율 계산
     * WCAG 2.1 AA 기준: 4.5:1 이상
     * WCAG 2.1 AAA 기준: 7:1 이상
     */
    fun getContrastRatio(foreground: Color, background: Color): Float {
        val luminance1 = getLuminance(foreground) + 0.05f
        val luminance2 = getLuminance(background) + 0.05f
        return maxOf(luminance1, luminance2) / minOf(luminance1, luminance2)
    }

    /**
     * 색상의 상대적 휘도 계산
     */
    private fun getLuminance(color: Color): Float {
        val r = if (color.red <= 0.03928f) color.red / 12.92f else ((color.red + 0.055) / 1.055f).pow(2.4).toFloat()
        val g = if (color.green <= 0.03928f) color.green / 12.92f else ((color.green + 0.055) / 1.055).pow(2.4).toFloat()
        val b = if (color.blue <= 0.03928f) color.blue / 12.92f else ((color.blue + 0.055) / 1.055).pow(2.4).toFloat()
        return 0.2126f * r + 0.7152f * g + 0.0722f * b
    }
}