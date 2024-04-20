package com.example.githubmvi.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavigation(
    navigator: MainNavController = rememberMainNavController()
) {
    NavHost(
        navController =navigator.navController,
        startDestination = navigator.startDestination
    ) {
        composable(route = Navigation.Routes.USERS) {
            UsersScreenDestination(onNavigateToRepos = navigator::navigateToRepos)
        }
    }
}