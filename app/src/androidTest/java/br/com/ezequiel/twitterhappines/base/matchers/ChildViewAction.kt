package br.com.ezequiel.twitterhappines.base.matchers

import android.support.annotation.IdRes
import android.support.test.espresso.UiController
import android.support.test.espresso.ViewAction
import android.view.View
import org.hamcrest.Matcher


object ChildViewAction {
    fun clickChildViewWithId(@IdRes id: Int) =
        object : ViewAction {
            override fun getDescription(): String = "Click on a child view with specified $id"

            override fun getConstraints(): Matcher<View>? = null

            override fun perform(uiController: UiController?, view: View?) {
                val v = view?.findViewById<View>(id)
                v?.performClick()
            }

        }

}