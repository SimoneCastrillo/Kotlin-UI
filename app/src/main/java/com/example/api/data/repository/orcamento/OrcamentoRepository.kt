package com.example.api.data.repository.orcamento

import com.example.api.data.model.response.orcamento.OrcamentoResponse
import com.example.api.data.network.api_services.orcamento.OrcamentoApiService
import okio.IOException

class OrcamentoRepository(private val apiService: OrcamentoApiService) {

    suspend fun buscarOrcamentos(id: Int, token: String): Result<List<OrcamentoResponse>> {
        return try {
            val response = apiService.getOrcamentosPorUsuario(id, "Bearer $token")
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

    suspend fun getOrcamento(id: Int, token: String): Result<OrcamentoResponse> {
        return try {
            val response = apiService.getOrcamentoPorId(id, "Bearer $token")
            if (response.isSuccessful) {
                response.body()?.let {
                    Result.success(it)
                } ?: Result.failure(Exception())
            } else {
                Result.failure(Exception())
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}