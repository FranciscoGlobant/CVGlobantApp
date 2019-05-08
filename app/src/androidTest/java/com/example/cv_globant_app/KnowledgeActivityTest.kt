package com.example.cv_globant_app

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import com.example.cv_globant_app.experience.ExperienceActivity
import com.example.cv_globant_app.knowledge.KnowledgeActivity
import org.junit.Rule
import org.junit.Test

class KnowledgeActivityTest {

    @get:Rule
    var activityRule: ActivityTestRule<KnowledgeActivity>
            = ActivityTestRule(KnowledgeActivity::class.java)

    @Test
    fun whenNextButtonClicked_ExperienceActivityIsLaunched() {
        Intents.init()
        Espresso.onView(ViewMatchers.withId(R.id.btn_next_knowledge)).perform(ViewActions.click())
        Intents.intended(IntentMatchers.hasComponent(ExperienceActivity::class.java.name))
        Intents.release()
    }
}