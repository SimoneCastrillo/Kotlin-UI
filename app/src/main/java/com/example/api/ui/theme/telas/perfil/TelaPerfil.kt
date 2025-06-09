package com.example.api.ui.theme.telas.perfil

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.api.R
import com.example.api.data.model.request.usuario.UsuarioUpdateRequest
import com.example.api.ui.theme.APITheme
import com.example.api.ui.theme.components.MenuIcones
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody

fun uriToMultipart(uri: Uri, context: Context): MultipartBody.Part? {
    val contentResolver = context.contentResolver
    val inputStream = contentResolver.openInputStream(uri) ?: return null
    val bytes = inputStream.readBytes()
    val requestFile = bytes.toRequestBody("image/*".toMediaTypeOrNull())
    return MultipartBody.Part.createFormData("foto", "foto.jpg", requestFile)
}

@Composable
fun PerfilScreen(navController: NavController, id: Int, token: String) {
    val viewModel: PerfilViewModel = viewModel()
    val usuario by viewModel.usuario.collectAsState()
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    var nome by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var celular by remember { mutableStateOf("") }
    var fotoPreviewUri by remember { mutableStateOf<Uri?>(null) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            fotoPreviewUri = it
        }
    }

    LaunchedEffect(key1 = id) {
        viewModel.carregarPerfil(id, token)
    }

    LaunchedEffect(usuario) {
        val user = usuario
        if (user != null) {
            nome = user.nome
            email = user.email
            celular = user.telefone
            fotoPreviewUri = user.foto?.let { uri -> Uri.parse(uri) }
        }
    }

    val qtdOrcamentos = usuario?.qtdOrcamento?.toString() ?: "0"

    val erroMsg = viewModel.erroMsg
    val sucessoMsg = viewModel.sucessoMsg

    BoxWithConstraints {
        val maxWidthSize = maxWidth

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Topo rosa com logo
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(if (maxWidthSize < 600.dp) 100.dp else 138.dp)
                    .background(color = Color(0xFFC54477)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo_branco),
                    contentDescription = "Logo",
                    modifier = Modifier.size(if (maxWidthSize < 600.dp) 68.dp else 92.dp),
                    contentScale = ContentScale.Fit
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            // Foto de perfil
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .border(2.dp, Color.White, shape = CircleShape)
                    .clickable { launcher.launch("image/*") },
                contentAlignment = Alignment.Center
            ) {
                val fotoUrl = usuario?.foto ?: "avatar_default"
                val base64Image = usuario?.foto ?: ""
                val imageBytes = android.util.Base64.decode(base64Image, android.util.Base64.DEFAULT)

                val imageBitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)

                if (fotoUrl != "avatar_default") {
                    Image(
                        bitmap = imageBitmap.asImageBitmap(),
                        contentDescription = "Foto de Perfil",
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(CircleShape) // Garante que a imagem seja cortada no formato circular
                            .border(2.dp, Color.White, shape = CircleShape),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.avatar), // Substitua pelo ID correto do avatar padrão
                        contentDescription = "Avatar Padrão",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = nome,
                onValueChange = { nome = it },
                label = { Text(stringResource(R.string.nome)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFFC54477),
                    unfocusedBorderColor = Color.Gray
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text(stringResource(R.string.email)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFFC54477),
                    unfocusedBorderColor = Color.Gray
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = celular,
                onValueChange = { celular = it },
                label = { Text(stringResource(R.string.celular)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFFC54477),
                    unfocusedBorderColor = Color.Gray
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = qtdOrcamentos,
                onValueChange = {},
                label = { Text(stringResource(R.string.quantidade_orcamento)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFFC54477),
                    unfocusedBorderColor = Color.Gray
                ),
                readOnly = true
            )

            // Mensagens
            erroMsg?.let {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = it,
                    color = Color.Red,
                    style = TextStyle(fontSize = 14.sp)
                )
            }

            sucessoMsg?.let {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = it,
                    color = Color(0xFF2E7D32),
                    style = TextStyle(fontSize = 14.sp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    coroutineScope.launch {
                        val fotoPart = fotoPreviewUri?.let { uriToMultipart(it, context) }

                        val request = UsuarioUpdateRequest(
                            nome = nome,
                            email = email,
                            telefone = celular,
                            foto = null  // Vamos ignorar a foto aqui, já que estamos passando via Multipart
                        )

                        Log.d("Base64", "Foto Base64: ${request.foto}")

                        viewModel.salvarPerfil(id, request, token, fotoPart)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFC54477),
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Salvar", style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold))
            }

            Spacer(modifier = Modifier.height(16.dp))

            MenuIcones(navController, id, token)
        }
    }
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TelaPerfil() {
    val navController = rememberNavController()
    APITheme {
        PerfilScreen(navController = navController, id = 37, token = "")
    }
}