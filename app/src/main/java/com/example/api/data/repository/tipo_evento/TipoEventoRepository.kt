package com.example.api.data.repository.tipo_evento

import com.example.api.data.model.response.tipo_evento.TipoEventoResponse
import com.example.api.data.network.ApiClient.tipoEventoApiService

class TipoEventoRepository() {
    suspend fun listar(): List<TipoEventoResponse> = tipoEventoApiService.getTiposEvento()
}