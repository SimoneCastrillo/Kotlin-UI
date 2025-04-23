package com.example.api.data.repository.decoracao

import com.example.api.data.model.response.decoracao.DecoracaoResponse
import com.example.api.data.network.ApiClient.decoracaoApiService

class DecoracaoRepository {
    suspend fun listarPorTipo(tipoEventoId: Int): List<DecoracaoResponse> {
        val response = decoracaoApiService.getDecoracoesPorTipo(tipoEventoId)
        if (response.isNullOrEmpty()) {
            return emptyList()
        }

        return response
    }
}