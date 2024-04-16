package com.example.githubmvi.ui.navigation

import com.example.githubmvi.ui.navigation.Navigation.Args.USER_ID

object Navigation {
    object Args {
        const val USER_ID = "user_id"
    }

    object Routes {
        const val USERS = "users"
        const val REPOS = "$USERS/{$USER_ID}"
    }
}