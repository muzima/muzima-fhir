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

class IntentActivity : AppCompatActivity() {

    lateinit var patientDao: PatientDaoImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent)
        var gson = Gson()

        patientDao = PatientDaoImpl()

        when {
            intent?.action == "com.muzima.muzimafhir.ACTION_REQUEST_RESOURCE" -> {
                if (intent.getStringExtra("resourceType") == "patient"
                        && intent.getStringExtra("queryType") == "getOne") {
                    var resultIntent = Intent()

                    var patient = Patient() //getPatient()
                    patient.gender = "Apache Heli"
                    patient.birthDate = Date(1994, 6, 20)

                    var patientJson = gson.toJson(patient)

                    resultIntent.putExtra("resource", patientJson)
                    resultIntent.putExtra("queryType", "getOne")
                    resultIntent.putExtra("resourceType", "patient")
                    setResult(RESULT_OK, resultIntent)
                    finish()
                }
            }
            else -> {
            }
        }

    }

    /**
     *  Get an patient by launching a coroutine within the scope of the application's lifespan
     */
    private fun getPatient() = GlobalScope.launch {
        patientDao.getPatient("5e2eb69b21c7a2122726889f")
    }
}
