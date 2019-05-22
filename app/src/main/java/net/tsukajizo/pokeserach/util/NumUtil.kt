package net.tsukajizo.pokeserach.util

class NumUtil{
    companion object {
        fun isNum(text:String?):Boolean{
            return try {
                Integer.parseInt(text)
                true
            }catch (e:NumberFormatException){
                false
            }
        }
    }
}