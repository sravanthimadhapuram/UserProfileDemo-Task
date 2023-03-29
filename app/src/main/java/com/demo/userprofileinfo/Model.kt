package com.demo.userprofileinfo

data class User(val id: Int,val name: String, val website: String)
data class Albums(val id: Int, val title: String, val userId:Int)
data class Photos(val id: Int,val title: String, val url: String, val thumbnailUrl: String,val albumId:Int)