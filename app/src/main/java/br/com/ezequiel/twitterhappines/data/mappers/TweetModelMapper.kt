package br.com.ezequiel.twitterhappines.data.mappers

import br.com.ezequiel.twitterhappines.core.extension.toDate
import br.com.ezequiel.twitterhappines.data.ws.user.Tweet
import br.com.ezequiel.twitterhappines.presentation.tweet.TweetModel
import javax.inject.Inject

class TweetModelMapper @Inject constructor() : Mapper<Tweet, TweetModel> {
    override fun transform(from: Tweet): TweetModel =
        TweetModel(
            id = from.id,
            createAt = from.createAt.toDate(),
            favoriteCount = from.favoriteCount,
            text = from.text
        )
}