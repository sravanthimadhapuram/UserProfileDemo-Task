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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.demo.userprofileinfo.Photos
import com.google.accompanist.coil.rememberCoilPainter


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@ExperimentalFoundationApi
@Composable
fun PhotoListScreen(photosList: List<Photos>, navController: NavHostController?) {
    val activity = (LocalContext.current as? Activity)

    Scaffold(topBar = {
        AppBar(
            title = "Photos of Album",
            imageVector = Icons.Filled.ArrowBack
        ) {
            activity?.finish()
        }
    }) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 80.dp),
            contentPadding = PaddingValues(8.dp)
        ) {
            itemsIndexed(photosList) { index, destination ->
                Row(Modifier.padding(8.dp)) {
                    navController?.let { ItemLayout(destination, index, it) }
                }
            }
        }

    }


}
@Composable
fun ItemLayout(
    destination: Photos,
    index: Int,
    navController: NavHostController
) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .background(color = Color.White)
            .fillMaxWidth()
            .clickable {
                navController.navigate("fullImage/$index")
            }.height(80.dp)
    ) {
        Image(
            painter = rememberCoilPainter(destination.thumbnailUrl),
            contentDescription = destination.title,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
        Text(
            text =  destination.title,
            color = Color.Black,
            fontSize = 14.sp,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)
        )
    }
}
