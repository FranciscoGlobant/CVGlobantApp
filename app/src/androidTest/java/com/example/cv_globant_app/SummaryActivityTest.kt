package com.example.cv_globant_app

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.example.cv_globant_app.knowledge.KnowledgeActivity
import com.example.cv_globant_app.summary.SummaryActivity
import org.junit.Rule
import org.junit.Test

class SummaryActivityTest {

    @get:Rule
    var activityRule: ActivityTestRule<SummaryActivity>
            = ActivityTestRule(SummaryActivity::class.java)

    @Test
    fun whenNextButtonClicked_KnowledgeActivityIsLaunched() {
        Intents.init()
        onView(withId(R.id.btn_next_summary)).perform(click())
        intended(hasComponent(KnowledgeActivity::class.java.name))
        Intents.release()
    }
}