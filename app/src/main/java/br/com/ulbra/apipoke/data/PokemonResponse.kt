package br.com.ulbra.apipoke.data

import br.com.ulbra.apipoke.model.Pokemon
import com.google.gson.annotations.SerializedName

data class PokemonListResponse(
    @SerializedName("results") val results: List<PokemonItemResponse>
)

data class PokemonItemResponse(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)

fun PokemonItemResponse.toPokemon(): Pokemon {
    val id = url.split("/").filter { it.isNotEmpty() }.last().toInt()
    val imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"
    return Pokemon(
        id = id,
        nome = name.replaceFirstChar { it.uppercase() },
        imagemUrl = imageUrl
    )
}
