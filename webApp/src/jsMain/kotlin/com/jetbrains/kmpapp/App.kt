package com.jetbrains.kmpapp

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import com.jetbrains.kmpapp.screens.list.ListScreen

@Composable
fun App() {
    Navigator(ListScreen)
}