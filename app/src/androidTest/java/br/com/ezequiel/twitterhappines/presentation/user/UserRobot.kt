package br.com.ezequiel.twitterhappines.presentation.user

import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.rule.ActivityTestRule
import br.com.ezequiel.twitterhappines.R
import br.com.ezequiel.twitterhappines.base.BaseTestRobot
import br.com.ezequiel.twitterhappines.base.extensions.getJson
import br.com.ezequiel.twitterhappines.base.extensions.inject
import okhttp3.mockwebserver.MockWebServer

class UserRobot(
    private val server: MockWebServer = MockWebServer()
) : BaseTestRobot() {

    private val activityRule = ActivityTestRule(UserActivity::class.java, false, false)
    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    init {
        server.start(36004)
        server.url("/")
    }

    fun destroy() {
        server.shutdown()
    }

    fun start() = apply {
        activityRule.launchActivity(Intent())
    }

    fun injectUser200() = apply {
        server.inject(200, context getJson R.raw.user404)
    }

    fun injectUser404() = apply {
        server.inject(404, context getJson R.raw.user200)
    }

    fun injectUnknown500() = apply {
        server.inject(500, "")
    }

    fun setText(text: String) = apply {
        fillEditText(R.id.it_search, text)
    }

    fun clickInSearch() = apply {
        clickView(R.id.iv_search)
    }

    fun checkUserNotFound() = apply {
        checkTextIsVisible(context.getString(R.string.label_user_not_found))
    }

    fun checkErrorIsVisible() = apply {
        checkIdIsVisible(R.id.iv_error)
        checkIdIsVisible(R.id.tv_title)
        checkIdIsVisible(R.id.btn_try_again)
    }
}