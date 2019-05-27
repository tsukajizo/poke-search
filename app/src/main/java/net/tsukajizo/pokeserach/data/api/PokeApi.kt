package net.tsukajizo.pokeserach.data.api

import io.reactivex.Single
import net.tsukajizo.pokeserach.data.pokemon.Pokemon
import net.tsukajizo.pokeserach.data.pokemon.PokemonList
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeApi {

    @GET("pokemon/?offset=0&limit=964")
    fun fetchPokemonList():Single<PokemonList>

    @GET("pokemon/{name}")
    fun fetchPokemon(@Path("name") name:String):Single<Pokemon>

    @GET("pokemon/{id}")
    fun fetchPokemon(@Path("id") id:Int):Single<Pokemon>
}