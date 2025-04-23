package com.example.api.ui.theme.telas.orcamento

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api.data.model.request.orcamento.OrcamentoRequest
import com.example.api.data.model.response.decoracao.DecoracaoResponse
import com.example.api.data.model.response.orcamento.OrcamentoResponse
import com.example.api.data.model.response.tipo_evento.TipoEventoResponse
import com.example.api.data.repository.decoracao.DecoracaoRepository
import com.example.api.data.repository.orcamento.OrcamentoRepository
import com.example.api.data.repository.tipo_evento.TipoEventoRepository
import com.example.api.data.repository.usuario.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class OrcamentoViewModel : ViewModel() {

    private val tipoEventoRepo = TipoEventoRepository()
    private val decoracaoRepo = DecoracaoRepository()
    private val repo = OrcamentoRepository()

    init {
        carregarTiposEvento() // Carregar os eventos assim que o ViewModel for criado
    }

    private val _tiposEvento = MutableStateFlow<List<TipoEventoResponse>>(emptyList())
    val tiposEvento: StateFlow<List<TipoEventoResponse>> = _tiposEvento

    private val _decoracoes = MutableStateFlow<List<DecoracaoResponse>>(emptyList())
    val decoracoes: StateFlow<List<DecoracaoResponse>> = _decoracoes

    private val _decoracaoSelecionadaId = MutableStateFlow<Int?>(null)
    val decoracaoSelecionadaId: StateFlow<Int?> = _decoracaoSelecionadaId

    // Campos do orçamento
    var dataEvento: String = ""
    var horario: String = ""
    var qtdConvidados: Int = 0
    var tipoEventoId: Int = 0
    var sugestao: String? = null
    var decoracaoId: Int? = null
    var usuarioId: Int = 1

    fun selecionarDecoracao(id: Int) {
        _decoracaoSelecionadaId.value = id
    }

    fun carregarTiposEvento() {
        viewModelScope.launch {
            try {
                val tipos = tipoEventoRepo.listar()  // Aqui você busca os dados do repositório
                Log.d("OrcamentoTeste", "Tipos de evento carregados: $tipos")
                _tiposEvento.value = tipos // Atualiza o StateFlow com os dados carregados
                Log.d("OrcamentoErro", "Tipos de evento carregados: ${_tiposEvento.value}")
            } catch (e: Exception) {
                Log.e("OrcamentoViewModel", "Erro ao carregar tipos de evento", e)
            }
        }
    }

    fun buscarDecoracoesPorTipoEvento(tipoEventoId: Int) {
        Log.e("Orcamento2ErroEvento", "Tipo de evento recebido na tela 2: $tipoEventoId")
        viewModelScope.launch {
            try {
                val response = decoracaoRepo.listarPorTipo(tipoEventoId)
                _decoracoes.value = response
            } catch (e: Exception) {
                // Tratar erro ao buscar decorações
                println("Erro ao buscar decorações: ${e.localizedMessage}")
                _decoracoes.value = emptyList()
            }
        }
    }


}
