package com.demo.userprofileinfo

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ValidationUtilTest {

    @Test
    fun validateUserTest() {
        val user = User(1,"testUrl","main")
        assertEquals(true, ValidationUtil.validateUser(user))
    }

    @Test
    fun validateMovieEmptyTest() {
        val movie = User(1,"","")
        assertEquals(false, ValidationUtil.validateUser(movie))
    }

}