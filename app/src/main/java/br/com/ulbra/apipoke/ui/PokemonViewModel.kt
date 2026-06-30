package br.com.ulbra.apipoke.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.ulbra.apipoke.data.PokemonRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PokemonViewModel : ViewModel() {
    private val repository = PokemonRepository()

    private val _uiState = MutableStateFlow(PokemonUiState())
    val uiState: StateFlow<PokemonUiState> = _uiState.asStateFlow()

    fun fetchPokemons() {
        viewModelScope.launch {
            _uiState.update { it.copy(carregando = true, erro = null) }
            try {
                val pokemons = repository.getPokemons()
                _uiState.update { it.copy(lista = pokemons, listaFiltrada = pokemons, carregando = false) }
            } catch (e: Exception) {
                _uiState.update { it.copy(
                    carregando = false,
                    erro = "Erro ao carregar Pokémons: ${e.localizedMessage}"
                ) }
            }
        }
    }

    fun onPesquisaAlterada(novaPesquisa: String) {
        _uiState.update { state ->
            val filtrados = if (novaPesquisa.isEmpty()) {
                state.lista
            } else {
                state.lista.filter { it.nome.contains(novaPesquisa, ignoreCase = true) }
            }
            state.copy(pesquisa = novaPesquisa, listaFiltrada = filtrados)
        }
    }
}
