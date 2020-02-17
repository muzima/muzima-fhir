package com.muzima.muzimafhir.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import com.muzima.muzimafhir.R
import com.muzima.muzimafhir.fhir.client.AppClient
import com.muzima.muzimafhir.data.fhir.Observation
import kotlinx.android.synthetic.main.activity_observation.*
import typeFixFolder.type.CodeableConcept_Input
import typeFixFolder.type.Observation_Enum_input
import typeFixFolder.type.Observation_Input

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
        appClient.getObservation("5dcd09d3ef744e3870c8d7d4") {
            s: String, o: Observation -> callbackFunc(s, o)
        }
    }

    private fun createObservation() {

        var oResourceType : Observation_Enum_input = Observation_Enum_input.OBSERVATION

        var code = CodeableConcept_Input
                .builder()
                .text("A code text")
                .build()

        var oInput = Observation_Input
                .builder()
                .resourceType(oResourceType)
                .code(code)
                .status("Gucci")
                .valueString("Good condition")
                .valueInteger(1337)
                .build()

        var appClient = AppClient()
        appClient.createObservation(oInput) { s: String, o: Observation -> callbackFunc(s, o)}
    }
}
