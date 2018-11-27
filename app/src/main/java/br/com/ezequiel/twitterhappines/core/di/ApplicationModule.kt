package br.com.ezequiel.twitterhappines.core.di

import android.content.Context
import br.com.ezequiel.twitterhappines.AndroidApplication
import br.com.ezequiel.twitterhappines.core.scheduler.ISchedulersProvider
import br.com.ezequiel.twitterhappines.core.scheduler.SchedulersProvider
import dagger.Module
import dagger.Provides
import dagger.Reusable
import javax.inject.Singleton

@Module
class ApplicationModule(val application: AndroidApplication) {
    @Provides
    @Singleton
    fun provideApplicationContext(): Context = application

    @Provides
    @Reusable
    fun provideSchedulerProvider(schedulersProvider: SchedulersProvider): ISchedulersProvider = schedulersProvider
}