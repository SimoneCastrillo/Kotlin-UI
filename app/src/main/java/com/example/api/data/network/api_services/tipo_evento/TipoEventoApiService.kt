package com.example.api.data.network.api_services.tipo_evento

import com.example.api.data.model.response.tipo_evento.TipoEventoResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TipoEventoApiService {
    @GET("api/tipos-evento")
    suspend fun getTiposEvento(
        @Query("buffetId") buffetId: Int = 1
    ): List<TipoEventoResponse>

    @GET("api/tipos-evento/{id}")
    suspend fun getTipoEventoPorId(@Path("id") id: Int): TipoEventoResponse
}