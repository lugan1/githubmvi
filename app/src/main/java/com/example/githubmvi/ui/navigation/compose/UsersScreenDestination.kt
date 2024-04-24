package com.example.githubmvi.ui.navigation.compose

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.githubmvi.ui.feature.users.UsersContract
import com.example.githubmvi.ui.feature.users.UsersViewModel
import com.example.githubmvi.ui.feature.users.UsersScreen
import com.example.githubmvi.ui.navigation.controller.MainNavController
import com.example.githubmvi.ui.navigation.controller.currentMainNavController

@Composable
fun UsersScreenDestination() {
    val viewModel: UsersViewModel = hiltViewModel()
    val currentMainNavController: MainNavController = currentMainNavController
    UsersScreen(
        state = viewModel.viewState.value,
        effectFlow = viewModel.effect,
        onEventSent = { event -> viewModel.setEvent(event) },
        onNavigationRequested = { navigationEffect ->
            if (navigationEffect is UsersContract.Effect.Navigation.ToRepos) {
                //todo: navigate to repos
                currentMainNavController.navigateToRepos(navigationEffect.userId)
            }
        }
    )
}