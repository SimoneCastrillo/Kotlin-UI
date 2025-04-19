package com.example.api.data.model.login

data class LoginResponse(
    val id: Int,
    val nome: String,
    val email: String,
    val telefone: String,
    val qtdOrcamento: Int,
    val role: String,
    val token: String,
    val foto: String?
)
