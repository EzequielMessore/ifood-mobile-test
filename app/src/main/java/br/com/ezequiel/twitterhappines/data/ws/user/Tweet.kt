package br.com.ezequiel.twitterhappines.data.ws.user

import com.google.gson.annotations.SerializedName

data class Tweet(
    val id: String,
    @SerializedName("created_at")
    val createAt: String,
    val text: String,
    @SerializedName("favoriteCount")
    val favoriteCount: Int
)