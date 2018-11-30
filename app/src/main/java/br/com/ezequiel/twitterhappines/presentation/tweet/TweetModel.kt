package br.com.ezequiel.twitterhappines.presentation.tweet

import br.com.ezequiel.twitterhappines.core.extension.toSimpleString
import java.util.*

data class TweetModel(
    val id: String,
    val createAt: Date,
    val text: String,
    val favoriteCount: Int) {

    var humor: Humor? = null

    val date = createAt.toSimpleString()
}