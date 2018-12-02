package br.com.ezequiel.twitterhappines.domain.interactor

import br.com.ezequiel.twitterhappines.OpenForTesting
import br.com.ezequiel.twitterhappines.core.interactor.UseCaseObservable
import br.com.ezequiel.twitterhappines.core.scheduler.ISchedulersProvider
import br.com.ezequiel.twitterhappines.data.ws.language.Analyse
import br.com.ezequiel.twitterhappines.data.ws.language.AnalyseDocument
import br.com.ezequiel.twitterhappines.data.ws.language.LanguageService
import br.com.ezequiel.twitterhappines.presentation.tweet.Humor
import io.reactivex.Observable
import javax.inject.Inject

@OpenForTesting
class AnalyseText @Inject constructor(
    private val languageService: LanguageService,
    schedulersProviders: ISchedulersProvider
) : UseCaseObservable<String, Humor>(schedulersProviders) {

    final override fun buildUseCase(params: String): Observable<Humor> =
        languageService.analyseText(
            Analyse(
                document = AnalyseDocument(
                    content = params
                )
            )
        )

}