package com.muzima.muzimafhir.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.muzima.muzimafhir.R
import com.muzima.muzimafhir.fhir.dao.PersonDao
import com.muzima.muzimafhir.fhir.dao.implementation.PersonDaoImpl
import com.muzima.muzimafhir.viewmodel.DisplayResourcesViewModel
import kotlinx.android.synthetic.main.activity_display_resources.*

class DisplayResourcesActivity : AppCompatActivity() {

    val TAG = "DisplayResourcesActivity"

    private lateinit var personDao: PersonDao

    lateinit var recyclerView: RecyclerView
    lateinit var viewAdapter: ResourceListDisplayAdapter
    lateinit var layoutManager: LinearLayoutManager
    lateinit var resourceSpinner: Spinner
    lateinit var resourceSpinnerAdapter: SpinnerAdapter
    lateinit var spinnerArray: Array<String>
    //lateinit var viewModel: DisplayResourcesViewModel

    val viewModel: DisplayResourcesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_resources)

        spinnerArray = arrayOf("EMPTY", "Person", "Patient", "Observation", "Location", "Encounter", "Practitioner")

        resourceSpinner = select_resource_spinner
        resourceSpinnerAdapter = ArrayAdapter<String>(this, resourceSpinner.id, spinnerArray)

        ArrayAdapter.createFromResource(
                this,
                R.array.resources_array,
                android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            resourceSpinner.adapter = adapter
        }

        resourceSpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent:AdapterView<*>, view: View, position: Int, id: Long){
                Log.d(TAG, "Spinner index $position selected")
                onSpinnerItemSelected(spinnerArray[position])
            }

            override fun onNothingSelected(parent: AdapterView<*>){
                // Another interface callback
            }
        }

        personDao = PersonDaoImpl()

        recyclerView = resource_recyclerview
        viewAdapter = ResourceListDisplayAdapter(this)
        layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = viewAdapter
        recyclerView.layoutManager = layoutManager

        viewModel.entries.observe(this, Observer {
            entries -> viewAdapter.setData(entries)
            if(entries.size == 0) textview_empty_list.visibility = View.VISIBLE
            else textview_empty_list.visibility = View.GONE
        })
    }

    private fun onSpinnerItemSelected(value: String){
        viewModel.getSelectedResource(value)
    }
}
