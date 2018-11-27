package br.com.ezequiel.twitterhappines.core.interactor

import br.com.ezequiel.twitterhappines.core.scheduler.ISchedulersProvider
import io.reactivex.Single

abstract class UseCaseSingle<in Params, T> constructor(private val schedulersProvider: ISchedulersProvider) :
    UseCase<Params, Single<T>>() {

    abstract fun buildUseCase(params: Params): Single<T>

    override fun run(params: Params): Single<T> =
        buildUseCase(params)
            .subscribeOn(schedulersProvider.io())
            .observeOn(schedulersProvider.ui())

}

