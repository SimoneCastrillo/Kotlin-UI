package com.example.api.data.repository.usuario

import android.content.Context
import android.net.Uri
import com.example.api.data.model.login.LoginResponse
import com.example.api.data.model.usuario.UsuarioUpdateRequest
import com.example.api.data.network.ApiClient.usuarioApiService
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import okio.IOException

class UsuarioRepository {
    suspend fun buscarPerfil(id: Int, token: String): Result<LoginResponse> {
        return try {
            val response = usuarioApiService.getUsuarioPorId(id, "Bearer $token")
            if (response.isSuccessful) {
                response.body()?.let {
                    Result.success(it)
                } ?: Result.failure(Exception("dados_invalidos"))
            } else {
                Result.failure(Exception("acesso_negado"))
            }
        } catch (e: IOException) {
            Result.failure(Exception("network_error"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun atualizarPerfil(id: Int, usuario: UsuarioUpdateRequest, token: String, fotoPart: MultipartBody.Part?) {
        val nomePart = usuario.nome.toRequestBody("text/plain".toMediaType())
        val emailPart = usuario.email.toRequestBody("text/plain".toMediaType())
        val telefonePart = usuario.telefone.toRequestBody("text/plain".toMediaType())

        // A foto será enviada como Multipart, se não for nula
        usuarioApiService.atualizarPerfil(
            id = id,
            nome = nomePart,
            email = emailPart,
            telefone = telefonePart,
            foto = fotoPart,  // Passa foto como Multipart
            token = "Bearer $token"
        )
    }

}