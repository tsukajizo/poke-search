package net.tsukajizo.pokeserach.data.pokemon

import com.google.gson.annotations.SerializedName

data class Pokemon(
    @SerializedName("id") val id:Int,
    @SerializedName("name") val name:String,
    @SerializedName("height") val height:Int,
    @SerializedName("is_default") val isDefault:Boolean,
    @SerializedName("order") val order:Int,
    @SerializedName("weight") val weight:Int,
    @SerializedName("sprites") val sprites:Sprites
){
    companion object {
        const val UNKNOWN_POKEMON_ID = 0

        fun getUnknownPokemon():Pokemon{
            return Pokemon(UNKNOWN_POKEMON_ID,"???",0,false,0,0, Sprites("",""))
        }

        fun isUnknown(pokemon: Pokemon):Boolean{
            return pokemon.id == UNKNOWN_POKEMON_ID
        }
    }
}


