package com.example.api.ui.theme.telas.cadastro

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.platform.LocalConfiguration
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
fun Cadastro(name: String, modifier: Modifier = Modifier, navController: NavController) {
    var nome by remember { mutableStateOf("") }
    var telefone by remember { mutableStateOf("") }

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
                .padding(start = 64.dp, top = 64.dp, end = 64.dp, bottom = 64.dp)
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
                        value = nome,
                        onValueChange = { nome = it },
                        label = { Text("Insira seu nome") },
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
                        label = { Text("(00) 91234-5678") },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFFD9D9D9),
                            unfocusedBorderColor = Color(0xFFD9D9D9)
                        )
                    )
                }

                Spacer(modifier = Modifier.height(31.dp))

                Box(modifier = Modifier
                    .width(305.dp)
                    .height(20.dp)
                    .padding(start = 5.dp, end = 5.dp)
                ){
                    Row(
                        modifier = Modifier.
                        fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically

                    ) {

                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .size(20.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFC54477)) // Cor rosa
                        ) {
                            Text(
                                text = "1",
                                color = Color.White,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Box(
                            modifier = Modifier
                                .padding(0.dp)
                                .width(20.dp)
                                .height(2.dp)
                                .background(Color(0xFFFFB6C1)),
                        )

                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .border(width = 2.dp, color = Color(0xFFFFB6C1), shape = CircleShape)
                                .size(20.dp)
                                .clip(CircleShape)
                        ) {
                            Text(
                                text = "2",
                                color = Color(0xFFFFB6C1),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(31.dp))

                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                ){
                    Button(
                        onClick = {
                            navController.navigate("cadastro-2")
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
                            text = "Avançar",
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight(800),
                                color = Color.White,
                                textAlign = TextAlign.Center,
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(122.dp))

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
fun GreetingPreview() {
    val navController = rememberNavController()

    APITheme {


        Cadastro("Android", navController = navController)
    }
}
