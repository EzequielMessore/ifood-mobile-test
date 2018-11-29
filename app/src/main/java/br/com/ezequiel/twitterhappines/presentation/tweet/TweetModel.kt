package br.com.ezequiel.twitterhappines.presentation.tweet

import br.com.ezequiel.twitterhappines.core.extension.toSimpleString
import java.util.*

data class TweetModel(
    val createAt: Date,
    val text: String,
    val favorited: Boolean,
    val retweeted: Boolean,
    val retweetCount: Int,
    val favoriteCount: Int,
    val user: UserTweetModel) {

    val HAPPY_EMOTION = 0x1F601
    val NEUTRAL_EMOTION = 0x1F610
    val SAD_EMOTION = 0x1F61

    val date = createAt.toSimpleString()

    fun getEmotion(unicode: Int) = String(Character.toChars(unicode))
}

data class UserTweetModel(
    val name: String
)