package br.com.ezequiel.twitterhappines.domain.interactor

import br.com.ezequiel.twitterhappines.core.interactor.UseCaseObservable
import br.com.ezequiel.twitterhappines.core.scheduler.ISchedulersProvider
import br.com.ezequiel.twitterhappines.data.mappers.UserModelMapper
import br.com.ezequiel.twitterhappines.data.ws.user.UserService
import br.com.ezequiel.twitterhappines.presentation.model.UserModel
import io.reactivex.Observable
import javax.inject.Inject

class GetUser @Inject constructor(
    private val userService: UserService,
    private val mapper: UserModelMapper,
    schedulersProvider: ISchedulersProvider
) : UseCaseObservable<String, UserModel>(schedulersProvider) {

    override fun buildUseCase(params: String): Observable<UserModel> =
        userService.getUser(params).map { mapper.transform(it) }

}