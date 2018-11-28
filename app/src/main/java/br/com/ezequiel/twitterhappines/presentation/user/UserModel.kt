package br.com.ezequiel.twitterhappines.presentation.user

import java.util.*

data class UserModel(
    val name: String,
    val screenName: String,
    val createdAt: Date,
    val image: String,
    val imageBackground: String
)