package com.zenjob.android.browsr.movies

import android.view.View
import androidx.test.espresso.Espresso
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.zenjob.android.browsr.R
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
@HiltAndroidTest
class MoviesActivityTest {
    /**
     * Use [ActivityScenarioRule] to create and launch the activity under test before each test,
     * and close it after each test. This is a replacement for
     * [androidx.test.rule.ActivityTestRule].
     */
    private var activityScenarioRule = activityScenarioRule<MoviesActivity>()
    private val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val rule: RuleChain = RuleChain
        .outerRule(hiltRule)
        .around(activityScenarioRule)

    @Test
    fun moviesLoaded() {
        Espresso.onView(isRoot()).perform(waitFor())
        Espresso.onView(
            Matchers.allOf(
                withId(R.id.recylcer_view),
                isDisplayed()
            )
        )
        Espresso.onView(withId(R.id.recylcer_view))
            .check(ViewAssertions.matches(isDisplayed()))
    }

    private fun waitFor(delay: Long = 2000): ViewAction {

        return object : ViewAction {
            override fun getConstraints(): Matcher<View> = isRoot()
            override fun getDescription(): String = "wait for $delay milliseconds"
            override fun perform(uiController: UiController, v: View?) {
                uiController.loopMainThreadForAtLeast(delay)
            }
        }
    }
}