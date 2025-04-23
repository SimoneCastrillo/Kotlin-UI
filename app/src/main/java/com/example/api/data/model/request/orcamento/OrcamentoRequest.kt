package com.example.api.data.model.request.orcamento

data class OrcamentoRequest(
    val dataEvento: String, // formato ISO "yyyy-MM-dd"
    val qtdConvidados: Int,
    val inicio: String,
    val sugestao: String?,
    val tipoEventoId: Int,
    val usuarioId: Int,
    val decoracaoId: Int?
)
