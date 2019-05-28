package net.tsukajizo.pokeserach.data.api

import io.reactivex.Single
import net.tsukajizo.pokeserach.data.pokemon.Pokemon
import net.tsukajizo.pokeserach.data.pokemon.PokemonIndex

class PokeRepository (val api:PokeApi){
    fun searchPokemon(id:Int):Single<Pokemon>{
         return api.fetchPokemon(id)
    }

    fun searchPokemon(name:String):Single<Pokemon>{
        return api.fetchPokemon(name)
    }

    fun fetchPokemonList(): Single<PokemonIndex> {
        return api.fetchPokemonList()
    }
}