package br.com.ezequiel.twitterhappines.presentation.user

import android.support.test.filters.MediumTest
import android.support.test.runner.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@MediumTest
class UserActivityTest {
    private lateinit var robot: UserRobot

    @Before
    fun setUp() {
        robot = UserRobot()
    }

    @After
    fun tearDown() {
        robot.destroy()
    }

    @Test
    fun test_IfIsAInvalid_Username() {
        robot
            .injectUser404()
            .start()
            .setText("¥€€")
            .clickInSearch()
            .checkUserNotFound()
    }

    @Test
    fun test_IfIsAValid_Username() {
        robot
            .injectUser200()
            .start()
            .setText("globoesportcom")
            .clickInSearch()
    }

    @Test
    fun test_Unknown500() {
        robot
            .injectUnknown500()
            .start()
            .setText("globoesportcom")
            .clickInSearch()
            .checkErrorIsVisible()
    }
}