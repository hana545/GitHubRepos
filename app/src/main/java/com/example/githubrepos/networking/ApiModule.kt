package com.example.githubrepos.networking
import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

object ApiModule {

    private const val BASE_URL = "https://api.github.com/"

    lateinit var retrofit: GitHubApiService

    private val json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    fun initRetrofit(context: Context) {

        val okhttp = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .addInterceptor(ChuckerInterceptor.Builder(context = context).build())
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .client(okhttp)
            .build()
            .create(GitHubApiService::class.java)
    }
}