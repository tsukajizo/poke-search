package net.tsukajizo.pokeserach.data.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeApi {

    @GET("pokemon/{name}")
    fun fetchPokemon(@Path("name") name:String):Single<Pokemon>

    @GET("pokemon/{id}")
    fun fetchPokemon(@Path("id") id:Int):Single<Pokemon>
}