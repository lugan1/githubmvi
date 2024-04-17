package com.example.githubmvi.ui.feature.users

import androidx.lifecycle.viewModelScope
import com.example.githubmvi.data.repository.UserRepository
import com.example.githubmvi.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UsersViewModel @Inject constructor(
    private val repository: UserRepository
): BaseViewModel<UsersContract.Event, UsersContract.State, UsersContract.Effect>(){
    init {
        getUsers()
    }

    // UI의 초기 상태를 설정하는 부분
    override fun setInitialState(): UsersContract.State = UsersContract.State(
        users = emptyList(),
        isLoading = true,
        isError = false
    )

    // 사용자에게 입력받은 Intent 를 처리하는 부분
    override fun handleEvents(event: UsersContract.Event) {
        when(event) {
            // 사용자가 유저를 선택했을 때
            is UsersContract.Event.UserSelection -> setEffect {
                UsersContract.Effect.Navigation.ToRepos(event.user.userId)
            }

            // 사용자가 오류화면에서 재시도를 눌렀을 때
            is UsersContract.Event.Retry -> getUsers()
        }
    }

    private fun getUsers() {
        // State reducer
        setState { copy(isLoading = true, isError = false) }

        viewModelScope.launch {
            repository.getUsers()
                .onSuccess { users ->
                    setState { copy(users = users, isLoading = false) }
                    setEffect { UsersContract.Effect.DataWasLoaded }
                }
                .onFailure {
                    setState { copy(isError = true, isLoading = false) }
                }
        }
    }
}