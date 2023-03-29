package com.demo.userprofileinfo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
@HiltViewModel
class MainViewModel constructor(private val mainRepository: MainRepository) : ViewModel() {

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
    get() = _errorMessage

    val userList = MutableLiveData<List<User>>()
    val photoList = MutableLiveData<List<Photos>>()
    val albumList = MutableLiveData<List<Albums>>()

    var job: Job? = null


    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }
    val loading = MutableLiveData<Boolean>()

    fun getUsers() {
        Log.d("Thread Outside", Thread.currentThread().name)

        viewModelScope.launch {
            Log.d("Thread Inside", Thread.currentThread().name)
            when (val response = mainRepository.getUsers()) {
                is NetworkState.Success -> {
                    userList.postValue(response.data)
                }
                is NetworkState.Error -> {
                    if (response.response.code() == 401) {
                        //userList.postValue(NetworkState.Error())
                    } else {
                       // userList.postValue(NetworkState.Error)
                    }
                }
            }
        }
    }


    fun getAlbums(id: Int) {
        Log.d("Thread Outside", Thread.currentThread().name)

        viewModelScope.launch {
            Log.d("Thread Inside", Thread.currentThread().name)
            when (val response = mainRepository.getAlbums(id)) {
                is NetworkState.Success -> {
                    albumList.postValue(response.data)
                }
                is NetworkState.Error -> {
                    if (response.response.code() == 401) {
                        //movieList.postValue(NetworkState.Error())
                    } else {
                        //movieList.postValue(NetworkState.Error)
                    }
                }
            }
        }
    }

    fun getPhotos(id: Int) {
        Log.d("Thread Outside", Thread.currentThread().name)

        viewModelScope.launch {
            Log.d("Thread Inside", Thread.currentThread().name)
            when (val response = mainRepository.getPhotos(id)) {
                is NetworkState.Success -> {
                    photoList.postValue(response.data)
                }
                is NetworkState.Error -> {
                    if (response.response.code() == 401) {
                        //movieList.postValue(NetworkState.Error())
                    } else {
                        //movieList.postValue(NetworkState.Error)
                    }
                }
            }
        }
    }

    private fun onError(message: String) {
        _errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}