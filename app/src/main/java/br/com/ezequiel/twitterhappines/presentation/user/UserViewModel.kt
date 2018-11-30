package br.com.ezequiel.twitterhappines.presentation.user

import android.arch.lifecycle.MutableLiveData
import br.com.ezequiel.twitterhappines.core.platform.BaseViewModel
import br.com.ezequiel.twitterhappines.domain.interactor.AnalyseText
import br.com.ezequiel.twitterhappines.domain.interactor.GetTweetsByUserId
import br.com.ezequiel.twitterhappines.domain.interactor.GetUser
import br.com.ezequiel.twitterhappines.presentation.tweet.TweetModel
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class UserViewModel @Inject constructor(
    private val getUserTask: GetUser,
    private val getTweetsByUserId: GetTweetsByUserId,
    private val analyseTextTask: AnalyseText
) : BaseViewModel() {

    val state: MutableLiveData<UserState> = MutableLiveData()
    val tweets: MutableLiveData<List<TweetModel>> = MutableLiveData()

    fun getUser(username: String) {
        state.value = UserLoading
        getUserTask(username)
            .subscribeBy(
                onNext = {
                    state.value = UserData(it)
                },
                onError = ::onError
            ).addTo(disposable)
    }

    fun getTweetsById(userId: Int) {
        state.value = UserLoading
        getTweetsByUserId(userId)
            .subscribeBy(
                onNext = {
                    tweets.value = it
                    state.value = TweetData(it)
                },
                onError = ::onError
            ).addTo(disposable)
    }

    fun analyseText(tweet: TweetModel) {
        state.value = UserLoading
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
                },
                onError = ::onError
            ).addTo(disposable)
    }

    private fun onError(throwable: Throwable) {
        state.value = UserError(throwable)
    }
}