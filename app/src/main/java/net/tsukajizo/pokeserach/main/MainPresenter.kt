package net.tsukajizo.pokeserach.main

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import net.tsukajizo.pokeserach.data.api.PokeRepository
import net.tsukajizo.pokeserach.data.pokemon.Pokemon
import net.tsukajizo.pokeserach.data.pokemon.Sprites

class MainPresenter(val mainView: MainContract.View, val repository: PokeRepository) :
    MainContract.Presenter {

    init {
        mainView.setPresenter(this)
    }

    override fun start() {
        // nothing to do
    }

    var disposable: Disposable? = null;

    override fun searchPokemon(pokemonId: Int) {
        disposable?.dispose()
        disposable = repository.searchPokemon(pokemonId)
            .observeOn(AndroidSchedulers.mainThread())
            .onErrorReturn {
                mainView.showAlertErrorSearch(pokemonId)
                Pokemon.getUnknownPokemon()
            }
            .subscribe({ response ->
                mainView.showSearchedPokemon(response)
            })

    }

    override fun searchPokemon(pokemonName: String) {
        disposable?.dispose()
        repository.searchPokemon(pokemonName)
            .observeOn(AndroidSchedulers.mainThread())
            .onErrorReturn {
                mainView.showAlertErrorSearch(pokemonName)
                Pokemon.getUnknownPokemon()
            }
            .subscribe({ response ->
                mainView.showSearchedPokemon(response)
            })
    }


}