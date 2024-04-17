package com.example.githubmvi.data.model.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserDetailDto(
    @field:Json(name = "avatar_url") val avatarUrl: String,
    @field:Json(name = "html_url") val htmlUrl: String,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "location") val location: String?,
    @field:Json(name = "blog") val blogUrl: String,
    @field:Json(name = "public_repos") val publicRepos: Int,
    @field:Json(name = "followers") val followers: Int,
    @field:Json(name = "following") val following: Int
)
