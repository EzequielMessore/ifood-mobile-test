package br.com.ezequiel.twitterhappines.data.ws.user

import com.google.gson.annotations.SerializedName

data class Tweet(
    @SerializedName("create_at")
    val createAt: String,
    val text: String,
    val favorited: Boolean,
    val retweeted: Boolean,
    @SerializedName("retweet_count")
    val retweetCount: Int,
    @SerializedName("favorite_count")
    val favorite_count: Int
)