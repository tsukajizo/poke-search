package net.tsukajizo.pokeserach.data.pokemon

import com.google.gson.annotations.SerializedName

data class PokemonInfo(
    @SerializedName("name") val name:String,
    //url: https://pokeapi.co/api/v2/pokemon/{pokemonId}/"
    @SerializedName("url") val url:String
)