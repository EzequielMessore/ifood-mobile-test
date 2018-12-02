package br.com.ezequiel.twitterhappines.presentation.user

import android.arch.lifecycle.Observer
import br.com.ezequiel.twitterhappines.base.Mocks
import br.com.ezequiel.twitterhappines.base.addArchExecutor
import br.com.ezequiel.twitterhappines.base.cleanArchExecutor
import br.com.ezequiel.twitterhappines.base.mock
import br.com.ezequiel.twitterhappines.domain.interactor.AnalyseText
import br.com.ezequiel.twitterhappines.domain.interactor.GetTweetsByUserId
import br.com.ezequiel.twitterhappines.domain.interactor.GetUser
import br.com.ezequiel.twitterhappines.presentation.tweet.*
import io.reactivex.Observable
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import org.mockito.Mockito.*


@RunWith(JUnitPlatform::class)
class UserViewModelTest : Spek({

    given("I have a UserViewModel") {
        val getUserTask = mock<GetUser>()
        val getTweetsByUserId = mock<GetTweetsByUserId>()
        val analyseTextTask = mock<AnalyseText>()
        val viewModel = UserViewModel(getUserTask, getTweetsByUserId, analyseTextTask)


        beforeEachTest {
            addArchExecutor()
        }

        afterEachTest {
            cleanArchExecutor()
        }

        on("I want get an User") {
            val ongoingStubbing = `when`(getUserTask(anyString()))

            it("then returns a UserModel") {
                val observer = mock<Observer<UserState>>()
                ongoingStubbing.thenReturn(Observable.just(Mocks.userModel))

                viewModel.state.observeForever(observer)
                viewModel.getUser(anyString())

                verify(observer).onChanged(UserLoading)
                verify(observer).onChanged(UserData(Mocks.userModel))
            }

            it("then returns an Error") {
                val observer = mock<Observer<UserState>>()
                ongoingStubbing.thenReturn(Observable.error(Mocks.exception))

                viewModel.state.observeForever(observer)
                viewModel.getUser(anyString())

                verify(observer).onChanged(UserLoading)
                verify(observer).onChanged(UserError(Mocks.exception))
            }
        }

        on("I want get Tweets by user id") {
            val stubbing = `when`(getTweetsByUserId(anyInt()))

            it("then returns a UserModel") {
                val observer = mock<Observer<TweetState>>()
                stubbing.thenReturn(Observable.just(Mocks.tweetListModel))

                viewModel.tweetState.observeForever(observer)
                viewModel.getTweetsById(anyInt())

                verify(observer).onChanged(TweetLoading)
                verify(observer).onChanged(TweetData(Mocks.tweetListModel))
            }

            it("then returns an Error") {
                val observer = mock<Observer<TweetState>>()
                stubbing.thenReturn(Observable.error(Mocks.exception))

                viewModel.tweetState.observeForever(observer)
                viewModel.getTweetsById(anyInt())

                verify(observer).onChanged(TweetLoading)
                verify(observer).onChanged(TweetError(Mocks.exception))
            }
        }

        on("I want analyse one text") {
            val stubbing = `when`(analyseTextTask(anyString()))

            it("then return an Humor") {
                stubbing.thenReturn(Observable.just(Mocks.humor))
                viewModel.tweets.value = Mocks.tweetListModel

                val observer = mock<Observer<TweetState>>()
                val observerList = mock<Observer<List<TweetModel>>>()

                viewModel.tweetState.observeForever(observer)
                viewModel.tweets.observeForever(observerList)

                viewModel.analyseText(Mocks.tweetListModel[0])

                verify(observer).onChanged(TweetLoadingItem)
                verify(observer).onChanged(TweetData(Mocks.tweetListModel))
            }

            it("then returns an Error") {
                val observer = mock<Observer<TweetState>>()
                stubbing.thenReturn(Observable.error(Mocks.exception))

                viewModel.tweetState.observeForever(observer)
                viewModel.analyseText(Mocks.tweetListModel[0])

                verify(observer).onChanged(TweetLoadingItem)
                verify(observer).onChanged(TweetError(Mocks.exception))
            }
        }

    }


})


























