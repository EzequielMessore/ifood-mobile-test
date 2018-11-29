package br.com.ezequiel.twitterhappines.data.ws.user

import com.google.gson.annotations.SerializedName

class User(
    val id: Int,
    val name: String,
    @SerializedName("screen_name")
    val screenName: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("profile_image_url")
    val image: String,
    @SerializedName("profile_background_color")
    val backgroundColor: String,
    @SerializedName("profile_banner_url")
    val bannerUrl: String?
)