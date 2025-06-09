package com.example.api.ui.theme.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast


fun abrirWhatsApp(context: Context, numero: String, mensagem: String) {
    val uri = Uri.parse("https://wa.me/$numero?text=${Uri.encode(mensagem)}")
    val intent = Intent(Intent.ACTION_VIEW, uri)
    intent.setPackage("com.whatsapp")

    if (intent.resolveActivity(context.packageManager) != null) {
        context.startActivity(intent)
    } else {
        Toast.makeText(context, "WhatsApp não instalado", Toast.LENGTH_SHORT).show()
    }
}
