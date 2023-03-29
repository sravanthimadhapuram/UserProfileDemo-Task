package com.demo.userprofileinfo.ui.screens

import android.annotation.SuppressLint
import android.content.Intent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.demo.userprofileinfo.R
import com.demo.userprofileinfo.User
import com.demo.userprofileinfo.ui.AlbumsListActivity

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ProfileListScreen(userList: List<User>, navController: NavHostController?) {
    val context = LocalContext.current

    Scaffold(topBar = {
        AppBar(
            title = "User List",
            imageVector = Icons.Filled.Menu
        ) {}
    }) {
        Surface(modifier = Modifier.fillMaxSize()) {
            LazyColumn {
                items(userList) { user ->
                    ProfileCard(user) {
                      //  navController?.navigate("albums/${it.id}")
                        var intent = Intent(context, AlbumsListActivity::class.java)
                        intent.putExtra("id",it.id)
                        context.startActivity(intent)
                    }
                }
            }
        }
    }
}

@Composable
fun AppBar(title: String, imageVector: ImageVector, iconClickAction: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = title,
                color = Color.White
            )
        },
        navigationIcon = {
            IconButton(onClick = { iconClickAction.invoke() }) {
                Icon(imageVector = imageVector, contentDescription = "")
            }
        }
    )
}
@Composable
fun ProfileCard(userProfile: User, clickAction: (User) -> Unit) {
    Card(
        modifier = Modifier
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth()
            .wrapContentHeight(align = Alignment.Top)
            .clickable { clickAction(userProfile) },
        elevation = 8.dp,
        backgroundColor = Color.White,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            ProfilePicture(userProfile, 50.dp)
            ProfileContent(userProfile, Alignment.Start)
        }
    }
}

@Composable
fun ProfilePicture(userProfile: User, profilePicSize: Dp) {
    Card(
        shape = CircleShape,
        border = BorderStroke(
            width = 0.3.dp,
            color = Color.Gray
        ),
        modifier = Modifier.padding(16.dp),
        elevation = 4.dp
    ) {
        Image(
            painter = painterResource(R.drawable.profile_n),
            contentDescription = "",
            modifier = Modifier.size(profilePicSize),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun ProfileContent(userProfile: User, alignment: Alignment.Horizontal) {
    Column(
        modifier = Modifier
            .padding(8.dp),
        horizontalAlignment = alignment
    ) {
        CompositionLocalProvider(
            LocalContentAlpha provides ContentAlpha.medium
        ) {
            Text(text = userProfile.name, style = MaterialTheme.typography.h6)
        }
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Text(
                text = userProfile.website ,
                style = MaterialTheme.typography.body2
            )
        }
    }
}

