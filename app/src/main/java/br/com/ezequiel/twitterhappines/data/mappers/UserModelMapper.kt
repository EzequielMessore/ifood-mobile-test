package br.com.ezequiel.twitterhappines.data.mappers

import br.com.ezequiel.twitterhappines.core.extension.toDate
import br.com.ezequiel.twitterhappines.data.ws.user.User
import br.com.ezequiel.twitterhappines.presentation.model.UserModel

class UserModelMapper : Mapper<User, UserModel> {
    override fun transform(from: User): UserModel =
            UserModel(
                name = from.name,
                screenName = from.screenName,
                createdAt = from.createdAt.toDate(),
                image = from.image,
                imageBackground = from.imageBackground
            )
}