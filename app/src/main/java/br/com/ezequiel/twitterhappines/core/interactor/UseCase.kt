package br.com.ezequiel.twitterhappines.core.interactor

abstract class UseCase<in Params, out Type> where Type : Any {

    abstract fun run(params: Params): Type

    operator fun invoke(params: Params): Type = run(params)

    class None
}