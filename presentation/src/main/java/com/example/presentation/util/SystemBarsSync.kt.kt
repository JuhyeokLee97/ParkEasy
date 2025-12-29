package com.example.presentation.util

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowInsetsControllerCompat


@Composable
fun SyncSystemBars(
    syncStatusBar: Boolean = false,
    syncNavigationBar: Boolean = false,
) {
    val view = LocalView.current
    val bg = MaterialTheme.colorScheme.background
    val isLight = bg.luminance() > 0.5f

    DisposableEffect(bg, isLight) {
        val activity = view.context as? Activity ?: return@DisposableEffect onDispose { }
        val window = activity.window
        val controller = WindowInsetsControllerCompat(window, view)
        if (syncStatusBar) {
            window.statusBarColor = bg.toArgb()
            controller.isAppearanceLightStatusBars = isLight
        }

        if (syncNavigationBar) {
            window.navigationBarColor = bg.toArgb()
            controller.isAppearanceLightNavigationBars = isLight
        }

        onDispose { }
    }
}