package com.example.githubmvi.data.model.response

import com.squareup.moshi.Json

data class RepositoryDto(
    @field:Json(name = "id") val id: Long,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "description") val description: String?,
    @field:Json(name = "watchers_count") val watchers: Int,
    @field:Json(name = "forks_count") val forks: Int,
    @field:Json(name = "stargazers_count") val stars: Int,
    @field:Json(name = "language") val language: String?,
    @field:Json(name = "html_url") val url: String
)

fun buildSampleRepository() = RepositoryDto(
    id = 1,
    name = "github-mvi",
    description = "Github MVI Sample",
    watchers = 10,
    forks = 20,
    stars = 30,
    language = "Kotlin",
    url = "https://www.github.com/myofficework/github-mvi"
)