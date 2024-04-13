package com.example.githubmvi.data.source

import retrofit2.http.GET

interface UserService {
    companion object {
        private const val USERS = "users"
    }
}