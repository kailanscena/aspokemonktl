package br.com.ulbra.apipoke.ui

import br.com.ulbra.apipoke.model.Pokemon

data class PokemonUiState(
    val lista: List<Pokemon> = emptyList(),
    val carregando: Boolean = false,
    val erro: String? = null
)
