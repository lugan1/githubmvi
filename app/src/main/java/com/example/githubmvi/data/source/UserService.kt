package com.example.githubmvi.data.source

import com.example.githubmvi.data.model.Repository
import com.example.githubmvi.data.model.User
import com.example.githubmvi.data.model.UserDetail
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
    suspend fun getUserDetail(@Path("username") username: String): UserDetail?

    @GET("$USERS/{$USER_NAME}/repos")
    suspend fun getRepositories(
        @Path(USER_NAME) username: String,
        @Query("sort") sort: String,
        @Query("order") order: String
    ): List<Repository>
}