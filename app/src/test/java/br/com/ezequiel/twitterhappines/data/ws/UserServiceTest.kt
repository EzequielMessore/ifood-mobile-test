package br.com.ezequiel.twitterhappines.data.ws

import br.com.ezequiel.twitterhappines.base.Mocks
import br.com.ezequiel.twitterhappines.base.mock
import br.com.ezequiel.twitterhappines.data.ws.user.UserApi
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
class UserServiceTest : Spek({

    given("a UserService") {
        val userApi = mock<UserApi>()
        val service = UserService(userApi)

        on("get User") {
            `when`(userApi.getUser(anyString())).thenReturn(Observable.just(Mocks.user))
            val test = service.getUser(anyString()).test()
            test.awaitTerminalEvent()

            it("return a user") {
                test.assertValue(Mocks.user)
            }

            it("return without errors and then finish") {
                test.assertNoErrors()
                test.assertTerminated()
            }

        }

        on("get TwitterList") {
            `when`(userApi.getTweets(anyInt())).thenReturn(Observable.just(Mocks.tweetList))
            val test = service.getTweets(anyInt()).test()
            test.awaitTerminalEvent()

            it("return a list of tweets") {
                test.assertValue(Mocks.tweetList)
            }

            it("return without errors and then finish") {
                test.assertNoErrors()
                test.assertTerminated()
            }

        }

    }
})