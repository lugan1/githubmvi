package com.example.githubmvi.ui.feature.users.screen

import androidx.compose.runtime.Composable
import com.example.githubmvi.ui.feature.users.UsersContract
import kotlinx.coroutines.flow.Flow

@Composable
fun UsersScreen(
    state: UsersContract.State,
    effectFlow: Flow<UsersContract.Effect>?,
    onEventSent: (event: UsersContract.Event) -> Unit,
    onNavigationRequested: (navigationEffect: UsersContract.Effect.Navigation) -> Unit
) {

}