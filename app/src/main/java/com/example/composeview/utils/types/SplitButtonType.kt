package com.example.composeview.utils.types

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ScheduleSend
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.composeview.R

enum class SplitButtonType(val stringResourceId: Int) {
    SENT(R.string.leading_button_name),
    SET_SENT_DATE_TIME(R.string.set_sending_date_and_time);

    fun getIcon(): ImageVector {
        return when (this) {
            SENT -> Icons.AutoMirrored.Filled.Send
            SET_SENT_DATE_TIME -> Icons.AutoMirrored.Filled.ScheduleSend
        }
    }

    fun getReverseTypeStringResourceId(): Int {
        return when (this) {
            SENT -> R.string.set_sending_date_and_time
            SET_SENT_DATE_TIME -> R.string.leading_button_name
        }
    }

    fun getReverseType(): SplitButtonType {
        return when (this) {
            SENT -> SET_SENT_DATE_TIME
            SET_SENT_DATE_TIME -> SENT
        }
    }
}