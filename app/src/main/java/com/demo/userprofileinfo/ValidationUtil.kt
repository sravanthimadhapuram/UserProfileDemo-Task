package com.demo.userprofileinfo

object ValidationUtil {

    fun validateUser(user: User) : Boolean {
        if (user.name.isNotEmpty() && user.website.isNotEmpty()) {
            return true
        }
        return false
    }

    fun validateAlbum(album: Albums) : Boolean {
        if (album.title.isNotEmpty()) {
            return true
        }
        return false
    }

    fun validatePhoto(photos: Photos) : Boolean {
        if (photos.title.isNotEmpty() && photos.thumbnailUrl.isNotEmpty()) {
            return true
        }
        return false
    }
}