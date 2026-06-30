package br.com.ulbra.apipoke.data

import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonApi {
    @GET("pokemon")
    suspend fun getPokemons(
        @Query("limit") limit: Int = 151
    ): PokemonListResponse
}
