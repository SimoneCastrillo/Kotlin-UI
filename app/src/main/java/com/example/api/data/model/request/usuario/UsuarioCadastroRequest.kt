package com.example.api.data.model.request.usuario

data class UsuarioCadastroRequest(
    val nome: String,
    val email: String,
    val senha: String,
    val telefone: String
)
