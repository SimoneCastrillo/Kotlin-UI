package com.example.api.ui.theme.telas.cadastro

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.api.R
import com.example.api.ui.theme.APITheme

@Composable
fun Cadastro2(name: String, modifier: Modifier = Modifier, navController: NavController) {
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var emailConfirmacao by remember { mutableStateOf("") }
    var senhaConfirmacao by remember { mutableStateOf("") }

    Column {
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
                .padding(start = 64.dp, top = 32.dp, end = 64.dp, bottom = 64.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {

                Box {

                    Box(
                        modifier = Modifier
                            .matchParentSize()
                            .padding(top = 8.dp)
                            .background(Color(0x33D9D9D9))
                    )

                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Insira seu email") },
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
                        value = emailConfirmacao,
                        onValueChange = { emailConfirmacao = it },
                        label = { Text("Confirme seu email") },
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
                        value = senha,
                        onValueChange = { senha = it },
                        label = { Text("Insira sua senha") },
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
                        value = senhaConfirmacao,
                        onValueChange = { senhaConfirmacao = it },
                        label = { Text("Confirme sua senha") },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFFD9D9D9),
                            unfocusedBorderColor = Color(0xFFD9D9D9)
                        )
                    )
                }

                Spacer(modifier = Modifier.height(31.dp))

                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                ){
                    Button(
                        onClick = {
                            navController.navigate("login")
                        },
                        colors = ButtonDefaults.buttonColors(
                            Color.Transparent,
                        ),
                        modifier = Modifier
                            .width(412.dp)
                            .height(40.dp)
                            .background(color = Color(0xFFC54477), shape = RoundedCornerShape(size = 16.dp))
                    ){
                        Text(
                            text = "Cadastrar",
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight(800),
                                color = Color.White,
                                textAlign = TextAlign.Center,
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(25.dp))

                Box(
                    modifier = Modifier
                        .width(284.dp)
                        .height(13.dp)
                ){
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable{
                                navController.navigate("login")
                            },
                        text = buildAnnotatedString {
                            append("Já possui uma conta? ")
                            withStyle(style = SpanStyle(color = Color(0xFFC54477))) {
                                append("Login")
                            }
                        },
                        style = TextStyle(
                            fontSize = 11.sp,
                            fontWeight = FontWeight(400),
                            color = Color(0xFF000000),
                            textAlign = TextAlign.Center,
                        )
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreviewCadastro2() {
    val navController = rememberNavController()

    APITheme {
        Cadastro2("Android", navController = navController)
    }
}