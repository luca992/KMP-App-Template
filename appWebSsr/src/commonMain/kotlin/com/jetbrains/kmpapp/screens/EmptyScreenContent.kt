package com.jetbrains.kmpapp.screens

import androidx.compose.runtime.Composable
import dev.kilua.core.IComponent
import dev.kilua.html.div

@Composable
fun IComponent.EmptyScreenContent() {
    div {
        className("text-center")
        +"TODO"// Text(stringResource(MR.strings.no_data_available))
    }
}
