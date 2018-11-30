package br.com.ezequiel.twitterhappines.data.ws.language

import br.com.ezequiel.twitterhappines.BuildConfig
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface LanguageApi {
    @POST("/v1/documents:analyzeSentiment")
    fun analiseText(
        @Query("key") auth: String = BuildConfig.LANGUAGE_KEY,
        @Body analyse: Analyse
    ) : Observable<Result>

}