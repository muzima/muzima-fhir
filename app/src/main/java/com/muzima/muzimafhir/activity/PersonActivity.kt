package com.muzima.muzimafhir.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import com.muzima.muzimafhir.R
import com.muzima.muzimafhir.fhir.client.AppClient
import com.muzima.muzimafhir.data.fhir.Person
import kotlinx.android.synthetic.main.activity_person.*
import graphqlcontent.type.HumanName_Input
import graphqlcontent.type.Person_Enum_input
import graphqlcontent.type.Person_Input

class PersonActivity : AppCompatActivity() {

    var persons = mutableListOf<Person>()
    lateinit var mAdapter: FhirViewAdapter
    lateinit var mLayoutManager: LinearLayoutManager
    lateinit var getPerson : Button
    lateinit var createPerson : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person)

        getPerson = generatePersonBtn
        getPerson.setOnClickListener{
            getPerson()
        }

        createPerson = PersonCreateBtn
        createPerson.setOnClickListener {
            createPerson()
        }

        mLayoutManager = LinearLayoutManager(this)
        var recyclerView = recyclerview_person
        mAdapter = FhirViewAdapter(persons)
        recyclerView.adapter = mAdapter
        recyclerView.layoutManager = mLayoutManager
    }

    override fun onStart() {
        super.onStart()
        //var  appClient = AppClient()
        //appClient.getPerson("5dcaa7f02eb0b70e90761396") { s: String, p: Person -> callbackFunc(s, p)}

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

    private fun getPerson() {

        var appClient = AppClient()


        appClient.getPerson("5dc2c3b0b16c6834d80bed75") { s: String, p: Person -> callbackFunc(s, p)}
        /*
        var p1: Person = Person(null, null, null, "male", null, null, null, null)
        persons.add(p1)
        mAdapter.notifyDataSetChanged()
         */

    }

    private fun createPerson() {
        var pResourceType : Person_Enum_input = Person_Enum_input.PERSON

        // Test of adding given and family names of type HumanName_Input
        var givenNames = mutableListOf<String>()
        givenNames.add("Kolbein")
        givenNames.add("Toreson")

        var familyName = HumanName_Input
                .builder()
                .family("Foldøy")
                .given(givenNames)
                .build()

        var names = mutableListOf<HumanName_Input>()
        names.add(familyName)

        //var pInput : Person_Input = Person_Input(pResourceType, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null)
        var pInput = Person_Input
                .builder()
                .resourceType(pResourceType)
                .gender("male")
                .name(names)
                .build()

        var appClient = AppClient()
        appClient.createPerson(pInput){ s: String, p: Person -> callbackFunc(s, p)}
    }
}
