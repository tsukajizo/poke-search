package net.tsukajizo.pokeserach

import android.app.Application
import com.google.gson.Gson
import net.tsukajizo.pokeserach.data.api.PokeApi
import net.tsukajizo.pokeserach.data.api.PokeRepository
import okhttp3.OkHttpClient
import org.koin.android.ext.android.startKoin
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class PokeSearchApplication : Application() {

    companion object {
        const val POKE_API_URL = "https://pokeapi.co/api/v2/"
    }

    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf(koinModule))
    }


    private val koinModule = module {
        single {
            Retrofit.Builder()
                .baseUrl(POKE_API_URL)
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
                .client(OkHttpClient().newBuilder().build())
                .build()
        }

        factory {
            (get() as Retrofit).create(PokeApi::class.java)
        }

        factory {
            PokeRepository(get())
        }
    }
}