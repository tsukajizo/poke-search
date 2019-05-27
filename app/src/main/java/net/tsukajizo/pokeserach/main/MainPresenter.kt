package net.tsukajizo.pokeserach.main

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import net.tsukajizo.pokeserach.data.api.PokeRepository
import net.tsukajizo.pokeserach.data.pokemon.Pokemon
import net.tsukajizo.pokeserach.data.pokemon.PokemonInfo

class MainPresenter(val mainView: MainContract.View, val repository: PokeRepository) :
    MainContract.Presenter {

    init {
        mainView.setPresenter(this)
    }

    var pokemonCount:Int = 0
    var pokemons:List<PokemonInfo>? = null
    var disposable: Disposable? = null;

    override fun start() {
        repository.fetchPokemonList()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                pokemonCount = response.count
                pokemons = response.results
            })
    }



    override fun searchPokemon(pokemonId: Int) {
        disposable?.dispose()
        if(pokemons == null){
            return
        }
        if(pokemons?.filter { pokemon -> pokemon.url.contains(pokemonId.toString())}!!.isEmpty()){
            mainView.showAlertErrorSearch(pokemonId)
            return
        }

        disposable = repository.searchPokemon(pokemonId)
            .observeOn(AndroidSchedulers.mainThread())
            .onErrorReturn {
                mainView.showAlertErrorSearch(pokemonId)
                Pokemon.getUnknownPokemon()
            }
            .subscribe({ response ->
                mainView.showPokemon(response)
            })

    }

    override fun searchPokemon(pokemonName: String) {
        disposable?.dispose()
        if(pokemons == null){
            return
        }
        if(pokemons?.filter { pokemon -> pokemon.name == pokemonName}!!.isEmpty()){
            mainView.showAlertErrorSearch(pokemonName)
            return
        }


        repository.searchPokemon(pokemonName)
            .observeOn(AndroidSchedulers.mainThread())
            .onErrorReturn {
                mainView.showAlertErrorSearch(pokemonName)
                Pokemon.getUnknownPokemon()
            }
            .subscribe({ response ->
                mainView.showPokemon(response)
            })
    }

    override fun dispose() {
        disposable?.dispose()
    }


}