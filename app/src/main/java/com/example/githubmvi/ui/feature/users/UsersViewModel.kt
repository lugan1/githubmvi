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

    override fun setInitialState(): UsersContract.State {
        TODO("Not yet implemented")
        // UI의 초기 상태를 설정하는 부분
    }

    override fun handleEvents(event: UsersContract.Event) {
        TODO("Not yet implemented")
        // User 에게 입력받은 Intent 를 처리하는 부분
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