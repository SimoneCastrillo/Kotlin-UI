package com.example.api.ui.theme.telas.visualizacao_evento

import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.api.R
import com.example.api.data.session.SessaoUsuario
import com.example.api.ui.theme.APITheme
import com.example.api.ui.theme.components.util.LoadingDialog
import com.example.api.ui.theme.components.MenuIcones
import com.example.api.ui.theme.components.util.formatarData
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Date
import java.util.Locale
import java.util.TimeZone

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaVisualizacaoEvento(id: Int, token: String, modifier: Modifier = Modifier, navController: NavController) {
    val viewModel: VisualizacaoEventoViewModel = viewModel()
    val orcamento by viewModel.orcamento.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val erroMsg by viewModel.erroMsg.collectAsState()
    var saborBolo = orcamento?.saborBolo ?: "Nenhuma"
    var showDialog by remember { mutableStateOf(false) }

    val context = LocalContext.current

    val qtdConvidadosState = remember { mutableStateOf(orcamento?.qtdConvidados?.toString() ?: "") }
    val dataEventoState = remember { mutableStateOf(orcamento?.dataEvento?.let { LocalDate.parse(it) } ?: LocalDate.now()) }
    val decoracaoIdState = remember { mutableStateOf(orcamento?.decoracao?.id ?: 0) }


    LaunchedEffect(Unit) {
        viewModel.carregarOrcamento(id, token)
    }
    val dataFormatada = formatarData(orcamento?.dataEvento ?: "2000-01-01")
    val tipoEventoId = orcamento?.tipoEvento?.id ?: 0

    LaunchedEffect(tipoEventoId) {
        tipoEventoId.let {
            viewModel.buscarDecoracoesPorTipoEvento(it)
        }
    }
    val decoracoes by viewModel.decoracoes.collectAsState()
    val decoracaoSelecionadaId by viewModel.decoracaoSelecionadaId.collectAsState()

    val focusManager = LocalFocusManager.current
    var showDatePickerDialog by remember {
        mutableStateOf(false)
    }
    val datePickerState = rememberDatePickerState()
    var selectedDate by remember {
        mutableStateOf("")
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
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
                    .padding(start = 55.dp, top = 16.dp, end = 55.dp, bottom = 16.dp),
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
                    if (isLoading) {
                        LoadingDialog("Carregando informações...")
                    }

                    erroMsg?.let {
                        Text(
                            text = it,
                            style = TextStyle(
                                fontSize = 12.sp,
                                fontWeight = FontWeight(700),
                                color = Color(0xFF1E1E1E),
                            )
                        )
                    }

                    Text(
                        text = orcamento?.tipoEvento?.nome ?: "Nenhum",
                        style = TextStyle(
                            fontSize = 32.sp,
                            fontWeight = FontWeight(700),
                            color = Color(0xFFC54477),
                        )
                    )

                    Text(
                        text = "${dataFormatada} às ${orcamento?.inicio ?: "Horário"}",
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

//                    Box(
//                        modifier = Modifier
//                            .width(25.dp)
//                            .height(4.dp)
//                            .background(color = Color(0xFFD9D9D9))
//                    )

//                    Box(
//                        contentAlignment = Alignment.Center,
//                        modifier = Modifier
//                            .size(35.dp)
//                            .clip(CircleShape)
//                            .background(color = Color(0xFFD9D9D9)
//                            )
//                    ){
//                        Box(
//                            contentAlignment = Alignment.Center,
//                            modifier = Modifier
//                                .size(21.dp)
//                        ){
//                            Image(
//                                painter = painterResource(R.drawable.confetti),
//                                contentDescription = "Descrição",
//                                modifier = Modifier.
//                                fillMaxSize(),
//                                contentScale = ContentScale.Fit
//                            )
//                        }
//                    }
                }
            }


            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxWidth()
                    .padding(start = 64.dp, end = 64.dp, bottom = 12.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp, Alignment.Top),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()  // Garante que a coluna ocupue toda a largura
                        .padding(bottom = 30.dp),  // Espaço no final para o botão e Row
                    verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    CampoInformacao(
                        titulo = "Decoração",
                        valor = orcamento?.decoracao?.nome ?: "Nenhuma"
                    )

                    CampoInformacao(
                        titulo = "Data do evento",
                        valor = dataFormatada
                    )

                    CampoInformacao(
                        titulo = "Quantidade de convidados",
                        valor = "${orcamento?.qtdConvidados ?: 0}"
                    )

                    CampoInformacao(
                        titulo = "Início",
                        valor = orcamento?.inicio ?: "Sem horário"
                    )

                    CampoInformacao(
                        titulo = "Fim",
                        valor = orcamento?.fim ?: "Sem horário"
                    )

                    CampoInformacao(
                        titulo = "Sabor do bolo",
                        valor = orcamento?.saborBolo
                    )

                    CampoInformacao(
                        titulo = "Prato principal",
                        valor = orcamento?.pratoPrincipal
                    )

                    CampoInformacao(
                        titulo = "Sugestão",
                        valor = orcamento?.sugestao
                    )

                    CampoInformacao(
                        titulo = "Valor total",
                        valor = "R$ ${orcamento?.faturamento ?: "Valor em análise"}"
                    )
                }

//                Box(
//                    modifier = Modifier
//                        .width(154.dp)
//                        .height(34.65.dp)
//                        .background(color = Color(0xFFC54477), shape = RoundedCornerShape(size = 8.dp)),
//                    contentAlignment = Alignment.Center
//                ) {
//                    Button(
//                        onClick = {
//                        },
//                        colors = ButtonDefaults.buttonColors(
//                            Color.Transparent,
//                        )
//                    ) {
//                        Text(
//                            text = "Solicitar mudanças",
//                            style = TextStyle(
//                                fontSize = 16.sp,
//                                fontWeight = FontWeight(800),
//                                color = Color(0xFFFFFFFF),
//                                textAlign = TextAlign.Center,
//                            )
//                        )
//                    }
//                }

//                Spacer(Modifier.height(19.dp))

                Box {
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .fillMaxWidth()
                    ) {
                        SessaoUsuario.usuarioId?.let {
                            MenuIcones(
                                navController = navController,
                                id = it,
                                token = token
                            )
                        }
                    }
                }



//        Row(
//            modifier = Modifier
//                .padding(top = 30.dp)
//                .width(166.dp)
//                .height(60.dp),
//            horizontalArrangement = Arrangement.SpaceAround,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Box(
//                contentAlignment = Alignment.Center,
//                modifier = Modifier
//                    .size(42.dp)
//                    .clip(CircleShape)
//                    .clickable {
//                        navController.navigate("pagina-inicial")
//                    }
//                    .border(1.dp, color = Color(0xFFC54477), shape = CircleShape)
//            ) {
//                Box(
//                    contentAlignment = Alignment.Center,
//                    modifier = Modifier
//                        .size(21.dp)
//                ) {
//                    Image(
//                        painter = painterResource(id = R.drawable.notificacao),
//                        contentDescription = "Descrição da Imagem",
//                        modifier = Modifier
//                            .fillMaxSize(),
//                        contentScale = ContentScale.Fit
//                    )
//                }
//            }
//
//            Box(
//                contentAlignment = Alignment.Center,
//                modifier = Modifier
//                    .size(60.dp)
//                    .clip(CircleShape)
//                    .border(1.dp, color = Color(0xFFC54477), shape = CircleShape)
//                    .background(color = Color(0xFFC54477))
//            ) {
//                Box(
//                    contentAlignment = Alignment.Center,
//                    modifier = Modifier
//                        .size(34.dp)
//                        .background(color = Color(0xFFC54477))
//                ) {
//                    Image(
//                        painter = painterResource(id = R.drawable.plus),
//                        contentDescription = "Descrição da Imagem",
//                        modifier = Modifier
//                            .fillMaxSize(),
//                        contentScale = ContentScale.Fit
//                    )
//                }
//            }
//
//            Box(
//                contentAlignment = Alignment.Center,
//                modifier = Modifier
//                    .size(42.dp)
//                    .clip(CircleShape)
//            ) {
//                Image(
//                    painter = painterResource(id = R.drawable.user),
//                    contentDescription = "Descrição da Imagem",
//                    modifier = Modifier
//                        .fillMaxSize(),
//                    contentScale = ContentScale.Fit
//                )
//            }
//        }
            }

        }

    }

    if (showDialog) {
        Dialog(onDismissRequest = { showDialog = false }) {
            Box(
                modifier = Modifier
                    .size(300.dp)
                    .background(Color.White, shape = MaterialTheme.shapes.medium)
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Decorações",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFC54477),
                            textAlign = TextAlign.Center
                        )
                    )

                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.weight(1f)

                    ) {

                        if (decoracoes.isEmpty()) {
                            item(span = { GridItemSpan(maxLineSpan) }) {
                                Text(
                                    text = "Nenhuma decoração disponível para este tipo de evento.",
                                    color = Color.Gray,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    textAlign = TextAlign.Center,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }

                        items(decoracoes) { decoracao ->
                            val base64Image = decoracao.foto
                            val imageBytes = android.util.Base64.decode(base64Image, android.util.Base64.DEFAULT)

                            val imageBitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)

                            Column {
                                Text(
                                    text = decoracao.nome,
                                    style = TextStyle(
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFFC54477),
                                        textAlign = TextAlign.Center
                                    ),
                                    modifier = Modifier
                                        .padding(bottom = 8.dp, top = 8.dp)
                                )

                                Box(
                                    modifier = Modifier
                                        .aspectRatio(1f)
                                        .background(Color.LightGray)
                                        .clickable {
                                            if (decoracaoSelecionadaId == decoracao.id) {
                                                viewModel.selecionarDecoracao(null) // ← desmarca se já estiver selecionada
                                            } else {
                                                viewModel.selecionarDecoracao(decoracao.id)
                                            }
                                        }
                                ) {
                                    Image(
                                        bitmap = imageBitmap.asImageBitmap(),
                                        contentDescription = decoracao.nome,
                                        modifier = Modifier.fillMaxSize(),
                                        contentScale = ContentScale.Crop
                                    )

                                    if (decoracaoSelecionadaId == decoracao.id) {
                                        Box(
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .background(Color.Black.copy(alpha = 0.3f)),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Icon(Icons.Default.CheckCircle, contentDescription = "Selecionado", tint = Color.White, modifier = Modifier.size(36.dp))
                                        }
                                    }
                                }
                            }
//
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = { showDialog = false },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFC54477),
                            contentColor = Color.White
                        )
                    ) {
                        var textoBotao = "Selecionar"; if (decoracaoSelecionadaId == null) {
                        textoBotao = "Fechar"
                    }
                        Text(text = textoBotao)
                    }
                }
            }
        }
    }
}

fun Long.toBrazilianDateFormat(
    pattern: String = "dd/MM/yyyy"
): String {
    val date = Date(this)
    val formatter = SimpleDateFormat(
        pattern, Locale("pt-br")
    ).apply {
        timeZone = TimeZone.getTimeZone("GMT")
    }
    return formatter.format(date)
}

@Composable
fun CampoInformacao(
    titulo: String,
    valor: String?,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .width(305.dp)
            .height(58.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.Start,
    ) {
        Text(
            text = titulo,
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
                    text = valor ?: "Não disponível",
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


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewRedefinirSenha3() {
    val navController = rememberNavController()

    APITheme {
        TelaVisualizacaoEvento(30, "", navController = navController)
    }
}