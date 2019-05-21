package net.tsukajizo.pokeserach.data.api

import com.google.gson.annotations.SerializedName

data class Pokemon(
    @SerializedName("id") val id:Int,
    @SerializedName("name") val name:String,
    @SerializedName("height") val height:Int,
    @SerializedName("is_default") val isDefault:Boolean,
    @SerializedName("order") val order:Int,
    @SerializedName("weight") val weight:Int
)