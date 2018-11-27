package br.com.ezequiel.twitterhappines.core.di.component

import android.app.Application
import br.com.ezequiel.twitterhappines.AndroidApplication
import br.com.ezequiel.twitterhappines.core.di.ActivityModule
import br.com.ezequiel.twitterhappines.core.di.ApplicationModule
import br.com.ezequiel.twitterhappines.core.di.NetworkModule
import br.com.ezequiel.twitterhappines.core.di.viewmodel.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityModule::class,
        NetworkModule::class,
        ApplicationModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun appModule(appModule: ApplicationModule): Builder

        fun build(): ApplicationComponent
    }

    fun inject(application: AndroidApplication)
}