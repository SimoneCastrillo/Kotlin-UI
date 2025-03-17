package com.example.api.ui.theme.telas.redefinicao_senha

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.api.R
import com.example.api.ui.theme.APITheme

@Composable
fun RedefinirSenha2(name: String, modifier: Modifier = Modifier) {
    var email by remember { mutableStateOf("") }

    Column(modifier = modifier) {
        BoxWithConstraints(
            modifier = Modifier
                .width(412.dp)
                .height(307.dp)
                .background(color = Color(0xFFC54477), shape = RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp, bottomStart = 0.dp, bottomEnd = 0.dp))
                .padding(start = 80.dp, top = 63.dp, end = 80.dp, bottom = 63.dp)
        ) {
            val boxWidth = maxWidth
            val boxHeight = maxHeight

            Image(
                painter = painterResource(id = R.drawable.logo_branco),
                contentDescription = "Descrição da Imagem",
                modifier = Modifier
                    .width(boxWidth)
                    .height(boxHeight),
                contentScale = ContentScale.Fit
            )
        }

        Box(
            modifier = Modifier
                .width(412.dp)
                .height(543.dp)
                .padding(start = 64.dp, top = 64.dp, end = 64.dp, bottom = 64.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Um e-mail com o código de recuperação foi enviado para a**z@g*.com." +
                            " Por favor, verifique sua caixa de entrada (e também a pasta de spam ou lixo eletrônico, se necessário) para encontrar o e-mail.",
                    style = TextStyle(
                        fontSize = 11.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF1E1E1E),
                        textAlign = TextAlign.Center,
                    ),
                    modifier = Modifier
                        .width(306.dp)
                        .height(52.dp)
                        .padding(bottom = 16.dp)
                )

                EmailFieldsRow(email, onEmailChange = { email = it })

                Spacer(modifier = Modifier.height(46.dp))

                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(Color.Transparent),
                    modifier = Modifier
                        .width(245.dp)
                        .height(40.dp)
                        .background(color = Color(0xFFC54477), shape = RoundedCornerShape(16.dp))
                ) {
                    Text(
                        text = "Verificar",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight(800),
                            color = Color.White,
                            textAlign = TextAlign.Center,
                        )
                    )
                }

                Spacer(modifier = Modifier.height(85.dp))

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Reenviar código | 2:35",
                    style = TextStyle(
                        fontSize = 11.sp,
                        fontWeight = FontWeight(400),
                        textDecoration = TextDecoration.Underline,
                        color = Color(0xFFC54477),
                        textAlign = TextAlign.Center,
                    )
                )

                Spacer(modifier = Modifier.height(85.dp))

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = buildAnnotatedString {
                        append("Lembrou a senha? ")
                        withStyle(style = SpanStyle(color = Color(0xFFC54477))) { // Rosa para a palavra "Voltar"
                            append("Voltar")
                        }
                    },
                    style = TextStyle(
                        fontSize = 11.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF000000), // Cor preta para o restante do texto
                        textAlign = TextAlign.Center
                    )
                )

            }
        }
    }
}

@Composable
fun EmailFieldsRow(email: String, onEmailChange: (String) -> Unit) {
    Row(
        modifier = Modifier
            .width(305.dp)
            .height(55.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        repeat(6) {
            EmailInputField(
                modifier = Modifier.weight(1f),
                value = email,
                onValueChange = onEmailChange
            )
        }
    }
}

@Composable
fun EmailInputField(modifier: Modifier = Modifier, value: String, onValueChange: (String) -> Unit) {
    Box(
        modifier = modifier
            .height(55.dp)
            .background(Color(0x33D9D9D9))
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxSize(),
            value = value,
            onValueChange = onValueChange,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFFD9D9D9),
                unfocusedBorderColor = Color(0xFFD9D9D9)
            )
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewRedefinirSenha2() {
    APITheme {
        RedefinirSenha2("Android")
    }
}
