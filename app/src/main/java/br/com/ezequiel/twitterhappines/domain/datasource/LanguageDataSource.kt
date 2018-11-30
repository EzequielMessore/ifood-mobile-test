package br.com.ezequiel.twitterhappines.domain.datasource

import br.com.ezequiel.twitterhappines.data.ws.language.Analyse
import br.com.ezequiel.twitterhappines.presentation.tweet.Humor
import io.reactivex.Observable

interface LanguageDataSource {
    fun analyseText(analyse: Analyse) : Observable<Humor>
}