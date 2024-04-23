package com.example.githubmvi.ui.feature.repos

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

@Composable
fun ReposScreen(
    state: ReposContract.State,
    effectFlow: Flow<ReposContract.Effect>?,
    onEventSent: (event: ReposContract.Event) -> Unit,
    onNavigationRequested: (ReposContract.Effect.Navigation) -> Unit
) {

    LaunchedEffect(Unit) {
        effectFlow?.collect { effect ->
            when (effect) {
                ReposContract.Effect.Navigation.Back -> {
                    onNavigationRequested(ReposContract.Effect.Navigation.Back)
                }
            }
        }
    }
}