package com.muzima.muzimafhir.activity

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

        // Get a single patient or patient list
        if (intent?.action == "com.muzima.muzimafhir.ACTION_REQUEST_RESOURCE") {
            if (intent.getStringExtra("resourceType") == "patient"
                    && intent.getStringExtra("queryType") == "getOne") {

                var id = intent.getStringExtra("id")
                var resultIntent = getPatientWithIntent(id)
                setResult(RESULT_OK, resultIntent)
                finish()
            }
            if (intent.getStringExtra("resourceType") == "patient"
                    && intent.getStringExtra("queryType") == "getAll") {

                var resultIntent = getPatientListWithIntent()
                setResult(RESULT_OK, resultIntent)
                finish()
            }
        }
    }

    private fun getPatientWithIntent(id: String): Intent {
        lateinit var patient: Patient
        var resultIntent = Intent()

        runBlocking {
            patient = patientDao.getPatient(id)
        }
        var patientJson = gson.toJson(patient)

        resultIntent.putExtra("resource", patientJson)
        resultIntent.putExtra("queryType", "getOne")
        resultIntent.putExtra("resourceType", "patient")
        return resultIntent
    }

    private fun getPatientListWithIntent(): Intent {
        lateinit var patientList: List<Patient>
        var resultIntent = Intent()

        runBlocking {
            patientList = patientDao.getPatientList()
        }
        var patientListJson = gson.toJson(patientList)

        resultIntent.putExtra("resource", patientListJson)
        resultIntent.putExtra("queryType", "getAll")
        resultIntent.putExtra("resourceType", "patient")
        return resultIntent
    }
}
