package com.example.appstarwars

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("people")
    suspend fun getResponse(): Response<Responses>
}
