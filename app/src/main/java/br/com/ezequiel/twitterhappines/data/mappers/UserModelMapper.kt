package br.com.ezequiel.twitterhappines.data.mappers

import br.com.ezequiel.twitterhappines.core.extension.toDate
import br.com.ezequiel.twitterhappines.data.ws.user.User
import br.com.ezequiel.twitterhappines.presentation.user.UserModel
import javax.inject.Inject

class UserModelMapper @Inject constructor() : Mapper<User, UserModel> {
    override fun transform(from: User): UserModel =
        UserModel(
            id = from.id,
            name = from.name,
            screenName = from.screenName,
            createdAt = from.createdAt.toDate(),
            image = from.image,
            bannerUrl = from.bannerUrl ?: "",
            backgroundColor = from.backgroundColor
        )
}