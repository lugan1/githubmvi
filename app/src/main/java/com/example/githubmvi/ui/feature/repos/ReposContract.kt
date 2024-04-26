package com.example.githubmvi.ui.feature.repos

import com.example.githubmvi.data.model.response.RepositoryDto
import com.example.githubmvi.data.model.response.UserDetailDto
import com.example.githubmvi.ui.base.ViewEvent
import com.example.githubmvi.ui.base.ViewSideEffect
import com.example.githubmvi.ui.base.ViewState

class ReposContract {

    sealed class Event : ViewEvent {
        object Retry : Event()
        object BackButtonClicked : Event()
    }

    data class State(
        val user: UserDetailDto?,
        val reposList: List<RepositoryDto>,
        val isUserLoading: Boolean,
        val isReposLoading: Boolean,
        val isError: Boolean,
    ) : ViewState

    sealed class Effect : ViewSideEffect {
        sealed class Navigation : Effect() {
            data object Back : Navigation()
        }
    }

}
