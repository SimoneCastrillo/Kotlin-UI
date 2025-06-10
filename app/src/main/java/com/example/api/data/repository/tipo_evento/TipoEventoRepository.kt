package com.example.api.data.repository.tipo_evento

import com.example.api.data.model.response.decoracao.DecoracaoResponse
import com.example.api.data.model.response.tipo_evento.TipoEventoResponse
import com.example.api.data.network.ApiClient.decoracaoApiService
import com.example.api.data.network.ApiClient.tipoEventoApiService

class TipoEventoRepository() {
    suspend fun listar(): List<TipoEventoResponse> = tipoEventoApiService.getTiposEvento()

    suspend fun buscarPorId(id: Int): TipoEventoResponse {
        return tipoEventoApiService.getTipoEventoPorId(id)
    }
}