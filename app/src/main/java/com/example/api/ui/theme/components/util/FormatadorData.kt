package com.example.api.ui.theme.components.util

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun formatarData(data: String): String {
    val formatterEntrada = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val formatterSaida = DateTimeFormatter.ofPattern("dd/MM/yyy")

    return LocalDate.parse(data, formatterEntrada).format(formatterSaida)
}