package com.example.api.data.model.response.orcamento

data class OrcamentoResponse(
    val id: Int,
    val dataEvento: String,
    val qtdConvidados: Int,
    val status: String,
    val cancelado: Boolean,
    val inicio: String,
    val fim: String,
    val saborBolo: String?,
    val pratoPrincipal: String?,
    val lucro: Double?,
    val faturamento: Double?,
    val despesa: Double?,
    val sugestao: String?,
    val usuario: Usuario,
    val tipoEvento: TipoEvento,
    val decoracao: Decoracao?,
    val buffet: Buffet,
   val endereco: Endereco
)

data class TipoEvento(
    val id: Int,
    val nome: String
)

data class Usuario(
    val id: Int,
    val nome: String,
    val email: String,
    val telefone: String
)

data class Decoracao(
    val id: Int,
    val nome: String,
    val foto: String
)

data class Buffet(
    val id: Int,
    val nome: String
)

data class Endereco(
    val id: Int,
    val rua: String,
    val numero: String,
    val bairro: String,
    val cidade: String,
    val estado: String,
    val cep: String
)

