package br.com.ezequiel.twitterhappines.presentation.tweet

sealed class TweetState
data class TweetData(val data: List<TweetModel>) : TweetState()
object TweetLoading : TweetState()
object TweetLoadingItem: TweetState()
data class TweetError(val error: Throwable) : TweetState()