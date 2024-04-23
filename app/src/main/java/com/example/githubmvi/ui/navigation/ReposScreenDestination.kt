package com.example.githubmvi.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.githubmvi.ui.feature.repos.ReposContract
import com.example.githubmvi.ui.feature.repos.ReposScreen
import com.example.githubmvi.ui.feature.repos.ReposViewModel

@Composable
fun ReposScreenDestination(UserId: String, navController: NavController) {
    val viewModel:ReposViewModel = hiltViewModel()
    ReposScreen(
        state = viewModel.viewState.value,
        effectFlow = viewModel.effect,
        onEventSent = { event -> viewModel.setEvent(event) },
        onNavigationRequested = { navigationEffect ->
            if (navigationEffect is ReposContract.Effect.Navigation.Back) {
                navController.popBackStack()
            }
        },
    )
}
