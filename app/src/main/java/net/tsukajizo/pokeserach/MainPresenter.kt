package net.tsukajizo.pokeserach

import io.reactivex.android.schedulers.AndroidSchedulers
import net.tsukajizo.pokeserach.data.api.PokeRepository
import net.tsukajizo.pokeserach.data.api.Pokemon

class MainPresenter(val mainView: MainContract.View, val repository: PokeRepository) : MainContract.Presenter {

    init {
        mainView.setPresenter(this)
    }

    override fun start() {
        // nothing to do
    }

    override fun searchPokemon(pokemonId: Int) {
       repository.searchPokemon(pokemonId)
           .observeOn(AndroidSchedulers.mainThread())
           .onErrorReturn {
               mainView.showAlertErrorSearch(pokemonId)
               Pokemon(0,"",0,false,0,0)
           }
           .subscribe({ response ->
               mainView.showSearchedPokemon(response)
           })

    }

    override fun searchPokemon(pokemonName: String) {
        repository.searchPokemon(pokemonName)
            .observeOn(AndroidSchedulers.mainThread())
            .onErrorReturn {
                mainView.showAlertErrorSearch(pokemonName)
                Pokemon(0,"",0,false,0,0)
            }
            .subscribe({ response ->
                mainView.showSearchedPokemon(response)
            })
    }


}