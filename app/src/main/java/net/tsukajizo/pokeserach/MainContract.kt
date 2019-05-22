package net.tsukajizo.pokeserach

import net.tsukajizo.pokeserach.data.api.Pokemon

interface MainContract {

    interface View : BaseView<Presenter> {
        fun showSearchedPokemon(pokemon:Pokemon)

        fun showAlertErrorSearch(name :String)

        fun showAlertErrorSearch(id:Int)
    }

    interface Presenter : BasePresenter {
        fun searchPokemon(pokemonId:Int)

        fun searchPokemon(pokemonName:String)

    }
}