package net.tsukajizo.pokeserach

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import net.tsukajizo.pokeserach.data.api.PokeApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
            .client(OkHttpClient().newBuilder().build())
            .build()

        val api:PokeApi = retrofit.create(PokeApi::class.java)
        api.fetchPokemon(100)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
            })
    }
}
