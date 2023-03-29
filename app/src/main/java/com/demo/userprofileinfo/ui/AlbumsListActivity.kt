package com.demo.userprofileinfo.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.demo.userprofileinfo.*
import com.demo.userprofileinfo.theme.JetpackcomposeTheme
import com.demo.userprofileinfo.ui.screens.AlbumListScreen
import com.demo.userprofileinfo.ui.screens.FullImage
import com.demo.userprofileinfo.ui.screens.PhotoListScreen


class AlbumsListActivity : ComponentActivity() {
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

        viewModel.albumList.observe(this) {
            setContent {
               // AlbumListScreen(it, null)
                JetpackcomposeTheme {
                   // AlbumListScreen( it, navController)
                    NavAlbumApplication(it, viewModel)
                }

            }
        }
        viewModel.photoList.observe(this) {
            setContent {
                // AlbumListScreen(it, null)
                JetpackcomposeTheme {
                    NavPhotsApplication( it)

                }

            }
        }
        viewModel.errorMessage.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
viewModel.getAlbums(intent.getIntExtra("id",1))

    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NavAlbumApplication(albumList: List<Albums>, destinationViewModel: MainViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "albumList") {
        composable(route = "albumList") {
                AlbumListScreen(albumsList = albumList, navController = navController)
        }
        val USER_ID_KEY="id"
        composable(
            route = "photos/{$USER_ID_KEY}",
            arguments = listOf(navArgument(USER_ID_KEY) {
                type = NavType.IntType
            })
        ) { navBackStackEntry ->
            destinationViewModel.getPhotos(navBackStackEntry.arguments!!.getInt(USER_ID_KEY))
        }

    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NavPhotsApplication(photsList: List<Photos>) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "photsList") {

        val USER_ID_KEY="id"
        composable(
            route = "photsList",
            arguments = listOf(navArgument(USER_ID_KEY) {
                type = NavType.IntType
            })
        ) { navBackStackEntry ->
            PhotoListScreen(photsList,navController)
        }
        composable(
            route = "fullImage/{$USER_ID_KEY}",
            arguments = listOf(navArgument(USER_ID_KEY) {
                type = NavType.IntType
            })
        ) { navBackStackEntry ->
            FullImage( photsList.get(navBackStackEntry.arguments!!.getInt(USER_ID_KEY)), navController)
        }

    }

}