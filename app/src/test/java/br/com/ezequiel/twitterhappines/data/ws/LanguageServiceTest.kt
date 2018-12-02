package br.com.ezequiel.twitterhappines.data.ws

import br.com.ezequiel.twitterhappines.base.Mocks
import br.com.ezequiel.twitterhappines.base.mock
import br.com.ezequiel.twitterhappines.data.ws.language.LanguageApi
import br.com.ezequiel.twitterhappines.data.ws.language.LanguageService
import br.com.ezequiel.twitterhappines.presentation.tweet.Humor
import io.reactivex.Observable
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import org.mockito.Mockito

@RunWith(JUnitPlatform::class)
class LanguageServiceTest : Spek({
    given("a LanguageService") {
        val languageApi = mock<LanguageApi>()
        val service = LanguageService(languageApi)

        on("service returns something") {
            val callingAnalyse = Mockito.`when`(languageApi.analiseText(analyse = Mocks.analyse))

            it("returns a Neutral") {
                callingAnalyse.thenReturn(Observable.just(Mocks.neutralResult))
                val test = service.analyseText(Mocks.analyse).test()

                test.awaitTerminalEvent()
                test.assertValue(Humor.NEUTRAL_EMOTION)
            }

            it("return a Happy") {
                callingAnalyse.thenReturn(Observable.just(Mocks.happyResult))
                val test = service.analyseText(Mocks.analyse).test()

                test.awaitTerminalEvent()
                test.assertValue(Humor.HAPPY_EMOTION)
            }

            it("return a Sad") {
                callingAnalyse.thenReturn(Observable.just(Mocks.sadResult))
                val test = service.analyseText(Mocks.analyse).test()

                test.awaitTerminalEvent()
                test.assertValue(Humor.SAD_EMOTION)
            }

            it("don't return errors and then finish") {
                callingAnalyse.thenReturn(Observable.just(Mocks.sadResult))

                val test = service.analyseText(Mocks.analyse).test()
                test.awaitTerminalEvent()
                test.assertNoErrors()
                test.assertTerminated()
            }

        }
    }
})