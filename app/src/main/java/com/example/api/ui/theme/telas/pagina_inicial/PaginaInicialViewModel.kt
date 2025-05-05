package com.example.api.ui.theme.telas.pagina_inicial

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api.R
import com.example.api.data.model.response.orcamento.OrcamentoResponse
import com.example.api.data.network.ApiClient
import com.example.api.data.repository.orcamento.OrcamentoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PaginaInicialViewModel(
    private val repository: OrcamentoRepository = OrcamentoRepository()
) : ViewModel() {

    private val _orcamentos = MutableStateFlow<List<OrcamentoResponse>>(emptyList())
    val orcamentos: StateFlow<List<OrcamentoResponse>> = _orcamentos

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _erroMsg = MutableStateFlow<String?>(null)
    val erroMsg: StateFlow<String?> = _erroMsg

    private val _mensagemResId = MutableStateFlow<Int?>(null)
    val mensagemResId: StateFlow<Int?> = _mensagemResId


    fun carregarOrcamentos(idUsuario: Int, token: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _erroMsg.value = null
            _orcamentos.value = emptyList()

            val result = repository.buscarOrcamentos(idUsuario, token)
            result.onSuccess {
                if (it.isEmpty()) {
                    _mensagemResId.value = R.string.solicite_ja_um_orcamento
                } else {
                    _orcamentos.value = it
                }
            }.onFailure {
                _mensagemResId.value = R.string.erro_rede
            }

            _isLoading.value = false
        }
    }
}
