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
    fun test_IfLoad_TweetsAUser() {
        robot
            .injectTweets200()
            .start()
            .checkHasItemList()
            .checkUsername()
    }
}