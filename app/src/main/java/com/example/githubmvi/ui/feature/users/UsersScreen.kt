package com.example.githubmvi.ui.feature.users

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.githubmvi.R
import com.example.githubmvi.ui.feature.common.NetworkError
import com.example.githubmvi.ui.feature.common.Progress
import com.example.githubmvi.ui.feature.users.UsersContract
import com.example.githubmvi.ui.feature.users.component.UsersList
import com.example.githubmvi.ui.feature.users.component.UsersTopBar
import kotlinx.coroutines.flow.Flow

@Composable
fun UsersScreen(
    state: UsersContract.State,
    effectFlow: Flow<UsersContract.Effect>?,
    onEventSent: (event: UsersContract.Event) -> Unit,
    onNavigationRequested: (navigationEffect: UsersContract.Effect.Navigation) -> Unit
) {
    val snackBarState = remember { SnackbarHostState() }
    val snackBarMessage = stringResource(R.string.users_screen_snackbar_loaded_message)

    // 사이드 이펙트 감지 후 처리
    LaunchedEffect(key1 = Unit) {
        effectFlow?.collect{ effect ->
            when (effect) {
                // 유저 데이터 로드 완료
                is UsersContract.Effect.DataWasLoaded -> {
                    //todo: 스낵바 출력
                    snackBarState.showSnackbar(
                        message = snackBarMessage,
                        duration = SnackbarDuration.Short
                    )
                }
                is UsersContract.Effect.Navigation.ToRepos -> {
                    onNavigationRequested(effect)
                }
            }
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackBarState,
                snackbar = { data -> Snackbar(snackbarData = data) }
            )
        },
        topBar = { UsersTopBar() }
    ) { paddingValues ->
        when {
            state.isLoading -> {
                // 로딩 중
                Progress(modifier = Modifier.padding(paddingValues))
            }
            state.isError -> {
                // 에러 발생
                NetworkError(
                    modifier = Modifier.padding(paddingValues),
                    onRetryButtonClick = {
                        onEventSent(UsersContract.Event.Retry)
                    }
                )
            }
            else -> {
                // 사용자 목록 출력
                UsersList(
                    modifier = Modifier.padding(paddingValues),
                    users = state.users,
                    onItemClick = { user ->
                        onEventSent(UsersContract.Event.UserSelection(user))
                    }
                )
            }
        }
    }
}