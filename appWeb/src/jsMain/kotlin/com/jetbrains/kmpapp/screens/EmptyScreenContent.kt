package com.jetbrains.kmpapp.screens

import androidx.compose.runtime.Composable
import com.jetbrains.kmpapp.MR
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text
import stringResource

@Composable
fun EmptyScreenContent() {
    Div({ classes("text-center") }) {
        Text(stringResource(MR.strings.no_data_available))
    }
}
