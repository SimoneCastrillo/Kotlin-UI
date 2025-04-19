package com.example.api.data.network

import com.example.api.data.model.orcamento.OrcamentoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface OrcamentoApiService {
    @GET("orcamentos/usuario/{id}")
    suspend fun getOrcamentosPorUsuario(
        @Path("id") id: Int,
        @Header("Authorization") token: String
    ): Response<List<OrcamentoResponse>>
}