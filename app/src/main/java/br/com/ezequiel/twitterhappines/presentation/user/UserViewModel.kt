package br.com.ezequiel.twitterhappines.presentation.user

import android.arch.lifecycle.MutableLiveData
import br.com.ezequiel.twitterhappines.core.platform.BaseViewModel
import br.com.ezequiel.twitterhappines.domain.interactor.GetTweetsByUserId
import br.com.ezequiel.twitterhappines.domain.interactor.GetUser
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class UserViewModel @Inject constructor(
    private val getUserTask: GetUser,
    private val getTweetsByUserId: GetTweetsByUserId
) : BaseViewModel() {

    val state: MutableLiveData<UserState> = MutableLiveData()

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
                    state.value = TweetData(it)
                },
                onError = ::onError
            ).addTo(disposable)
    }

    private fun onError(throwable: Throwable) {
        state.value = UserError(throwable)
    }
}