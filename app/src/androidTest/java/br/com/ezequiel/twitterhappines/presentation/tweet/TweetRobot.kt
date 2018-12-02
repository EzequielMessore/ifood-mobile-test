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
        server.url("/")
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

    fun checkHasItemList() = apply {
        checkTextIsVisible("@Goncalojr93 Curte fazer um tweet em")
    }

    fun checkUsername() = apply {
        checkTextIsVisible("Ezequiel")
        checkTextIsVisible("| @ZicaMessore")
    }


}