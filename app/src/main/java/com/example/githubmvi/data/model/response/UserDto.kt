package com.example.githubmvi.data.model.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class User(
    @field:Json(name = "login") val userId: String,
    @field:Json(name = "avatar_url") val avatarUrl: String,
    @field:Json(name = "html_url") val htmlUrl: String
)

// Preview 용의 샘플 데이터 생성
fun buildSampleUser() = User(
    userId = "51234843",
    avatarUrl = "https://avatars.githubusercontent.com/myofficework000",
    htmlUrl = "https://github.com/51234843",
)
