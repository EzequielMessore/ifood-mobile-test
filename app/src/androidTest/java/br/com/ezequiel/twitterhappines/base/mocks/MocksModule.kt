package br.com.ezequiel.twitterhappines.base.mocks

import br.com.ezequiel.twitterhappines.core.di.NetworkModule

class MockNetworkModule : NetworkModule() {
    private val url = "http://localhost:36004/"

    override fun provideUrlTwitter(): String = url

    override fun provideUrGooogleLanguage(): String = url

}