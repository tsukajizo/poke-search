package net.tsukajizo.pokeserach.util

import java.util.regex.Pattern

class Validator{
    companion object {
        const val INPUT_PATTERN = "^[a-zA-Z0-9]+$"

        fun validateInput(text:String):Boolean{
            val pattern = Pattern.compile(INPUT_PATTERN)
            return pattern.matcher(text).matches()
        }
    }
}