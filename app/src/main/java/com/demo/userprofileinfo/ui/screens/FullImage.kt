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
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.demo.userprofileinfo.Albums
import com.demo.userprofileinfo.Photos
import com.demo.userprofileinfo.R
import com.google.accompanist.coil.rememberCoilPainter


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@ExperimentalFoundationApi
@Composable
fun FullImage(
    photos: Photos,
    navController: NavHostController?
) {
    Scaffold(topBar = {
        AppBar(
            title = "Album List",
            imageVector = Icons.Filled.ArrowBack
        ) {
            navController?.navigateUp()
        }
    }) {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text =  photos.title.replaceFirstChar(Char::titlecase) ,
                    color = Color.Black,
                    fontSize = 22.sp,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp).height(60.dp)
                )
                Image(
                    painter = rememberCoilPainter(photos.url),
                    contentDescription = photos.title,
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.Crop
                ) }
        }

    }

}