package com.example.api.data.network.api_services.decoracao

import com.example.api.data.model.response.decoracao.DecoracaoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DecoracaoApiService {
    @GET("api/decoracoes/tipo-de-evento")
    suspend fun getDecoracoesPorTipo(@Query("tipoEventoId") tipoEventoId: Int): List<DecoracaoResponse>?

    @GET("api/decoracoes/{id}")
    suspend fun getDecoracaoPorId(@Path("id") id: Int): DecoracaoResponse
}