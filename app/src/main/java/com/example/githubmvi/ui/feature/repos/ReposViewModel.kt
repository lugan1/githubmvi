package com.example.githubmvi.ui.feature.repos

import androidx.lifecycle.viewModelScope
import com.example.githubmvi.data.repository.UserRepository
import com.example.githubmvi.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class ReposViewModel(
    private val userId: String,
    private val githubRepository: UserRepository
) : BaseViewModel<ReposContract.Event, ReposContract.State, ReposContract.Effect>() {

    init { getAll() }

    override fun setInitialState() = ReposContract.State(
        user = null,
        reposList = emptyList(),
        isUserLoading = true,
        isReposLoading = true,
        isError = false,
    )

    override fun handleEvents(event: ReposContract.Event) {
        when (event) {
            ReposContract.Event.BackButtonClicked -> {
                setEffect { ReposContract.Effect.Navigation.Back }
            }
            ReposContract.Event.Retry -> getAll()
        }
    }

    private fun getAll() {
        viewModelScope.launch {
            getUser()
            getRepos()
        }
    }

    private suspend fun getUser() {
        githubRepository.getUser(userId)
            .onSuccess { userDetail ->
                setState { copy(user = userDetail, isUserLoading = false) }
            }
            .onFailure {
                setState { copy(isError = true, isUserLoading = false) }
            }
    }

    private suspend fun getRepos() {
        setState { copy(isReposLoading = true, isError = false) }

        githubRepository.getRepositories(userId, "stars", "desc")
            .onSuccess { repos ->
                setState { copy(reposList = repos, isReposLoading = false) }
            }
            .onFailure {
                setState { copy(isError = true, isReposLoading = false) }
            }
    }

}