package br.com.ulbra.apipoke.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokemonRepository {
    private val api: PokemonApi = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(PokemonApi::class.java)

    suspend fun getPokemons() = api.getPokemons().results.map { it.toPokemon() }
}
