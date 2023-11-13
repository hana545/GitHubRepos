package com.example.githubrepos.model

data class Repository(
    val id: String,
    val name: String,
    val author: String,
    val created: String,
    val updated: String,
    val watchers: Int,
    val issues: Int,
    val forks: Int
)
