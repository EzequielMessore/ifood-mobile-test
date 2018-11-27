package br.com.ezequiel.twitterhappines.data.ws.user

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi {
    @GET("users/show.json?screen_name=username")
    fun getUser(@Query("username") username: String): Observable<User>

    @GET("statuses/user_timeline.json?user_id=id")
    fun getTweets(@Query("id") userId: Int): Observable<List<Tweet>>
}