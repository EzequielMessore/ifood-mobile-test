package br.com.ezequiel.twitterhappines.base

import br.com.ezequiel.twitterhappines.data.ws.language.Analyse
import br.com.ezequiel.twitterhappines.data.ws.language.AnalyseDocument
import br.com.ezequiel.twitterhappines.data.ws.language.Result
import br.com.ezequiel.twitterhappines.data.ws.language.ResultDocument
import br.com.ezequiel.twitterhappines.data.ws.user.Tweet
import br.com.ezequiel.twitterhappines.data.ws.user.User
import br.com.ezequiel.twitterhappines.presentation.tweet.Humor
import br.com.ezequiel.twitterhappines.presentation.tweet.TweetModel
import br.com.ezequiel.twitterhappines.presentation.user.UserModel
import java.util.*

object Mocks {
    val user = User(
        0,
        "",
        "",
        "",
        "",
        "",
        ""
    )

    val tweetList = listOf(
        Tweet(
            id = "",
            text = "",
            favoriteCount = 0,
            createAt = ""
        )
    )

    val tweetListModel = listOf(
        TweetModel(
            id = "",
            createAt = Date("25/06/1992"),
            text = "",
            favoriteCount = 0
        )
    )

    val analyse = Analyse(
        AnalyseDocument("")
    )

    val neutralResult = Result(ResultDocument(0.0))
    val happyResult = Result(ResultDocument(1.0))
    val sadResult = Result(ResultDocument(-1.0))
    val userModel = UserModel(0, "", "", Date(), "", "", "")
    val humor = Humor.NEUTRAL_EMOTION
    val exception = Exception()
}
