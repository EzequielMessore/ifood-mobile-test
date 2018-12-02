package br.com.ezequiel.twitterhappines.presentation.tweet

import android.support.test.InstrumentationRegistry
import android.support.test.rule.ActivityTestRule
import br.com.ezequiel.twitterhappines.R
import br.com.ezequiel.twitterhappines.base.BaseTestRobot
import br.com.ezequiel.twitterhappines.base.extensions.getJson
import br.com.ezequiel.twitterhappines.base.extensions.inject
import br.com.ezequiel.twitterhappines.base.extensions.jsonToObject
import br.com.ezequiel.twitterhappines.data.mappers.UserModelMapper
import br.com.ezequiel.twitterhappines.data.ws.user.User
import okhttp3.mockwebserver.MockWebServer

class TweetRobot(
    private val server: MockWebServer = MockWebServer()
) : BaseTestRobot() {

    private val activityRule = ActivityTestRule(TweetActivity::class.java, false, false)
    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    init {
        server.start(36004)
        server.url("/v1")
    }

    fun destroy() {
        server.shutdown()
    }

    fun start() = apply {
        val user = context getJson R.raw.user200 jsonToObject User::class.java
        val model = UserModelMapper().transform(user)

        activityRule.launchActivity(TweetActivity.newIntent(context, model))
    }

    fun injectTweets200() = apply {
        server.inject(200, context getJson R.raw.tweet200)
    }

    fun injectUnknown500() = apply {
        server.inject(500, "")
    }

    fun injectHumorHappy() = apply {
        server.inject(200, context getJson R.raw.happy_humor)
    }

    fun checkHasItemList() = apply {
        checkTextIsDisplayedWithDescendant("@Goncalojr93 Curte fazer um tweet em", R.id.rv_tweets)
    }

    fun checkUsername() = apply {
        checkTextIsVisible("Ezequiel")
        checkTextIsVisible("| @ZicaMessore")
    }

    fun clickInAnalyse() = apply {
        clickChildViewRecyclerView(R.id.rv_tweets, R.id.v_analise)
    }

    fun checkHappyHumorIsVisible() {
        checkTextIsDisplayedWithDescendant("Happy Tweet", R.id.rv_tweets)
    }

    fun checkErrorIsVisible() = apply {
        checkIdIsVisible(R.id.iv_error)
        checkIdIsVisible(R.id.tv_title)
        checkIdIsVisible(R.id.btn_try_again)
    }

}