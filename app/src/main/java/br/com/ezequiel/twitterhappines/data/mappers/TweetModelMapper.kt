package br.com.ezequiel.twitterhappines.data.mappers

import br.com.ezequiel.twitterhappines.core.extension.toDate
import br.com.ezequiel.twitterhappines.data.ws.user.Tweet
import br.com.ezequiel.twitterhappines.presentation.tweet.TweetModel
import javax.inject.Inject

class TweetModelMapper @Inject constructor(
    private val mapper: TweetUserModelMapper
) : Mapper<Tweet, TweetModel> {
    override fun transform(from: Tweet): TweetModel =
        TweetModel(
            createAt = from.createAt.toDate(),
            favoriteCount = from.favorite_count,
            favorited = from.favorited,
            retweetCount = from.retweetCount,
            retweeted = from.retweeted,
            text = from.text,
            user = mapper.transform(from.user)
        )
}