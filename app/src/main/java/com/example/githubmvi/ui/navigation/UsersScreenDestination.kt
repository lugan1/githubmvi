package com.example.githubmvi.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.githubmvi.ui.feature.users.UsersContract
import com.example.githubmvi.ui.feature.users.UsersViewModel
import com.example.githubmvi.ui.feature.users.screen.UsersScreen

@Composable
fun UsersScreenDestination(
    onNavigateToRepos: (String) -> Unit,
) {
    val viewModel: UsersViewModel = hiltViewModel()
    UsersScreen(
        state = viewModel.viewState.value,
        effectFlow = viewModel.effect,
        onEventSent = { event -> viewModel.setEvent(event) },
        onNavigationRequested = { navigationEffect ->
            if (navigationEffect is UsersContract.Effect.Navigation.ToRepos) {
                //todo: navigate to repos
                onNavigateToRepos(navigationEffect.userId)
            }
        }
    )
}