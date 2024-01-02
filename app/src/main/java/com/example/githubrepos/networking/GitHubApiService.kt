package com.example.githubrepos.networking

import com.example.githubrepos.model.ListRepositoriesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface  GitHubApiService {

    @GET("search/repositories")
    suspend fun searchRepositories(@Query("q") query: String): Response<ListRepositoriesResponse>
}