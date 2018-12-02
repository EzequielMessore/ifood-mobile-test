package br.com.ezequiel.twitterhappines.data.ws.language

import br.com.ezequiel.twitterhappines.OpenForTesting
import br.com.ezequiel.twitterhappines.domain.datasource.LanguageDataSource
import br.com.ezequiel.twitterhappines.presentation.tweet.Humor
import io.reactivex.Observable
import javax.inject.Inject

@OpenForTesting
class LanguageService @Inject constructor(
    private val api: LanguageApi
) : LanguageDataSource {
    override fun analyseText(analyse: Analyse): Observable<Humor> =
        api.analiseText(analyse = analyse).map {
            when (it.document.score) {
                in -1.0..-0.25 -> Humor.SAD_EMOTION
                in -0.25..0.25 -> Humor.NEUTRAL_EMOTION
                else -> Humor.HAPPY_EMOTION
            }
        }
}