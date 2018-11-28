package br.com.ezequiel.twitterhappines.presentation.user

import android.arch.lifecycle.MutableLiveData
import br.com.ezequiel.twitterhappines.core.platform.BaseViewModel
import br.com.ezequiel.twitterhappines.domain.interactor.GetUser
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class UserViewModel @Inject constructor(
    private val getUser: GetUser
) : BaseViewModel() {

    val state: MutableLiveData<UserState> = MutableLiveData()

    fun getUser(username: String) {
        state.value = UserLoading
        getUser
            .run(username)
            .subscribeBy(
                onNext = {
                    state.value = UserData(it)
                },
                onError = {
                    state.value = UserError(it)
                }
            ).addTo(disposable)
    }
}