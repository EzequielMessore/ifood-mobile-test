package br.com.ezequiel.twitterhappines.domain.interactor

import br.com.ezequiel.twitterhappines.base.Mocks
import br.com.ezequiel.twitterhappines.base.SchedulerProvider
import br.com.ezequiel.twitterhappines.base.mock
import br.com.ezequiel.twitterhappines.data.mappers.TweetModelMapper
import br.com.ezequiel.twitterhappines.data.mappers.UserModelMapper
import br.com.ezequiel.twitterhappines.data.ws.language.LanguageService
import br.com.ezequiel.twitterhappines.data.ws.user.UserService
import io.reactivex.Observable
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import org.mockito.Mockito.*

@RunWith(JUnitPlatform::class)
class UseCaseTest : Spek({

    given("the use case ${GetUser::class.simpleName}") {
        val userService = mock<UserService>()
        val mapper = mock<UserModelMapper>()
        val task = GetUser(userService, mapper, SchedulerProvider)

        on("call invoke()") {
            `when`(userService.getUser(anyString())).thenReturn(Observable.just(Mocks.user))
            `when`(mapper.transform(Mocks.user)).thenReturn(Mocks.userModel)
            val test = task(anyString()).test()

            it("then return a UserModel") {
                test.awaitTerminalEvent()
                test.assertValue(Mocks.userModel)
            }

            it("return without errors and then finish") {
                test.assertNoErrors()
                test.assertTerminated()
            }
        }

        on("call invoke() and return error") {
            `when`(userService.getUser(anyString())).thenReturn(Observable.error(Mocks.exception))
            val test = task(anyString()).test()

            it("return error") {
                test.awaitTerminalEvent()
                test.assertError(Mocks.exception)
                test.assertNotComplete()
            }
        }
    }

    given("the use case ${GetTweetsByUserId::class.simpleName}") {
        val userService = mock<UserService>()
        val mapper = mock<TweetModelMapper>()
        val task = GetTweetsByUserId(userService, mapper, SchedulerProvider)

        on("call invoke()") {
            `when`(userService.getTweets(anyInt())).thenReturn(Observable.just(Mocks.tweetList))
            `when`(mapper.transform(Mocks.tweetList)).thenReturn(Mocks.tweetListModel)
            val test = task(anyInt()).test()

            it("then return a List of Tweet Model") {
                test.awaitTerminalEvent()
                test.assertValue(Mocks.tweetListModel)
            }

            it("return without errors and then finish") {
                test.assertNoErrors()
                test.assertTerminated()
            }
        }

        on("call invoke() and return error") {
            `when`(userService.getTweets(anyInt())).thenReturn(Observable.error(Mocks.exception))
            val test = task(anyInt()).test()

            it("return error") {
                test.awaitTerminalEvent()
                test.assertError(Mocks.exception)
                test.assertNotComplete()
            }
        }
    }

    given("the use case ${AnalyseText::class.simpleName}") {
        val languageService = mock<LanguageService>()
        val task = AnalyseText(languageService, SchedulerProvider)

        on("call invoke()") {
            `when`(languageService.analyseText(Mocks.analyse)).thenReturn(Observable.just(Mocks.humor))
            val test = task("").test()

            it("then return a Humor") {
                test.awaitTerminalEvent()
                test.assertValue(Mocks.humor)
            }

            it("return without errors and then finish") {
                test.assertNoErrors()
                test.assertTerminated()
            }
        }

        on("call invoke() and return error") {
            `when`(languageService.analyseText(Mocks.analyse)).thenReturn(Observable.error(Mocks.exception))
            val test = task("").test()

            it("return error") {
                test.awaitTerminalEvent()
                test.assertError(Mocks.exception)
                test.assertNotComplete()
            }

        }
    }
})