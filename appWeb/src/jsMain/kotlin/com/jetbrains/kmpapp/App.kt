package com.jetbrains.kmpapp

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jetbrains.kmpapp.screens.detail.DetailScreen
import com.jetbrains.kmpapp.screens.list.ListScreen

@Composable
fun App() {
    val navController = rememberNavController()
    NavHost(
        navController, startDestination = "list"
    ) {
        composable("list") {
            ListScreen(navController)
        }
        composable("detail/{objectId}") { backStackEntry ->
            val objectId = backStackEntry.arguments?.getString("objectId")?.toInt()
            DetailScreen(navController, objectId!!)
        }
    }
}