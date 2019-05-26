package net.tsukajizo.pokeserach.util

import android.widget.ImageView
import com.bumptech.glide.Glide

class GlideImageLoader {

    fun load(view: ImageView,url:String) {
        Glide.with(view).load(url).into(view)
    }
}