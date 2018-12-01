package br.com.ezequiel.twitterhappines.presentation.user

import retrofit2.HttpException
import java.net.HttpURLConnection

sealed class UserState
data class UserData(val data: UserModel?) : UserState()
object UserLoading : UserState()
data class UserError(val error: Throwable) : UserState() {
    fun isNotFound() = error is HttpException && error.code() == HttpURLConnection.HTTP_NOT_FOUND
}