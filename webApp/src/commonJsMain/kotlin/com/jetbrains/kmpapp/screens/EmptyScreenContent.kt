package com.jetbrains.kmpapp.screens

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text

@Composable
fun EmptyScreenContent() {
    Div({ classes("text-center") }) {
        Text("No data available")
//        Text(stringResource(Res.string.no_data_available))
    }
}
