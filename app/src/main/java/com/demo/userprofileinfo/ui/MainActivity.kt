package com.demo.userprofileinfo.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.demo.userprofileinfo.*
import com.demo.userprofileinfo.theme.JetpackcomposeTheme
import com.demo.userprofileinfo.ui.screens.ProfileListScreen

class MainActivity : ComponentActivity() {
    lateinit var viewModel: MainViewModel

    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val retrofitService = RetrofitService.getInstance()
        val mainRepository = MainRepository(retrofitService)
        viewModel = ViewModelProvider(
            this,
            MyViewModelFactory(mainRepository)
        ).get(MainViewModel::class.java)
        viewModel.userList.observe(this) {
            setContent {
              //  ProfileListScreen(it, null)
                JetpackcomposeTheme {
                    UsersApplication(it, viewModel)
                }
            }
        }

        viewModel.errorMessage.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
        viewModel.getUsers()

    }
}


    @ExperimentalFoundationApi
    @Composable
    fun UsersApplication( userList: List<User>,destinationViewModel: MainViewModel) {
       val navController:NavHostController = rememberNavController()
        NavHost(navController = navController, startDestination = "userList") {
            composable(route = "userList") {
                ProfileListScreen(userList, navController)
            }
        }



}