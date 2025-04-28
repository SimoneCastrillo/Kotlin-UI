package com.example.api.ui.theme.telas.orcamento

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.api.data.model.request.orcamento.OrcamentoRequest
import com.example.api.data.model.response.decoracao.DecoracaoResponse
import com.example.api.data.model.response.orcamento.OrcamentoResponse
import com.example.api.data.repository.decoracao.DecoracaoRepository
import com.example.api.data.repository.orcamento.OrcamentoRepository
import com.example.api.data.session.SessaoUsuario
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class Orcamento2ViewModel() : ViewModel() {
    private val decoracaoRepository = DecoracaoRepository()
    private val orcamentoRepository = OrcamentoRepository()

    var erroMsg by mutableStateOf<List<String>>(emptyList()) // Mudando para lista de strings

    var sucessoMsg by mutableStateOf<String?>(null)
        private set

    var isLoading by mutableStateOf(false)
        private set

    var dataEvento: String = ""
    var horario: String = ""
    var qtdConvidados: Int = 0
    var tipoEventoId: Int = 0
    var sugestao: String? = null
    var decoracaoId: Int? = null
    val usuarioId = SessaoUsuario.usuarioId ?: 0


    private val _decoracoes = MutableStateFlow<List<DecoracaoResponse>>(emptyList())
    val decoracoes: StateFlow<List<DecoracaoResponse>> = _decoracoes

    private val _decoracaoSelecionadaId = MutableStateFlow<Int?>(null)
    val decoracaoSelecionadaId: StateFlow<Int?> = _decoracaoSelecionadaId

    fun selecionarDecoracao(id: Int?) {
        _decoracaoSelecionadaId.value = id
    }

    fun buscarDecoracoesPorTipoEvento(tipoEventoId: Int) {
        viewModelScope.launch {

            try {
                val response = decoracaoRepository.listarPorTipo(tipoEventoId)
                _decoracoes.value = response
            } catch (e: Exception) {
                _decoracoes.value = emptyList()
            }
        }
    }

    fun cadastrarOrcamento(
        request: OrcamentoRequest,
        navController: NavController
    ) {
        if (validarCampos(request)) {
            viewModelScope.launch {
                isLoading = true
                erroMsg = emptyList()
                sucessoMsg = null

                val token = SessaoUsuario.token!!
                val result = orcamentoRepository.cadastrarOrcamento(request, token)

                result.onSuccess { response ->
                    sucessoMsg = "Orçamento criado com sucesso!"
                    val id = response.id
                    navController.navigate("visualizacao-evento/$id/$token")
                }.onFailure { exception ->
                    erroMsg = listOf("Erro ao cadastrar o orçamento: ${exception.message}")
                }

                isLoading = false
            }
        }
    }


    fun validarCampos(request: OrcamentoRequest): Boolean {
        val erros = mutableListOf<String>()

        val dataEvento = LocalDate.parse(request.dataEvento, DateTimeFormatter.ISO_DATE)

        if (dataEvento.isBefore(LocalDate.now())) {
            erros.add("A data do evento deve ser no futuro.")
        }

        if (request.qtdConvidados > 180) {
            erros.add("A quantidade de convidados deve ser no máximo 180.")
        }

        if (request.inicio == null) {
            erros.add("O horário de início é obrigatório.")
        }

        if (erros.isNotEmpty()) {
            erroMsg = erros
            return false
        }

        return true
    }

    fun setDadosOrcamento(
        data: String,
        horario: String,
        quantidade: Int,
        tipoEventoId: Int,
        sugestao: String?,
        decoracaoId: Int?
    ) {
        this.dataEvento = data
        this.horario = horario
        this.qtdConvidados = quantidade
        this.tipoEventoId = tipoEventoId
        this.sugestao = sugestao
        this.decoracaoId = decoracaoId
    }
}