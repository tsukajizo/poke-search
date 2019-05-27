package net.tsukajizo.pokeserach.data.pokemon

import com.google.gson.annotations.SerializedName

data class PokemonList(
    @SerializedName("count")  val count:Int,
    @SerializedName("results")  val results:List<PokemonInfo>
)