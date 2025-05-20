import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
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
import androidx.compose.runtime.collectAsState
import com.example.api.ui.theme.telas.orcamento.OrcamentoViewModel
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Orcamento(modifier: Modifier = Modifier, navController: NavController) {
    val viewModel = OrcamentoViewModel()
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val tiposEvento by viewModel.tiposEvento.collectAsState(emptyList())

    var evento by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    var data by remember { mutableStateOf("Selecione a Data") }
    var horario by remember { mutableStateOf("Selecione o Horário") }
    var quantidade by remember { mutableStateOf("140") }

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.carregarTiposEvento()
    }

    Column {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(138.dp)
                .background(color = Color(0xFFC54477), shape = RoundedCornerShape(0.dp))
                .padding(start = 16.dp, top = 32.dp, end = 16.dp, bottom = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .width((screenWidth * 0.25f))
                    .height(36.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo_branco),
                    contentDescription = "Descrição da Imagem",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Fit
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Orçamento",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFC54477)
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = "Selecione o horário e data da sua reserva e a quantidade de pessoas.",
                style = TextStyle(
                    fontSize = 14.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = data,
                onValueChange = {},
                label = { Text("Data") },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        showDatePickerDialog(context) { selectedDate -> data = selectedDate }
                    },
                readOnly = true,
                enabled = false,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    disabledTextColor = Color.Black
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = horario,
                onValueChange = {},
                label = { Text("Horário") },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        showTimePickerDialog(context) { selectedTime -> horario = selectedTime }
                    },
                readOnly = true,
                enabled = false,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    disabledTextColor = Color.Black
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            Box {
                OutlinedTextField(
                    value = evento,
                    onValueChange = {},
                    label = { Text("Tipo de Evento") },
                    readOnly = true,
                    modifier = Modifier.fillMaxWidth(),
                    trailingIcon = {
                        Icon(
                            Icons.Filled.ArrowDropDown, contentDescription = null,
                            modifier = Modifier.clickable { expanded = !expanded })
                    }
                )

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    if (tiposEvento.isNotEmpty()) {
                        tiposEvento.forEach { tipo ->
                            DropdownMenuItem(
                                onClick = {
                                    evento = tipo.nome
                                    expanded = false
                                },
                                text = { Text(text = tipo.nome) }
                            )
                        }
                    } else {
                        DropdownMenuItem(
                            onClick = {},
                            enabled = false,
                            text = { Text(text = "Nenhum evento disponível") }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = quantidade,
                onValueChange = { quantidade = it },
                label = { Text("Quantidade de Convidados") },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Gray,
                    focusedBorderColor = Color(0xFFD9D9D9),
                    unfocusedBorderColor = Color(0xFFD9D9D9),
                    disabledBorderColor = Color.Gray,
                    errorBorderColor = Color.Red
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    val url = "tela-orcamento2/$evento/$data/$horario/$quantidade"
                    navController.navigate(url)
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFC54477),
                    contentColor = Color.White
                )
            ) {
                Text(text = "Avançar", style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold))
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Cancelar",
                style = TextStyle(
                    fontSize = 14.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier.clickable { /* Implementar ação de cancelar */ }
            )
        }
    }
}

fun showDatePickerDialog(context: Context, onDateSelected: (String) -> Unit) {
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    val datePickerDialog = DatePickerDialog(context, { _, year, monthOfYear, dayOfMonth ->
        val selectedDate = String.format("%04d-%02d-%02d", year, monthOfYear + 1, dayOfMonth)
        onDateSelected(selectedDate)
    }, year, month, day)

    datePickerDialog.show()
}

fun showTimePickerDialog(context: Context, onTimeSelected: (String) -> Unit) {
    val calendar = Calendar.getInstance()
    val hour = calendar.get(Calendar.HOUR_OF_DAY)
    val minute = calendar.get(Calendar.MINUTE)

    val timePickerDialog = TimePickerDialog(context, { _, hourOfDay, minuteOfHour ->
        val selectedTime = String.format("%02d:%02d:00", hourOfDay, minuteOfHour)
        onTimeSelected(selectedTime)
    }, hour, minute, true)

    timePickerDialog.show()
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Orcamento() {
    val navController = rememberNavController()
    APITheme {
        Orcamento(navController = navController)
    }
}