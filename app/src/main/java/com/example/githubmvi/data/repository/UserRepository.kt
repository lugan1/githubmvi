package com.example.githubmvi.data.repository

import com.example.githubmvi.data.model.response.RepositoryDto
import com.example.githubmvi.data.model.response.User
import com.example.githubmvi.data.model.response.UserDetailDto

interface UserRepository {
    suspend fun getUsers(): Result<List<User>>
    suspend fun getUser(username: String): Result<UserDetailDto?>
    suspend fun getRepositories(username: String, sort: String, order: String): Result<List<RepositoryDto>>
}