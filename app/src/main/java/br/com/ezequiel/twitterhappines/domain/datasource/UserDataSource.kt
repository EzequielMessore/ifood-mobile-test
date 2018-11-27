package br.com.ezequiel.twitterhappines.domain.datasource

import br.com.ezequiel.twitterhappines.data.ws.user.Tweet
import br.com.ezequiel.twitterhappines.data.ws.user.User
import io.reactivex.Observable

interface UserDataSource {
    fun getUser(username: String): Observable<User>
    fun getTweets(userId: Int): Observable<List<Tweet>>
}