package com.example.api.data.model.response.usuario

data class UsuarioCadastroResponse(
    val id: Int,
    val nome: String,
    val email: String,
    val senha: String,
    val telefone: String,
    val role: String,
    val foto: String?
)
