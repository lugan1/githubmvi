package com.example.githubmvi.data.repository.impl

import com.example.githubmvi.data.model.Repository
import com.example.githubmvi.data.model.User
import com.example.githubmvi.data.model.UserDetail
import com.example.githubmvi.data.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(): UserRepository {

    override suspend fun getUsers(): Result<List<User>> {
        TODO("Not yet implemented")
    }

    override suspend fun getUser(username: String): Result<UserDetail?> {
        TODO("Not yet implemented")
    }

    override suspend fun getRepositories(
        username: String,
        sort: String,
        order: String
    ): Result<List<Repository>> {
        TODO("Not yet implemented")
    }
}