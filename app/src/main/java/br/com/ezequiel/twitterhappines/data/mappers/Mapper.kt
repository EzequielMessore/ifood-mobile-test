package br.com.ezequiel.twitterhappines.data.mappers

interface Mapper<in From, To> {
    fun transform(from: From): To
    fun transform(from: List<From>): List<To> =
            from.mapTo(mutableListOf()) { transform(it) }

}