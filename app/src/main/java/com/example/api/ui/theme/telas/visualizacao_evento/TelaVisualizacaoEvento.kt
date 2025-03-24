package com.example.api.ui.theme.telas.visualizacao_evento

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.api.R
import com.example.api.ui.theme.APITheme
import com.example.api.ui.theme.telas.redefinicao_senha.RedefinirSenha3

@Composable
fun TelaVisualizacaoEvento(name: String, modifier: Modifier = Modifier, navController: NavController) {

    Column(
        modifier = Modifier
            .width(412.dp)
            .height(138.dp)
            .background(color = Color(0xFFC54477), shape = RoundedCornerShape(0.dp))
            .padding(start = 80.dp, top = 32.dp, end = 80.dp, bottom = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Box(
            modifier = Modifier
                .width(92.dp)
                .height(36.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_branco),
                contentDescription = "Descrição da Imagem",
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Fit
            )
        }
    }

    Column(
        modifier = Modifier
            .width(412.dp)
            .padding(start = 55.dp, top = 160.dp, end = 55.dp, bottom = 64.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            modifier = Modifier
                .width(190.dp)
                .height(58.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.Top),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Aniversário",
                style = TextStyle(
                    fontSize = 32.sp,
                    fontWeight = FontWeight(700),
                    color = Color(0xFFC54477),
                )
            )

            Text(
                text = "22/03/2025 às 18h00",
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight(700),
                    color = Color(0xFF1E1E1E),
                )
            )
        }


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(35.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(35.dp)
                    .clip(CircleShape)
                    .border(1.dp, color = Color(0xFFC54477), shape = CircleShape)
                    .background(color = Color(0xFFC54477)
                )
            ){
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(21.dp)
                ){
                    Image(
                        painter = painterResource(R.drawable.whatsapp_logo),
                        contentDescription = "Descrição",
                        modifier = Modifier.
                        fillMaxSize(),
                        contentScale = ContentScale.Fit
                    )
                }
            }

            Box(
                modifier = Modifier
                    .width(25.dp)
                    .height(4.dp)
                    .background(color = Color(0xFFD9D9D9))
            )

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(35.dp)
                    .clip(CircleShape)
                    .background(color = Color(0xFFD9D9D9)
                )
            ){
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(21.dp)
                ){
                    Image(
                        painter = painterResource(R.drawable.clock),
                        contentDescription = "Descrição",
                        modifier = Modifier.
                        fillMaxSize(),
                        contentScale = ContentScale.Fit
                    )
                }
            }

            Box(
                modifier = Modifier
                    .width(25.dp)
                    .height(4.dp)
                    .background(color = Color(0xFFD9D9D9))
            )

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(35.dp)
                    .clip(CircleShape)
                    .background(color = Color(0xFFD9D9D9)
                    )
            ){
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(21.dp)
                ){
                    Image(
                        painter = painterResource(R.drawable.agendamento_aprovado),
                        contentDescription = "Descrição",
                        modifier = Modifier.
                        fillMaxSize(),
                        contentScale = ContentScale.Fit
                    )
                }
            }

            Box(
                modifier = Modifier
                    .width(25.dp)
                    .height(4.dp)
                    .background(color = Color(0xFFD9D9D9))
            )

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(35.dp)
                    .clip(CircleShape)
                    .background(color = Color(0xFFD9D9D9)
                    )
            ){
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(21.dp)
                ){
                    Image(
                        painter = painterResource(R.drawable.note),
                        contentDescription = "Descrição",
                        modifier = Modifier.
                        fillMaxSize(),
                        contentScale = ContentScale.Fit
                    )
                }
            }

            Box(
                modifier = Modifier
                    .width(25.dp)
                    .height(4.dp)
                    .background(color = Color(0xFFD9D9D9))
            )

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(35.dp)
                    .clip(CircleShape)
                    .background(color = Color(0xFFD9D9D9)
                    )
            ){
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(21.dp)
                ){
                    Image(
                        painter = painterResource(R.drawable.confetti),
                        contentDescription = "Descrição",
                        modifier = Modifier.
                        fillMaxSize(),
                        contentScale = ContentScale.Fit
                    )
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .width(412.dp)
            .padding(start = 64.dp, top = 300.dp, end = 64.dp, bottom = 12.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            modifier = Modifier
                .width(305.dp)
                .height(303.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Column(
                modifier = Modifier
                    .width(305.dp)
                    .height(58.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    text = "Decoração",
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight(600),
                        color = Color(0xFF000000),
                        textAlign = TextAlign.Center,
                    )
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(47.dp, Alignment.End),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .border(1.dp, color = Color(0xFFD9D9D9), shape = RoundedCornerShape(size = 8.dp))
                        .width(305.dp)
                        .height(35.dp)
                        .background(color = Color(0x33D9D9D9), shape = RoundedCornerShape(size = 8.dp))
                        .padding(start = 16.dp, top = 3.dp, end = 16.dp, bottom = 3.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .width(273.dp)
                            .height(15.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = "Infantil com Balões",
                            style = TextStyle(
                                fontSize = 12.sp,
                                fontWeight = FontWeight(400),
                                color = Color(0xFF000000),
                                textAlign = TextAlign.Center,
                            )
                        )

                        Box(
                            modifier = Modifier
                                .size(14.dp)
                        ) {
                            Image(
                                modifier = Modifier
                                    .fillMaxSize(),
                                painter = painterResource(id = R.drawable.caret_down),
                                contentDescription = "image description",
                                contentScale = ContentScale.None
                            )
                        }


                    }
                }
            }

            Column(
                modifier = Modifier
                    .width(305.dp)
                    .height(58.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    text = "Sabor do bolo",
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight(600),
                        color = Color(0xFF000000),
                        textAlign = TextAlign.Center,
                    )
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(47.dp, Alignment.End),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .border(1.dp, color = Color(0xFFD9D9D9), shape = RoundedCornerShape(size = 8.dp))
                        .width(305.dp)
                        .height(35.dp)
                        .background(color = Color(0x33D9D9D9), shape = RoundedCornerShape(size = 8.dp))
                        .padding(start = 16.dp, top = 3.dp, end = 16.dp, bottom = 3.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .width(273.dp)
                            .height(15.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = "Floresta Negra",
                            style = TextStyle(
                                fontSize = 12.sp,
                                fontWeight = FontWeight(400),
                                color = Color(0xFF000000),
                                textAlign = TextAlign.Center,
                            )
                        )
                    }
                }
            }

            Column(
                modifier = Modifier
                    .width(305.dp)
                    .height(58.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    text = "Quantidade de convidados",
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight(600),
                        color = Color(0xFF000000),
                        textAlign = TextAlign.Center,
                    )
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(47.dp, Alignment.End),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .border(1.dp, color = Color(0xFFD9D9D9), shape = RoundedCornerShape(size = 8.dp))
                        .width(305.dp)
                        .height(35.dp)
                        .background(color = Color(0x33D9D9D9), shape = RoundedCornerShape(size = 8.dp))
                        .padding(start = 16.dp, top = 3.dp, end = 16.dp, bottom = 3.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .width(273.dp)
                            .height(15.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = "140",
                            style = TextStyle(
                                fontSize = 12.sp,
                                fontWeight = FontWeight(400),
                                color = Color(0xFF000000),
                                textAlign = TextAlign.Center,
                            )
                        )
                    }
                }
            }

            Column(
                modifier = Modifier
                    .width(305.dp)
                    .height(58.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    text = "Valor total",
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight(600),
                        color = Color(0xFF000000),
                        textAlign = TextAlign.Center,
                    )
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(47.dp, Alignment.End),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .border(1.dp, color = Color(0xFFD9D9D9), shape = RoundedCornerShape(size = 8.dp))
                        .width(305.dp)
                        .height(35.dp)
                        .background(color = Color(0x33D9D9D9), shape = RoundedCornerShape(size = 8.dp))
                        .padding(start = 16.dp, top = 3.dp, end = 16.dp, bottom = 3.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .width(273.dp)
                            .height(15.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = "R$ 2.500,00",
                            style = TextStyle(
                                fontSize = 12.sp,
                                fontWeight = FontWeight(400),
                                color = Color(0xFF000000),
                                textAlign = TextAlign.Center,
                            )
                        )
                    }
                }
            }
        }

        Box( modifier = Modifier
            .width(154.dp)
            .height(34.65.dp)
            .background(color = Color(0xFFC54477), shape = RoundedCornerShape(size = 8.dp)),
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    Color.Transparent,
                )
            ) {
                Text(
                    text = "Salvar",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight(800),
                        color = Color(0xFFFFFFFF),
                        textAlign = TextAlign.Center,
                    )
                )
            }
        }

        Spacer(Modifier.height(19.dp))

        Row(
            modifier = Modifier
                .padding(top = 30.dp)
                .width(166.dp)
                .height(60.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ){
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(42.dp)
                    .clip(CircleShape)
                    .clickable {
                        navController.navigate("pagina-inicial")
                    }
                    .border(1.dp, color = Color(0xFFC54477), shape = CircleShape)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(21.dp)
                ){
                    Image(
                        painter = painterResource(id = R.drawable.notificacao),
                        contentDescription = "Descrição da Imagem",
                        modifier = Modifier
                            .fillMaxSize(),
                        contentScale = ContentScale.Fit
                    )
                }
            }

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
                    .border(1.dp, color = Color(0xFFC54477), shape = CircleShape)
                    .background(color = Color(0xFFC54477))
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(34.dp)
                        .background(color = Color(0xFFC54477))
                ){
                    Image(
                        painter = painterResource(id = R.drawable.plus),
                        contentDescription = "Descrição da Imagem",
                        modifier = Modifier
                            .fillMaxSize(),
                        contentScale = ContentScale.Fit
                    )
                }
            }

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(42.dp)
                    .clip(CircleShape)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.user),
                    contentDescription = "Descrição da Imagem",
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.Fit
                )
            }
        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewRedefinirSenha3() {
    val navController = rememberNavController()

    APITheme {
        TelaVisualizacaoEvento("Android", navController = navController)
    }
}