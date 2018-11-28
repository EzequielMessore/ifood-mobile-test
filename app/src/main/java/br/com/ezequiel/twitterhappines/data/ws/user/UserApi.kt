package br.com.ezequiel.twitterhappines.data.ws.user

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi {
    @GET("1.1/users/show.json")
    fun getUser(@Query("screen_name") username: String): Observable<User>

    @GET("1.1/statuses/user_timeline.json?user_id=id")
    fun getTweets(@Query("id") userId: Int): Observable<List<Tweet>>
}