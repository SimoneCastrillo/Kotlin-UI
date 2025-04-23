package com.example.api.data.repository.orcamento

import com.example.api.data.model.request.orcamento.OrcamentoRequest
import com.example.api.data.model.response.orcamento.OrcamentoResponse
import com.example.api.data.network.ApiClient.orcamentoApiService
import com.example.api.data.network.api_services.orcamento.OrcamentoApiService
import okio.IOException

class OrcamentoRepository() {

    suspend fun buscarOrcamentos(id: Int, token: String): Result<List<OrcamentoResponse>> {
        return try {
            val response = orcamentoApiService.getOrcamentosPorUsuario(id, "Bearer $token")
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
            val response = orcamentoApiService.getOrcamentoPorId(id, "Bearer $token")
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

    suspend fun cadastrarOrcamento(request: OrcamentoRequest, token: String): Result<OrcamentoResponse> {
        return try {
            val response = orcamentoApiService.cadastrarOrcamento(request, "Bearer $token")
            if (response.isSuccessful) {
                response.body()?.let {
                    Result.success(it)
                } ?: Result.failure(Exception("Resposta vazia"))
            } else {
                Result.failure(Exception("Erro ao cadastrar: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}