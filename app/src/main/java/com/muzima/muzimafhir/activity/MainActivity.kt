package com.muzima.muzimafhir.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.muzima.muzimafhir.R
import com.muzima.muzimafhir.data.AppClient
import com.muzima.muzimafhir.data.Person
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    var persons = mutableListOf<Person>()
    lateinit var mAdapter: FhirViewAdapter
    lateinit var mLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        mLayoutManager = LinearLayoutManager(this)
        var recyclerView = recyclerview_main
        mAdapter = FhirViewAdapter(persons)
        recyclerView.adapter = mAdapter
        recyclerView.layoutManager = mLayoutManager
    }

    override fun onStart() {
        super.onStart()
        var  appClient = AppClient()
        appClient.getPerson("5dcaa7f02eb0b70e90761396") { s: String, p: Person -> callbackFunc(s, p)}
    }

    private fun callbackFunc(result: String, p: Person){
        runOnUiThread {
            // Stuff that updates the UI
            println("Callback function called!")
            println(result)
            persons.add(p)
            mAdapter.notifyDataSetChanged()
        }
    }
}
