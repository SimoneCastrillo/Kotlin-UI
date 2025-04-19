package com.example.api.data.network

import com.example.api.data.model.LoginRequest
import com.example.api.data.model.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("usuarios/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @GET("usuarios/{id}")
    suspend fun getUsuarioPorId(
        @Path("id") id: Int,
        @Header("Authorization") token: String
    ): Response<LoginResponse>
}