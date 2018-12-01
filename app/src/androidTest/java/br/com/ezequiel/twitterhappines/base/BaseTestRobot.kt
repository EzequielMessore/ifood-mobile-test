package br.com.ezequiel.twitterhappines.base

import android.support.annotation.IdRes
import android.support.design.widget.Snackbar
import android.support.test.espresso.Espresso.onData
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.assertion.ViewAssertions.doesNotExist
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.CoreMatchers
import org.hamcrest.Matchers.allOf

open class BaseTestRobot {
    protected fun fillEditText(resId: Int, text: String) = onView(withId(resId)).perform(
        replaceText(text),
        closeSoftKeyboard()
    )

    protected fun clickView(resId: Int) = onView((withId(resId))).perform(click())

    protected fun textView(resId: Int) = onView(withId(resId))

    protected fun matchText(resId: Int, string: Int) =
        textView(resId).check(ViewAssertions.matches(withId(string)))

    protected fun matchText(viewInteraction: ViewInteraction, text: String) =
        viewInteraction.check(ViewAssertions.matches(withText(text)))

    protected fun matchText(resId: Int, text: String) = matchText(textView(resId), text)

    protected fun clickListItem(listRes: Int, position: Int) {
        onData(CoreMatchers.anything())
            .inAdapterView(allOf(withId(listRes)))
            .atPosition(position).perform(click())
    }

    protected fun checkTextIsDisplayedWithDescendant(text: String, @IdRes descendantId: Int) {
        onView(CoreMatchers.allOf(withText(text), isDescendantOfA(withId(descendantId))))
    }

    protected fun checkTextIsNotDisplayed(text: String) {
        onView(withText(text)).check(doesNotExist())
    }

    protected fun checkTextIsVisible(text: String) {
        onView(withText(text)).check(matches(isDisplayed()))
    }

    protected fun checkIdIsVisible(@IdRes id: Int) {
        onView(withId(id)).check(matches(isDisplayed()))
    }

    protected fun checkHintIsVisible(text: String) {
        onView(withHint(text)).check(matches(isDisplayed()))
    }

    protected fun checkSnackbar(text: String) {
        withText(text).matches(
            allOf(
                isDescendantOfA(isAssignableFrom(Snackbar.SnackbarLayout::class.java)),
                isCompletelyDisplayed()
            )
        )
    }
}
