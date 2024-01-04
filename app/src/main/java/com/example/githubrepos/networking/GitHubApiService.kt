package com.example.githubrepos.networking

import com.example.githubrepos.model.ListRepositoriesResponse
import com.example.githubrepos.model.Repository
import com.example.githubrepos.model.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface  GitHubApiService {

    @GET("search/repositories")
    suspend fun searchRepositories(@Query("q") query: String): Response<ListRepositoriesResponse>

    @GET("repos/{repo_user}/{repo_name}")
    suspend fun getRepository(@Path("repo_user") repoUser: String, @Path("repo_name") repoName: String): Response<Repository>

    @GET
    suspend fun getLanguage(@Url url: String): Response<Map<String, Int>>

    @GET
    suspend fun getContributors(@Url url: String): Response<List<User>>
}