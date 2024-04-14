package com.example.githubmvi.data.repository

import com.example.githubmvi.data.model.Repository
import com.example.githubmvi.data.model.User
import com.example.githubmvi.data.model.UserDetail

interface UserRepository {
    suspend fun getUsers(): Result<List<User>>
    suspend fun getUser(username: String): Result<UserDetail?>
    suspend fun getRepositories(username: String, sort: String, order: String): Result<List<Repository>>
}