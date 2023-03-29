package com.demo.userprofileinfo


import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

@RunWith(JUnit4::class)
class MainRepositoryTest {

    lateinit var mainRepository: MainRepository

    @Mock
    lateinit var apiService: RetrofitService

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        mainRepository = MainRepository(apiService)
    }

    @Test
    fun `get all user test`() {
        runBlocking {
            Mockito.`when`(apiService.getUsers()).thenReturn(Response.success(listOf<User>()))
            val response = mainRepository.getUsers()
            assertTrue(Response.success(listOf<User>()).isSuccessful)
        }

    }

}