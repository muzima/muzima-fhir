package com.muzima.muzimafhir.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.muzima.muzimafhir.R
import com.muzima.muzimafhir.data.AppClient
import com.muzima.muzimafhir.data.Person
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    lateinit var personBtn : Button
    lateinit var patientBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // initialize person activity button
        personBtn = personActivityBtn
        personActivityBtn.setOnClickListener {
            // Handler code here.
            val intent = Intent(this, PersonActivity::class.java)
            startActivity(intent)
        }

        patientBtn = patientActivityBtn
        patientActivityBtn.setOnClickListener{
            val intent = Intent(this, PatientActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        var  appClient = AppClient()
        //appClient.getPerson("5dcaa7f02eb0b70e90761396") { s: String, p: Person -> callbackFunc(s, p)}

    }
}
