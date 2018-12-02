package br.com.ezequiel.twitterhappines.base.matchers

import android.view.View
import org.hamcrest.BaseMatcher
import org.hamcrest.Description
import org.hamcrest.Factory
import org.hamcrest.Matcher

class InstanceOfView(private val clazz: Class<out View>) : BaseMatcher<View>() {

    companion object {
        @Factory
        fun instanceOf(type: Class<out View>): Matcher<View> {
            return InstanceOfView(type)
        }
    }

    override fun matches(item: Any): Boolean {
        return clazz.isInstance(item)
    }

    override fun describeTo(description: Description) {
        description.appendText("an instance of ").appendText(clazz.name)
    }
}