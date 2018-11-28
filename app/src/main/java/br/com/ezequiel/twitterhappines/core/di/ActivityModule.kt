package br.com.ezequiel.twitterhappines.core.di

import br.com.ezequiel.twitterhappines.presentation.user.UserActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract fun contributeUserActivity(): UserActivity
}