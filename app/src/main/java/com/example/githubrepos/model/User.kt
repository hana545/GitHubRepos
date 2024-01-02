package com.example.githubrepos.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User (
    @SerialName("login") val name: String,
    @SerialName("id") val id: Int,
    @SerialName("avatar_url") val avatarUrl: String,
    // Add other properties as needed
    ) : java.io.Serializable