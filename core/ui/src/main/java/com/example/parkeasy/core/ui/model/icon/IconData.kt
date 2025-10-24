package com.example.parkeasy.core.ui.model.icon

import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector

sealed class IconData {
    data class Vector(val imageVector: ImageVector) : IconData()
    data class Resource(val painter: Painter) : IconData()
    data class Url(val url: String) : IconData()
    object None : IconData()
}