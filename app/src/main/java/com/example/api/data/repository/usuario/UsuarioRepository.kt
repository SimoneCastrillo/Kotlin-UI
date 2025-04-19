package com.example.api.data.repository.usuario

import com.example.api.data.model.login.LoginResponse
import com.example.api.data.network.ApiClient.apiService
import okio.IOException

class UsuarioRepository {
    suspend fun buscarPerfil(id: Int, token: String): Result<LoginResponse> {
        return try {
            val response = apiService.getUsuarioPorId(id, "Bearer $token")
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
}