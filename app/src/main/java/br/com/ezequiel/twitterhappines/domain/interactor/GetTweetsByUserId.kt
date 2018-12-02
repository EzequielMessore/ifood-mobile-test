package br.com.ezequiel.twitterhappines.domain.interactor

import br.com.ezequiel.twitterhappines.core.interactor.UseCaseObservable
import br.com.ezequiel.twitterhappines.core.scheduler.ISchedulersProvider
import br.com.ezequiel.twitterhappines.data.mappers.TweetModelMapper
import br.com.ezequiel.twitterhappines.data.ws.user.UserService
import br.com.ezequiel.twitterhappines.presentation.tweet.TweetModel
import io.reactivex.Observable
import javax.inject.Inject

open class GetTweetsByUserId @Inject constructor(
    private val userService: UserService,
    private val mapper: TweetModelMapper,
    schedulersProvider: ISchedulersProvider
) : UseCaseObservable<Int, List<TweetModel>>(schedulersProvider) {

    override fun buildUseCase(params: Int): Observable<List<TweetModel>> =
        userService.getTweets(params).map { mapper.transform(it) }

}