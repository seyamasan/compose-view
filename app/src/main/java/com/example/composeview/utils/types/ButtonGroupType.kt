package com.example.composeview.utils.types

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Coffee
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Work
import androidx.compose.material.icons.outlined.Coffee
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Restaurant
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Work
import androidx.compose.ui.graphics.vector.ImageVector

enum class ButtonGroupType(val label: String) {
    WORK("Work"),
    RESTAURANT("Restaurant"),
    COFFEE("Coffee"),
    SEARCH("Search"),
    HOME("Home");

    fun getCheckedIcon(): ImageVector {
        return when (this) {
            WORK -> Icons.Filled.Work
            RESTAURANT -> Icons.Filled.Restaurant
            COFFEE -> Icons.Filled.Coffee
            SEARCH -> Icons.Filled.Search
            HOME -> Icons.Filled.Home
        }
    }

    fun getUnCheckedIcon(): ImageVector {
        return when (this) {
            WORK -> Icons.Outlined.Work
            RESTAURANT -> Icons.Outlined.Restaurant
            COFFEE -> Icons.Outlined.Coffee
            SEARCH -> Icons.Outlined.Search
            HOME -> Icons.Outlined.Home
        }
    }
}