package com.example.githubrepos.model

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parceler
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class Repository(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("full_name") val fullName: String,
    @SerialName("owner") val author: User,
    @SerialName("git_url") val url: String,
    @SerialName("description") val description: String?,
    @SerialName("created_at") val created: String,
    @SerialName("updated_at") val updated: String,
    @SerialName("stargazers_count") val stars: Int,
    @SerialName("watchers_count") val watchers: Int,
    @SerialName("open_issues_count") val issues: Int,
    @SerialName("forks_count") val forks: Int,
    @SerialName("languages_url") val languagesUrl: String,
    @SerialName("contributors_url") val contributorsUrl: String
) : java.io.Serializable

@kotlinx.serialization.Serializable
data class ListRepositoriesResponse(
    @SerialName("items") val repos: MutableList<Repository>
)
