package com.example.majournee.models

data class Song(var name: String? = "", var artist: String? = "", var image: String? = ""){

    override fun toString(): String {
        return "Song(name=$name, artist=$artist, image=$image)"
    }
}