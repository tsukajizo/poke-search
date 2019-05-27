package net.tsukajizo.pokeserach.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import net.tsukajizo.pokeserach.R
import net.tsukajizo.pokeserach.data.api.PokeApi
import net.tsukajizo.pokeserach.data.api.PokeRepository
import net.tsukajizo.pokeserach.data.pokemon.Pokemon
import net.tsukajizo.pokeserach.util.GlideImageLoader
import net.tsukajizo.pokeserach.util.NumUtil
import net.tsukajizo.pokeserach.util.Validator
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
        search.addTextChangedListener( object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                error.text = ""
                clearPokemon()
                val input = s.toString()
                if(input.isBlank()){
                    error.text = ""
                }else if(!Validator.validateInput(input)){
                  showAlertInputError()
                } else if(NumUtil.isNum(input)){
                    presenter.searchPokemon(Integer.parseInt(input))
                }else{
                    presenter.searchPokemon(input)
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })
    }

    override fun onStart() {
        super.onStart()
        presenter.start()
    }

    private fun clearPokemon(){
        showPokemon(Pokemon.getUnknownPokemon())
    }


    override fun showPokemon(pokemon: Pokemon) {
        pokeId.text = if(!Pokemon.isUnknown(pokemon)) pokemon.id.toString() else "???"
        pokeName.text =  if(!Pokemon.isUnknown(pokemon)) pokemon.name else "???"
        if(!Pokemon.isUnknown(pokemon)) {
            error.text = ""
            val loader = GlideImageLoader()
            loader.load(pokeImage, pokemon.sprites.frontDefault)
        }else{
            pokeImage.setImageBitmap(null)
        }
    }

    override fun showAlertErrorSearch(name: String) {
        error.text = String.format(getString(R.string.error_name_not_found),name)
        showPokemon(Pokemon.getUnknownPokemon())
    }

    override fun showAlertErrorSearch(id: Int) {
        error.text = String.format(getString(R.string.error_id_not_found),id)
        showPokemon(Pokemon.getUnknownPokemon())
    }

    private fun showAlertInputError(){
        error.text = getString(R.string.error_input_incorrect_string)
    }

    override fun setPresenter(presenter: MainContract.Presenter) {
        this.presenter = presenter
    }
}
