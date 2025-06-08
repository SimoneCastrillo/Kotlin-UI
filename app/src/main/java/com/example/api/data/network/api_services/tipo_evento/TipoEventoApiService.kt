package com.example.api.data.network.api_services.tipo_evento

import com.example.api.data.model.response.tipo_evento.TipoEventoResponse
import retrofit2.http.GET

interface TipoEventoApiService {
    @GET("api/tipos-evento")
    suspend fun getTiposEvento(): List<TipoEventoResponse>
}