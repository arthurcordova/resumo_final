package com.arcanesecurity.resumofinal.services

import com.arcanesecurity.resumofinal.BuildConfig
import com.arcanesecurity.resumofinal.model.PixabayImage
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayApi {

    @GET("/api/")
    suspend fun fetchImage(
        @Query("key") key: String = BuildConfig.API_KEY,
        @Query("q") q: String,
        @Query("lang") lang: String = "pt",
        @Query("page") page: Int = 1
    ): Response<PixabayImage>

}