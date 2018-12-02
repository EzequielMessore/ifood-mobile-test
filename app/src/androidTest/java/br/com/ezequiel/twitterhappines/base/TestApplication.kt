package br.com.ezequiel.twitterhappines.base

import br.com.ezequiel.twitterhappines.AndroidApplication
import br.com.ezequiel.twitterhappines.base.mocks.MockNetworkModule
import br.com.ezequiel.twitterhappines.core.di.ApplicationModule
import br.com.ezequiel.twitterhappines.core.di.component.ApplicationComponent
import br.com.ezequiel.twitterhappines.core.di.component.DaggerApplicationComponent

class TestApplication : AndroidApplication() {

    override fun createComponent(): ApplicationComponent {
        return DaggerApplicationComponent
            .builder()
            .application(this)
            .appModule(ApplicationModule(this))
            .networkModule(MockNetworkModule())
            .build()
    }
}