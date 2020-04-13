package com.muzima.muzimafhir.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import com.muzima.muzimafhir.R
import com.muzima.muzimafhir.fhir.client.AppClient
import com.muzima.muzimafhir.data.fhir.Observation
import kotlinx.android.synthetic.main.activity_observation.*
import graphqlcontent.type.CodeableConcept_Input
import graphqlcontent.type.Coding_Input
import graphqlcontent.type.Observation_Enum_input
import graphqlcontent.type.Observation_Input
import java.time.LocalDateTime
import java.util.*

class ObservationActivity : AppCompatActivity() {

    var observations = mutableListOf<Observation>()
    lateinit var mAdapter: ObservationViewAdapter
    lateinit var mLayoutManager: LinearLayoutManager
    lateinit var getObservation : Button
    lateinit var createObservation : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_observation)

        getObservation = getObservationBtn
        getObservation.setOnClickListener {
            getObservation()
        }

        createObservation = ObservationCreateBtn
        createObservation.setOnClickListener {
            createObservation()
        }

        mLayoutManager = LinearLayoutManager(this)
        var recyclerView = recyclerview_observation
        mAdapter = ObservationViewAdapter(observations)
        recyclerView.adapter = mAdapter
        recyclerView.layoutManager = mLayoutManager

    }

    private fun callbackFunc(result: String, o: Observation){
        runOnUiThread {
            // Stuff that updates the UI
            println("Callback function called!")
            println(result)
            observations.add(o)
            mAdapter.notifyDataSetChanged()
        }
    }

    private fun getObservation() {
        var appClient = AppClient()
        appClient.getObservation("5e45550f58b312549ee0e1c3") {
            s: String, o: Observation -> callbackFunc(s, o)
        }
    }

    private fun createObservation() {

        var oResourceType : Observation_Enum_input = Observation_Enum_input.OBSERVATION

        var codingList = mutableListOf<Coding_Input>()
        var codingInput = Coding_Input
                .builder()
                .display("Display string")
                .build()

        codingList.add(codingInput)

        var code = CodeableConcept_Input
                .builder()
                .text("A code text")
                .coding(codingList)
                .build()
        //val dateTime = LocalDateTime.now()

        var oInput = Observation_Input
                .builder()
                .resourceType(oResourceType)
                .code(code)
                .status("final")
                //.valueString("Good condition")
                .valueInteger(1337)
                //.valueDateTime(dateTime)
                .build()

        var appClient = AppClient()
        appClient.createObservation(oInput) { s: String, o: Observation -> callbackFunc(s, o)}
    }
}
