package net.tsukajizo.pokeserach.main

import net.tsukajizo.pokeserach.BasePresenter
import net.tsukajizo.pokeserach.BaseView
import net.tsukajizo.pokeserach.data.pokemon.Pokemon

interface MainContract {

    interface View : BaseView<Presenter> {
        fun showPokemon(pokemon: Pokemon)

        fun showAlertErrorSearch(name :String)

        fun showAlertErrorSearch(id:Int)
    }

    interface Presenter : BasePresenter {
        fun searchPokemon(pokemonId:Int)

        fun searchPokemon(pokemonName:String)

        fun dispose()
    }
}