package br.com.ezequiel.twitterhappines.presentation.user

import br.com.ezequiel.twitterhappines.presentation.tweet.TweetModel
import retrofit2.HttpException
import java.net.HttpURLConnection

sealed class UserState
data class UserData(val data: UserModel) : UserState()
data class TweetData(val data: List<TweetModel>): UserState()
object UserLoading : UserState()
data class UserError(val error: Throwable) : UserState() {
    fun isNotFound() = error is HttpException && error.code() == HttpURLConnection.HTTP_NOT_FOUND
}