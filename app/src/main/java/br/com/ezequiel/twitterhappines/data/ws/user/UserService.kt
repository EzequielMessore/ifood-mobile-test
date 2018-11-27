package br.com.ezequiel.twitterhappines.data.ws.user

import br.com.ezequiel.twitterhappines.domain.datasource.UserDataSource
import io.reactivex.Observable
import javax.inject.Inject

class UserService @Inject constructor(
    private val api: UserApi
) : UserDataSource {

    override fun getUser(username: String): Observable<User> = api.getUser(username)
    override fun getTweets(userId: Int): Observable<List<Tweet>> = api.getTweets(userId)

}