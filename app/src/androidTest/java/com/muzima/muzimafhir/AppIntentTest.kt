package com.muzima.muzimafhir

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers.toPackage
import androidx.test.espresso.intent.rule.IntentsTestRule
import com.muzima.muzimafhir.activity.IntentActivity
import com.muzima.muzimafhir.intenthandling.IntentHandler
import org.junit.Rule
import org.junit.Test

class AppIntentTest {

    @get:Rule
    val intentsTestRule = IntentsTestRule(IntentActivity::class.java)

    @Test
    fun muzimaPatientCanBeInserted(){
        // Build the result to return when the activity is launched.
        val resultData = Intent()
        resultData.action = "com.muzima.muzimafhir.ACTION_INSERT_RESOURCE"
        resultData.putExtra("resourceType", "patient")
        resultData.putExtra("resource", PatientData.patient1)

        val result = Instrumentation.ActivityResult(Activity.RESULT_OK, resultData)

        // Set up result stubbing when an intent sent to "contacts" is seen.
        intending(toPackage("com.muzima.muzimafhir")).respondWith(result)
    }
}