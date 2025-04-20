package com.example.api.data.repository.usuario

import com.example.api.data.model.request.usuario.UsuarioCadastroRequest
import com.example.api.data.model.response.login.LoginResponse
import com.example.api.data.model.request.usuario.UsuarioUpdateRequest
import com.example.api.data.model.response.usuario.UsuarioCadastroResponse
import com.example.api.data.network.ApiClient.usuarioApiService
import okhttp3.MediaType.Companion.toMediaType
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

    suspend fun cadastrarUsuario(usuario: UsuarioCadastroRequest): Result<UsuarioCadastroResponse> {
        return try {
            val response = usuarioApiService.cadastrarUsuario(usuario)
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    Result.success(body)
                } else {
                    Result.failure(Exception("Resposta vazia"))
                }
            } else {
                Result.failure(Exception("Erro ao cadastrar: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}