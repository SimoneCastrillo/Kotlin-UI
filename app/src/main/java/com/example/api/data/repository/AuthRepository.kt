package com.example.api.data.repository

import com.example.api.data.model.LoginRequest
import com.example.api.data.model.LoginResponse
import com.example.api.data.network.ApiClient
import com.example.api.data.network.ApiClient.apiService
import okio.IOException

class AuthRepository {
    suspend fun login(email: String, senha: String): Result<LoginResponse> {
        return try {
            val response = apiService.login(LoginRequest(email, senha))

            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    Result.success(body)
                } else {
                    // Caso o corpo da resposta seja nulo, falha genérica
                    Result.failure(Exception("Erro desconhecido"))
                }
            } else {
                // Trata erros 401 (Unauthorized) de forma específica
                if (response.code() == 401) {
                    Result.failure(Exception("invalid_login"))  // Login inválido
                } else {
                    // Outros erros de status HTTP
                    Result.failure(Exception("Erro ao autenticar: ${response.message()}"))
                }
            }
        } catch (e: IOException) {
            // Erro de rede
            Result.failure(Exception("network_error"))
        } catch (e: Exception) {
            // Qualquer outro erro inesperado
            Result.failure(e)
        }
    }
}