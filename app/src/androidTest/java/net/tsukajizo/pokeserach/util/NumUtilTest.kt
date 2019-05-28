package net.tsukajizo.pokeserach.util

import android.support.test.runner.AndroidJUnit4
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NumUtilTest {

    @Test
    fun isNumTest() {
        assertTrue(NumUtil.isNum("124"))
        assertFalse(NumUtil.isNum("abc"))
        assertFalse(NumUtil.isNum("1ab"))
    }
}