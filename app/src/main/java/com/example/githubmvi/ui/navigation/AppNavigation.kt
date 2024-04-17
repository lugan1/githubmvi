package com.example.githubmvi.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Navigation.Routes.USERS
    ) {
        composable(route = Navigation.Routes.USERS) {
            UsersScreenDestination(navController)
        }
    }
}