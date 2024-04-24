package com.example.githubmvi.ui.navigation.controller

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.githubmvi.ui.navigation.Navigation

class MainNavController(
    val navController: NavHostController
) {
    private val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination


    val startDestination = Navigation.Routes.USERS

    fun navigateToRepos(userId: String) {
        navController.navigate("${Navigation.Routes.REPOS}/$userId")
    }

    fun popBackStack() {
        navController.popBackStack()
    }

    fun clearBackSTack() {
        val options = NavOptions.Builder()
            .setPopUpTo(navController.graph.findStartDestination().id, inclusive = false)
            .build()
        navController.navigate(startDestination, options)
    }

    fun isSameCurrentDestination(route: String): Boolean {
        return navController.currentDestination?.route == route
    }
}

@Composable
fun rememberMainNavController(
    navController: NavHostController = rememberNavController()
): MainNavController {
    return remember(navController) { MainNavController(navController) }
}