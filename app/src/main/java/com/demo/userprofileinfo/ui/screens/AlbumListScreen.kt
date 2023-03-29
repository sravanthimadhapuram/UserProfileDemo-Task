package com.demo.userprofileinfo.ui.screens

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.demo.userprofileinfo.Albums
import com.demo.userprofileinfo.R


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@ExperimentalFoundationApi
@Composable
fun AlbumListScreen(
    albumsList: List<Albums>,
    navController: NavHostController?
) {
    val activity = (LocalContext.current as? Activity)

    Scaffold(topBar = {
        AppBar(
            title = "Album List",
            imageVector = Icons.Filled.ArrowBack
        ) {
            activity?.finish()
        }
    }) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 145.dp),
            contentPadding = PaddingValues(6.dp)
        ) {
            itemsIndexed(albumsList) { index, destination ->
                Row(Modifier.padding(8.dp)) {
                    navController?.let { ItemLayout(destination, destination.id, it) }
                }
            }
        }

    }

}
@Composable
fun ItemLayout(
    destination: Albums,
    index: Int,
    navController: NavHostController
) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
            .clickable {
                navController.navigate("photos/$index")
            }
    ) {
        Image(
            painter = painterResource(R.drawable.album),
            contentDescription = destination.title,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
        Text(
            text =  destination.title.replaceFirstChar(Char::titlecase),
            color = Color.DarkGray,
            fontSize = 16.sp,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp).height(60.dp)
        )
    }
}
