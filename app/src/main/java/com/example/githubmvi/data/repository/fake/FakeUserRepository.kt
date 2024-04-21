package com.example.githubmvi.data.repository.fake

import com.example.githubmvi.data.model.response.RepositoryDto
import com.example.githubmvi.data.model.response.User
import com.example.githubmvi.data.model.response.UserDetailDto
import com.example.githubmvi.data.model.response.buildSampleRepository
import com.example.githubmvi.data.model.response.buildSampleUser
import com.example.githubmvi.data.model.response.buildSampleUserDetail
import com.example.githubmvi.data.repository.UserRepository
import com.example.githubmvi.data.repository.impl.makeApiCall
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class FakeUserRepository @Inject constructor(
    private val dispatcher: CoroutineDispatcher
): UserRepository {
    override suspend fun getUsers(): Result<List<User>> = makeApiCall(dispatcher) {
        List(5) { buildSampleUser() }
    }

    override suspend fun getUser(username: String): Result<UserDetailDto?> = makeApiCall(dispatcher) {
        buildSampleUserDetail()
    }

    override suspend fun getRepositories(
        username: String,
        sort: String,
        order: String
    ): Result<List<RepositoryDto>> = makeApiCall(dispatcher) {
        List(5) { buildSampleRepository() }
    }
}