package com.demo.userprofileinfo

class MainRepository constructor(private val retrofitService: RetrofitService) {

    suspend fun getUsers() : NetworkState<List<User>> {
            val response = retrofitService.getUsers()
            return if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null) {
                    NetworkState.Success(responseBody)
                } else {
                    NetworkState.Error(response)
                }
            } else {
                NetworkState.Error(response)
            }
        }
    suspend fun getAlbums(id: Int) : NetworkState<List<Albums>> {
        val response = retrofitService.getAlbums(id)
        return if (response.isSuccessful) {
            val responseBody = response.body()
            if (responseBody != null) {
                NetworkState.Success(responseBody)
            } else {
                NetworkState.Error(response)
            }
        } else {
            NetworkState.Error(response)
        }
    }  suspend fun getPhotos(id: Int) : NetworkState<List<Photos>> {
        val response = retrofitService.getPhotos(id)
        return if (response.isSuccessful) {
            val responseBody = response.body()
            if (responseBody != null) {
                NetworkState.Success(responseBody)
            } else {
                NetworkState.Error(response)
            }
        } else {
            NetworkState.Error(response)
        }
    }
}