package com.example.api.data.network.api_services.orcamento

import com.example.api.data.model.request.orcamento.OrcamentoRequest
import com.example.api.data.model.response.orcamento.OrcamentoResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface OrcamentoApiService {
    @GET("orcamentos/usuario/{id}")
    suspend fun getOrcamentosPorUsuario(
        @Path("id") id: Int,
        @Header("Authorization") token: String
    ): Response<List<OrcamentoResponse>>

    @GET("orcamentos/{id}")
    suspend fun getOrcamentoPorId(
        @Path("id") id: Int,
        @Header("Authorization") token: String
    ): Response<OrcamentoResponse>

    @POST("orcamentos")
    suspend fun cadastrarOrcamento(
        @Body orcamento: OrcamentoRequest,
        @Header("Authorization") token: String
    ): Response<OrcamentoResponse>

    @PUT("/orcamentos/{id}")
    suspend fun atualizarOrcamento(
        @Path("id") id: Int,
        @Body request: OrcamentoRequest,
        @Header("Authorization") token: String
    ): Response<OrcamentoResponse>
}