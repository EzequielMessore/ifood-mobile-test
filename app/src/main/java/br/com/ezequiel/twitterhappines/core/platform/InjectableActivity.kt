package br.com.ezequiel.twitterhappines.core.platform

import android.os.Bundle
import dagger.android.AndroidInjection

abstract class InjectableActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }
}