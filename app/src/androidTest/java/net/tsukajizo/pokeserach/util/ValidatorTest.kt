package net.tsukajizo.pokeserach.util

import android.support.test.runner.AndroidJUnit4
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ValidatorTest {
    @Test
    fun validateInputTesT() {
        assertTrue(Validator.validateInput("pikachu"))
        assertFalse(Validator.validateInput("pikachu\n"))
        assertTrue(Validator.validateInput("123"))
        assertTrue(Validator.validateInput("porygon2"))
        assertFalse(Validator.validateInput("%dabc"))
    }
}