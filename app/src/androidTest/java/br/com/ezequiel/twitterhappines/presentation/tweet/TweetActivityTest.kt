package br.com.ezequiel.twitterhappines.presentation.tweet

import android.support.test.runner.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TweetActivityTest {
    private lateinit var robot: TweetRobot

    @Before
    fun setUp() {
        robot = TweetRobot()
    }

    @After
    fun tearDown() {
        robot.destroy()
    }

    @Test
    fun testWhetherTheListOfUserTweetsIsLoaded() {
        robot
            .injectTweets200()
            .start()
            .checkUsername()
            .checkHasItemList()
    }

    @Test
    fun testShouldTheHappyMoodAppear() {
        robot
            .injectTweets200()
            .injectHumorHappy()
            .start()
            .clickInAnalyse()
            .checkHappyHumorIsVisible()
    }


    @Test
    fun test_Unknown500() {
        robot
            .injectUnknown500()
            .start()
            .checkErrorIsVisible()
    }

}