package com.muzima.muzimafhir.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import com.muzima.muzimafhir.R
import com.muzima.muzimafhir.fhir.client.AppClient
import com.muzima.muzimafhir.data.fhir.Patient
import kotlinx.android.synthetic.main.activity_patient.*
import graphqlcontent.type.HumanName_Input
import graphqlcontent.type.Patient_Enum_input
import graphqlcontent.type.Patient_Input
import graphqlcontent.type.Person_Enum_input

class PatientActivity : AppCompatActivity() {

    var patients = mutableListOf<Patient>()
    lateinit var mAdapter: PatientViewAdapter
    lateinit var mLayoutManager: LinearLayoutManager
    lateinit var getPatientBtn: Button
    lateinit var createPatientBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient)

        getPatientBtn = getPatientButton
        getPatientBtn.setOnClickListener {
            getPatient()
        }

        createPatientBtn = createPatientButton
        createPatientBtn.setOnClickListener {
            createPatient()
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

    private fun getPatient() {
        //var p1: Patient = Patient(null, null, null, "male", null, null, null, null)
        //patients.add(p1)
        //mAdapter.notifyDataSetChanged()
        var appClient = AppClient()
        appClient.getPatient("5e2eb69b21c7a2122726889f") { s: String, p: Patient -> callbackFunc(s, p) }
    }

    private fun createPatient() {

        var pResourceType : Patient_Enum_input = Patient_Enum_input.PATIENT

        // Test of adding given and family names of type HumanName_Input
        var givenNames = mutableListOf<String>()
        givenNames.add("Klara")
        givenNames.add("Tora")

        var familyName = HumanName_Input
                .builder()
                .family("Fleksnes")
                .given(givenNames)
                .build()

        var names = mutableListOf<HumanName_Input>()
        names.add(familyName)

        var pInput = Patient_Input
                .builder()
                .resourceType(pResourceType)
                .gender("female")
                .name(names)
                .build()

        var appClient = AppClient()
        appClient.createPatient(pInput){ s: String, p: Patient -> callbackFunc(s, p)}
    }

}