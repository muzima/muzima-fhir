package com.muzima.muzimafhir.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import com.muzima.muzimafhir.R
import com.muzima.muzimafhir.data.AppClient
import com.muzima.muzimafhir.data.Encounter
import kotlinx.android.synthetic.main.activity_encounter.*

class EncounterActivity : AppCompatActivity() {

    var encounters = mutableListOf<Encounter>()
    lateinit var mAdapter: EncounterViewAdapter
    lateinit var mLayoutManager: LinearLayoutManager
    lateinit var getEncounter: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_encounter)

        getEncounter = getEncounterBtn
        getEncounter.setOnClickListener{
            getEncounter()
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

}
