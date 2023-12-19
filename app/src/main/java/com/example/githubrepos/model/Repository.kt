package com.example.githubrepos.model

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parceler
import kotlinx.parcelize.Parcelize

@Parcelize
data class Repository(
    val id: String,
    val name: String,
    val author: String,
    val created: String,
    val updated: String,
    val watchers: Int,
    val issues: Int,
    val forks: Int
) : Parcelable
