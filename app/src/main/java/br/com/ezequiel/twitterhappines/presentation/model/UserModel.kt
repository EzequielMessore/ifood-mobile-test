package br.com.ezequiel.twitterhappines.presentation.model

import java.util.*

data class UserModel(
    val name: String,
    val screenName: String,
    val createdAt: Date,
    val image: String,
    val imageBackground: String
)