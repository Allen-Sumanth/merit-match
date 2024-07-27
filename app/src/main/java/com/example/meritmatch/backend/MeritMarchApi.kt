package com.example.meritmatch.backend

import retrofit2.Response
import retrofit2.http.GET

interface MeritMarchApi {
    @GET("/check-account")
    suspend fun checkAccount(): Response<AccCheck>
}