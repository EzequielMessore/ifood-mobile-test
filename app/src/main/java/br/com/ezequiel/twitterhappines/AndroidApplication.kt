package br.com.ezequiel.twitterhappines

import android.app.Activity
import android.app.Application
import br.com.ezequiel.twitterhappines.core.di.ApplicationModule
import br.com.ezequiel.twitterhappines.core.di.component.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

open class AndroidApplication : Application(), HasActivityInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    open fun createComponent() = DaggerApplicationComponent.builder()
        .application(this)
        .appModule(ApplicationModule(this))
        .build()

    override fun onCreate() {
        super.onCreate()
        createComponent().inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingAndroidInjector
}