package com.example.githubmvi.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.githubmvi.ui.navigation.compose.UsersScreenDestination
import com.example.githubmvi.ui.navigation.controller.LocalMainNavController
import com.example.githubmvi.ui.navigation.controller.MainNavController
import com.example.githubmvi.ui.navigation.controller.rememberMainNavController

@Composable
fun AppNavigation(
    navigator: MainNavController = rememberMainNavController()
) {
    CompositionLocalProvider(
        LocalMainNavController provides navigator
    ) {
        NavHost(
            navController = navigator.navController,
            startDestination = navigator.startDestination
        ) {
            composable(route = Navigation.Routes.USERS) {
                UsersScreenDestination()
            }
        }
    }
}