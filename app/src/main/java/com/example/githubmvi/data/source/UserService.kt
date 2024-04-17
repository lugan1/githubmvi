package com.example.githubmvi.data.source

import com.example.githubmvi.data.model.response.RepositoryDto
import com.example.githubmvi.data.model.response.User
import com.example.githubmvi.data.model.response.UserDetailDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserService {
    companion object {
        private const val USERS = "users"
        private const val USER_NAME = "username"
    }

    @GET(USERS)
    suspend fun getUsers(@Query("since") since: Int): List<User>

    @GET("$USERS/{$USER_NAME}")
    suspend fun getUserDetail(@Path("username") username: String): UserDetailDto?

    @GET("$USERS/{$USER_NAME}/repos")
    suspend fun getRepositories(
        @Path(USER_NAME) username: String,
        @Query("sort") sort: String,
        @Query("order") order: String
    ): List<RepositoryDto>
}