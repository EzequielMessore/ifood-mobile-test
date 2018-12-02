package br.com.ezequiel.twitterhappines.presentation.user

import android.graphics.Color
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class UserModel(
    val id: Int,
    val name: String,
    val screenName: String,
    val createdAt: Date,
    val image: String,
    val backgroundColor: String,
    val bannerUrl: String
) : Parcelable {

    fun displayName() = "| @$screenName"
    fun color() = Color.parseColor("#$backgroundColor")

}