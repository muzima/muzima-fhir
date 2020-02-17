package com.muzima.muzimafhir.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import com.muzima.muzimafhir.R
import com.muzima.muzimafhir.fhir.client.AppClient
import com.muzima.muzimafhir.data.fhir.Encounter
import kotlinx.android.synthetic.main.activity_encounter.*
import typeFixFolder.type.Coding_Input
import typeFixFolder.type.Encounter_Enum_input
import typeFixFolder.type.Encounter_Input

class EncounterActivity : AppCompatActivity() {

    var encounters = mutableListOf<Encounter>()
    lateinit var mAdapter: EncounterViewAdapter
    lateinit var mLayoutManager: LinearLayoutManager
    lateinit var getEncounter: Button
    lateinit var createEncounter: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_encounter)

        getEncounter = getEncounterBtn
        getEncounter.setOnClickListener{
            getEncounter()
        }

        createEncounter = createEncounterBtn
        createEncounter.setOnClickListener{
            createEncounter()
        }

        mLayoutManager = LinearLayoutManager(this)
        var recyclerView = recyclerview_Encounter
        mAdapter = EncounterViewAdapter(encounters)
        recyclerView.adapter = mAdapter
        recyclerView.layoutManager = mLayoutManager
    }

    private fun callbackFunc(result: String, p: Encounter) {
        runOnUiThread {
            // Stuff that updates the UI
            println("Callback function called!")
            println(result)
            encounters.add(p)
            mAdapter.notifyDataSetChanged()
        }
    }
    private fun getEncounter() {
        //var e1: Encounter = Encounter(null)
        //encounters.add(e1)
        //mAdapter.notifyDataSetChanged()
        var appClient = AppClient()
        appClient.getEncounter("5e414ec558b312549ee0e1c0") { s: String, e: Encounter -> callbackFunc(s, e) }
    }

    private fun createEncounter(){
        var eResourceType : Encounter_Enum_input = Encounter_Enum_input.ENCOUNTER
        var eClass = Coding_Input
                .builder()
                .display("hello")
                .build()

        var eInput = Encounter_Input
                .builder()
                .resourceType(eResourceType)
                .status("arrived")
                .class_(eClass)
                .build()

        var appClient = AppClient()
        appClient.createEncounter(eInput){s:String, e: Encounter -> callbackFunc(s,e)}
    }

}
