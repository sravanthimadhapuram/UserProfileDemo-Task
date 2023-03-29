package com.demo.userprofileinfo

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitService {

    @GET("users")
    suspend fun getUsers() : Response<List<User>>

    @GET("users/{id}/albums")
    suspend fun getAlbums(@Path("id") id: Int ) : Response<List<Albums>>

    @GET("albums/{id}/photos")
    suspend fun getPhotos(@Path("id") id: Int ) : Response<List<Photos>>

    companion object {
        var retrofitService: RetrofitService? = null
        fun getInstance() : RetrofitService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://jsonplaceholder.typicode.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }

    }
}