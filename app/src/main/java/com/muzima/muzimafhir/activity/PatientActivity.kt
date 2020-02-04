package com.muzima.muzimafhir.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import com.muzima.muzimafhir.R
import com.muzima.muzimafhir.data.AppClient
import com.muzima.muzimafhir.data.Patient
import kotlinx.android.synthetic.main.activity_patient.*

class PatientActivity : AppCompatActivity() {

    var patients = mutableListOf<Patient>()
    lateinit var mAdapter: PatientViewAdapter
    lateinit var mLayoutManager: LinearLayoutManager
    lateinit var generatePatient: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient)

        generatePatient = generatePatientBtn
        generatePatient.setOnClickListener {
            generatePatient()
        }
        mLayoutManager = LinearLayoutManager(this)
        var recyclerView = recyclerview_Patient
        mAdapter = PatientViewAdapter(patients)
        recyclerView.adapter = mAdapter
        recyclerView.layoutManager = mLayoutManager
    }


    override fun onStart() {
        super.onStart()
        var appClient = AppClient()
        //appClient.getPatient("5dcaa7f02eb0b70e90761396") { s: String, p: Patient -> callbackFunc(s, p)}

    }

    private fun callbackFunc(result: String, p: Patient) {
        runOnUiThread {
            // Stuff that updates the UI
            println("Callback function called!")
            println(result)
            patients.add(p)
            mAdapter.notifyDataSetChanged()
        }
    }

    private fun generatePatient() {
        //var p1: Patient = Patient(null, null, null, "male", null, null, null, null)
        //patients.add(p1)
        //mAdapter.notifyDataSetChanged()
        var appClient = AppClient()
        appClient.getPatient("5e2eb69b21c7a2122726889f") { s: String, p: Patient -> callbackFunc(s, p) }
    }

}