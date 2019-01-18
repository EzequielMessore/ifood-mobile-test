package br.com.ezequiel.twitterhappines.presentation.user

import android.arch.lifecycle.MutableLiveData
import br.com.ezequiel.twitterhappines.core.platform.BaseViewModel
import br.com.ezequiel.twitterhappines.domain.interactor.AnalyseText
import br.com.ezequiel.twitterhappines.domain.interactor.GetTweetsByUserId
import br.com.ezequiel.twitterhappines.domain.interactor.GetUser
import br.com.ezequiel.twitterhappines.presentation.tweet.*
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class UserViewModel @Inject constructor(
    private val getUserTask: GetUser,
    private val getTweetsByUserId: GetTweetsByUserId,
    private val analyseTextTask: AnalyseText
) : BaseViewModel() {

    val state: MutableLiveData<UserState> = MutableLiveData()
    val tweetState: MutableLiveData<TweetState> = MutableLiveData()
    val tweets: MutableLiveData<List<TweetModel>> = MutableLiveData()

    fun getUser(username: String) {
        state.value = UserLoading
        getUserTask(username)
            .subscribeBy(
                onNext = {
                    state.value = UserData(it)
                },
                onError = {
                    state.value = UserError(it)
                }
            ).addTo(disposable)
    }

    fun getTweetsById(userId: Int) {
        tweetState.value = TweetLoading
        getTweetsByUserId(userId)
            .subscribeBy(
                onNext = {
                    tweets.value = it
                    tweetState.value = TweetData(it)
                },
                onError = ::onError
            ).addTo(disposable)
    }

    fun analyseText(tweet: TweetModel) {
        tweetState.value = TweetLoadingItem
        analyseTextTask(tweet.text)
            .subscribeBy(
                onNext = {
                    tweet.humor = it
                    tweets.value = tweets.value?.map { model ->
                        if (model.id != tweet.id) {
                            model
                        } else {
                            tweet
                        }
                    }
                    tweets.value?.let { list ->
                        tweetState.value = TweetData(list)
                    }
                },
                onError = ::onError
            ).addTo(disposable)
    }

    private fun onError(throwable: Throwable) {
        tweetState.value = TweetError(throwable)
    }
}