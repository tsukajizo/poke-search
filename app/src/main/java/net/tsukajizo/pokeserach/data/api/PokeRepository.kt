package net.tsukajizo.pokeserach.data.api

import io.reactivex.Single
import net.tsukajizo.pokeserach.data.pokemon.Pokemon
import net.tsukajizo.pokeserach.data.pokemon.PokemonList

class PokeRepository (val api:PokeApi){
    fun searchPokemon(id:Int):Single<Pokemon>{
         return api.fetchPokemon(id)
    }

    fun searchPokemon(name:String):Single<Pokemon>{
        return api.fetchPokemon(name)
    }

    fun fetchPokemonList() :Single<PokemonList>{
        return api.fetchPokemonList()
    }
}