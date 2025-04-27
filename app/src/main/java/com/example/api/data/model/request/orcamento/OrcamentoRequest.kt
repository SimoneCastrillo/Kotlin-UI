package com.example.api.data.model.request.orcamento

data class OrcamentoRequest(
    val dataEvento: String,
    val qtdConvidados: Int,
    val inicio: String,
    val sugestao: String?,
    val tipoEventoId: Int,
    val usuarioId: Int,
    val decoracaoId: Int?
)
