package br.com.ezequiel.twitterhappines.base.runner

import android.app.Application
import android.content.Context
import android.support.test.runner.AndroidJUnitRunner
import br.com.ezequiel.twitterhappines.base.TestApplication

class MockTestRunner : AndroidJUnitRunner() {

    override fun newApplication(cl: ClassLoader?, className: String?, context: Context?): Application {
        return super.newApplication(cl, TestApplication::class.java.name, context)
    }
}