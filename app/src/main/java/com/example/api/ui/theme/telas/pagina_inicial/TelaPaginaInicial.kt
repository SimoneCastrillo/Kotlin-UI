package com.example.api.ui.theme.telas.pagina_inicial

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.ui.text.TextStyle
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.api.R
import com.example.api.ui.theme.APITheme

@Composable
fun TelaPaginaIncial(name: String, modifier: Modifier = Modifier){
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
            .padding(start = 42.dp, top = 183.dp, end = 42.dp, bottom = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column(modifier = Modifier
            .width(151.dp)
            .height(39.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Reservas",
                style = androidx.compose.ui.text.TextStyle(
                    fontSize = 32.sp,
                    fontWeight = FontWeight(700),
                    color = Color(0xFFC54477)
                )
            )
        }

        Spacer(modifier = Modifier.height(31.dp))

        Column(modifier = Modifier
            .width(305.dp)
            .height(600.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            
            Row(modifier = Modifier
                .border(1.dp, color = Color(0xFFD9D9D9), shape = RoundedCornerShape(size = 8.dp))
                .width(305.dp)
                .height(35.dp)
                .background(color = Color(0x33D9D9D9), shape = RoundedCornerShape(size = 8.dp))
                .padding(start = 16.dp, top = 3.dp, end = 16.dp, bottom = 3.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Image(
                        painter = painterResource(id = R.drawable.pendente),
                        contentDescription = "Descrição da Imagem",
                        modifier = Modifier
                            .padding(0.125.dp)
                            .width(17.dp)
                            .height(17.dp),
                        contentScale = ContentScale.Fit
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "Aniversário",
                        style = TextStyle(
                            fontSize = 10.sp,
                            fontWeight = FontWeight(600),
                        )
                    )
                }

                Box(
                    Modifier
                        .width(86.dp)
                        .height(12.dp)
                ) {
                    Text(
                        text = "Data: 22/03/2025",
                        style = TextStyle(
                            fontSize = 10.sp,
                            fontWeight = FontWeight(600),
                            color = Color(0xFF000000),

                            textAlign = TextAlign.Center,
                        )
                    )
                }
            }

            Row(modifier = Modifier
                .border(1.dp, color = Color(0xFFD9D9D9), shape = RoundedCornerShape(size = 8.dp))
                .width(305.dp)
                .height(35.dp)
                .background(color = Color(0x33D9D9D9), shape = RoundedCornerShape(size = 8.dp))
                .padding(start = 16.dp, top = 3.dp, end = 16.dp, bottom = 3.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Image(
                        painter = painterResource(id = R.drawable.pendente),
                        contentDescription = "Descrição da Imagem",
                        modifier = Modifier
                            .padding(0.125.dp)
                            .width(17.dp)
                            .height(17.dp),
                        contentScale = ContentScale.Fit
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "Coffee Break",
                        style = TextStyle(
                            fontSize = 10.sp,
                            fontWeight = FontWeight(600),
                        )
                    )
                }

                Box(
                    Modifier
                        .width(86.dp)
                        .height(12.dp)
                ) {
                    Text(
                        text = "Data: 28/05/2025",
                        style = TextStyle(
                            fontSize = 10.sp,
                            fontWeight = FontWeight(600),
                            color = Color(0xFF000000),

                            textAlign = TextAlign.Center,
                        )
                    )
                }
            }

            Row(modifier = Modifier
                .border(1.dp, color = Color(0xFFD9D9D9), shape = RoundedCornerShape(size = 8.dp))
                .width(305.dp)
                .height(35.dp)
                .background(color = Color(0x33D9D9D9), shape = RoundedCornerShape(size = 8.dp))
                .padding(start = 16.dp, top = 3.dp, end = 16.dp, bottom = 3.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Image(
                        painter = painterResource(id = R.drawable.cancelado),
                        contentDescription = "Descrição da Imagem",
                        modifier = Modifier
                            .padding(0.125.dp)
                            .width(17.dp)
                            .height(17.dp),
                        contentScale = ContentScale.Fit
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "Coffee Break",
                        style = TextStyle(
                            fontSize = 10.sp,
                            fontWeight = FontWeight(600),
                        )
                    )
                }

                Box(
                    Modifier
                        .width(86.dp)
                        .height(12.dp)
                ) {
                    Text(
                        text = "Data: 22/03/2025",
                        style = TextStyle(
                            fontSize = 10.sp,
                            fontWeight = FontWeight(600),
                            color = Color(0xFF000000),

                            textAlign = TextAlign.Center,
                        )
                    )
                }
            }

            Spacer(Modifier.height(335.dp))

            Row(
                modifier = Modifier
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
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewTelaPaginaInicial(){
    APITheme {
        TelaPaginaIncial("Android")
    }
}
