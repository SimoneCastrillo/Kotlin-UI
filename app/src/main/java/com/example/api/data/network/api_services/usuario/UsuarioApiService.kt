package com.example.api.data.network.api_services.usuario

import com.example.api.data.model.request.login.LoginRequest
import com.example.api.data.model.request.usuario.UsuarioCadastroRequest
import com.example.api.data.model.response.login.LoginResponse
import com.example.api.data.model.response.usuario.UsuarioCadastroResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface UsuarioApiService {
    @POST("api/usuarios/login/1")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @GET("api/usuarios/{id}")
    suspend fun getUsuarioPorId(
        @Path("id") id: Int,
        @Header("Authorization") token: String
    ): Response<LoginResponse>

    @Multipart
    @PATCH("api/usuarios/{id}")
    suspend fun atualizarPerfil(
        @Path("id") id: Int,
        @Part("nome") nome: RequestBody,
        @Part("email") email: RequestBody,
        @Part("telefone") telefone: RequestBody,
        @Part foto: MultipartBody.Part?,
        @Header("Authorization") token: String
    )

    @POST("api/usuarios/1")
    suspend fun cadastrarUsuario(
        @Body usuario: UsuarioCadastroRequest
    ): Response<UsuarioCadastroResponse>
}