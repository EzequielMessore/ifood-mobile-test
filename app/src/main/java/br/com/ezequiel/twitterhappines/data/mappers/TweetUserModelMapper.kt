package br.com.ezequiel.twitterhappines.data.mappers

import br.com.ezequiel.twitterhappines.data.ws.user.UserTweet
import br.com.ezequiel.twitterhappines.presentation.tweet.UserTweetModel
import javax.inject.Inject

class TweetUserModelMapper @Inject constructor() : Mapper<UserTweet, UserTweetModel> {
    override fun transform(from: UserTweet): UserTweetModel =
        UserTweetModel(
            from.screenName
        )
}