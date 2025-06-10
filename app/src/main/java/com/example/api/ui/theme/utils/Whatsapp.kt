package com.example.api.ui.theme.utils

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast


fun abrirWhatsApp(context: Context, numero: String, mensagem: String) {
    val url = "https://api.whatsapp.com/send?phone=$numero&text=${Uri.encode(mensagem)}"
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

    try {
        context.startActivity(intent)
    } catch (e: ActivityNotFoundException) {
        Toast.makeText(context, "Não foi possível abrir o WhatsApp", Toast.LENGTH_SHORT).show()
    }
}

fun gerarMensagemWhatsApp(
    nomeUsuario: String,
    dataEvento: String,
    horario: String,
    qtdConvidados: Int,
    tipoEventoNome: String?,
    decoracaoNome: String?,
    sugestao: String?
): String {
    return """
        Olá, meu nome é $nomeUsuario e gostaria de saber os valores para o orçamento:

        Data do evento: $dataEvento
        Quantidade de pessoas: $qtdConvidados
        Horário de início: $horario
        Tipo de evento: ${tipoEventoNome ?: "N/A"}
        Decoração: ${decoracaoNome ?: "N/A"}
        Observação: ${sugestao ?: "N/A"}
    """.trimIndent()
}


