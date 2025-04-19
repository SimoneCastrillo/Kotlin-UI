package com.example.api.ui.theme.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.api.R

@Composable
fun MenuIcones(navController: NavController, id: Int, token: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        MenuIcon(iconRes = R.drawable.notificacao, description = "Notificações") {
            navController.navigate("pagina-inicial")
        }

        AddNewEventIcon(navController = navController)

        MenuIcon(iconRes = R.drawable.user, description = "Perfil") {
            navController.navigate("tela-perfil/$id/$token")
        }

    }
}

@Composable
fun MenuIcon(iconRes: Int, description: String, onClick: () -> Unit = {}) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(42.dp)
            .clip(CircleShape)
            .clickable(onClick = onClick)
            .border(1.dp, color = Color(0xFFC54477), shape = CircleShape)
            .padding(8.dp)
    ) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = description,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Fit
        )
    }
}

@Composable
fun AddNewEventIcon(navController: NavController) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(60.dp)
            .clip(CircleShape)
            .clickable {
                navController.navigate("tela-orcamento")
            }
            .background(color = Color(0xFFC54477))
            .border(1.dp, color = Color(0xFFC54477), shape = CircleShape)
            .padding(8.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.plus),
            contentDescription = "Adicionar novo evento",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Fit
        )
    }
}
