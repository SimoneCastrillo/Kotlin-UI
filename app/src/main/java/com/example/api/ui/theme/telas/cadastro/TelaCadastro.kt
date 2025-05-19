package com.example.api.ui.theme.telas.cadastro

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.api.R
import com.example.api.ui.theme.APITheme
import com.example.api.ui.theme.components.TelaComTextoLogin

@Composable
fun Cadastro(name: String, modifier: Modifier = Modifier, navController: NavController) {
    var nome by remember { mutableStateOf("") }
    var telefone by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            // Header com imagem
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFC54477))
                    .padding(vertical = 64.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo_branco),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp),
                    contentScale = ContentScale.Fit
                )
            }

            Spacer(modifier = Modifier.height(64.dp))

            Column(
                modifier = Modifier
                    .width(412.dp)
                    .padding(start = 64.dp, end = 64.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Box {
                    Box(
                        modifier = Modifier
                            .matchParentSize()
                            .padding(top = 8.dp)
                            .background(Color(0x33D9D9D9))
                    )

                    OutlinedTextField(
                        value = nome,
                        onValueChange = { nome = it },
                        singleLine = true,
                        label = { Text(stringResource(R.string.insira_seu_nome)) },
                        modifier = Modifier
                            .fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFFD9D9D9),
                            unfocusedBorderColor = Color(0xFFD9D9D9)
                        )
                    )
                }

                Spacer(modifier = Modifier.height(28.dp))

                Box {
                    Box(
                        modifier = Modifier
                            .matchParentSize()
                            .padding(top = 8.dp)
                            .background(Color(0x33D9D9D9))
                    )

                    OutlinedTextField(
                        value = telefone,
                        onValueChange = { telefone = it },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        label = { Text(stringResource(R.string.insira_seu_telefone)) },
                        modifier = Modifier
                            .fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFFD9D9D9),
                            unfocusedBorderColor = Color(0xFFD9D9D9)
                        )
                    )
                }

                if (errorMessage.isNotEmpty()) {
                    Text(
                        text = errorMessage,
                        color = Color.Red,
                        fontSize = 14.sp,
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }

                Spacer(modifier = Modifier.height(46.dp))

                Button(
                    onClick = {
                        if (nome.isBlank() || telefone.isBlank()) {
                            errorMessage = "Por favor, preencha todos os campos."
                        } else {
                            errorMessage = ""
                            val encodedNome = Uri.encode(nome)
                            val encodedTelefone = Uri.encode(telefone)
                            navController.navigate("cadastro-2/$encodedNome/$encodedTelefone")
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC54477)),
                    modifier = Modifier
                        .width(412.dp)
                        .height(45.dp)
                        .background(
                            color = Color(0xFFC54477),
                            shape = RoundedCornerShape(size = 16.dp)
                        )
                        .padding(horizontal = 16.dp, vertical = 2.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(
                        text = stringResource(R.string.avancar),
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(64.dp))

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate("login")
                        },
                    text = buildAnnotatedString {
                        append(stringResource(id = R.string.ja_possui_conta))
                        withStyle(style = SpanStyle(color = Color(0xFFC54477))) {
                            append(stringResource(id = R.string.login))
                        }
                    },
                    style = TextStyle(
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color(0xFF000000),
                        textAlign = TextAlign.Center
                    )
                )
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun CadastroPreview() {
    val navController = rememberNavController()
    APITheme {
        Cadastro(name = "Preview", navController = navController)
    }
}
