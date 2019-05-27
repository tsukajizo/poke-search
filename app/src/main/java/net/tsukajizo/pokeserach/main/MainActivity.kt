package net.tsukajizo.pokeserach.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import net.tsukajizo.pokeserach.R
import net.tsukajizo.pokeserach.data.api.PokeApi
import net.tsukajizo.pokeserach.data.api.PokeRepository
import net.tsukajizo.pokeserach.data.pokemon.Pokemon
import net.tsukajizo.pokeserach.util.GlideImageLoader
import net.tsukajizo.pokeserach.util.NumUtil
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() , MainContract.View {

    private lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
            .client(OkHttpClient().newBuilder().build())
            .build()

        presenter =
            MainPresenter(this, PokeRepository(retrofit.create(PokeApi::class.java)))
        search.setOnQueryTextListener(object : android.support.v7.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(text: String?): Boolean {
                if(text == null){
                    return false
                }
                if(NumUtil.isNum(text)){
                    presenter.searchPokemon(Integer.parseInt(text))
                }else{
                    presenter.searchPokemon(text)
                }
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                // nothing to do
                return false;
            }

        })
    }

    override fun showSearchedPokemon(pokemon: Pokemon) {
        Log.d("MainActivity","Pokemon:" + pokemon)
        pokeId.text = pokemon.id.toString()
        pokeName.text = pokemon.name
        val loader = GlideImageLoader()
        loader.load(pokeImage,pokemon.sprites.frontDefault)
    }

    override fun showAlertErrorSearch(name: String) {
        Toast.makeText(this,name + " is not found",Toast.LENGTH_SHORT).show()
    }

    override fun showAlertErrorSearch(id: Int) {
        Toast.makeText(this,"id:" + id + " is not found",Toast.LENGTH_SHORT).show()
    }

    override fun setPresenter(presenter: MainContract.Presenter) {
        this.presenter = presenter
    }
}
