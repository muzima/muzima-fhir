package com.muzima.muzimafhir.activity

import android.app.Activity
import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import com.muzima.muzimafhir.R
import com.muzima.muzimafhir.data.fhir.Patient
import com.muzima.muzimafhir.fhir.dao.PatientDao
import com.muzima.muzimafhir.fhir.dao.implementation.PatientDaoImpl
import com.muzima.muzimafhir.fhir.query.PatientQuery
import com.muzima.muzimafhir.intenthandling.IntentHandler
import kotlinx.coroutines.*
import java.util.*
import kotlin.coroutines.CoroutineContext

class IntentActivity : AppCompatActivity() {

    private lateinit var patientDao: PatientDao
    private lateinit var gson: Gson

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent)
        gson = Gson()

        patientDao = PatientDaoImpl()

        var intentHandler = IntentHandler()

        var resultIntent = intentHandler.handleIntent(intent)
        if (resultIntent != null) {
            setResult(RESULT_OK, resultIntent)
            finish()
        } else {
            setResult(RESULT_CANCELED)
            finish()
        }
    }
}
