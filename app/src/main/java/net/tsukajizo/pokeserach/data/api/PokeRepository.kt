package net.tsukajizo.pokeserach.data.api

import io.reactivex.Single
import net.tsukajizo.pokeserach.data.pokemon.Pokemon

class PokeRepository (val api:PokeApi){
    fun searchPokemon(id:Int):Single<Pokemon>{
        return api.fetchPokemon(id)
    }

    fun searchPokemon(name:String):Single<Pokemon>{
        return api.fetchPokemon(name)
    }
}