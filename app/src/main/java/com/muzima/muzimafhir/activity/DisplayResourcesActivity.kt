package com.muzima.muzimafhir.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

    private lateinit var personDao: PersonDao

    lateinit var recyclerView: RecyclerView
    lateinit var viewAdapter: ResourceListDisplayAdapter
    lateinit var layoutManager: LinearLayoutManager
    //lateinit var viewModel: DisplayResourcesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_resources)

        personDao = PersonDaoImpl()
        val viewModel: DisplayResourcesViewModel by viewModels()

        recyclerView = resource_recyclerview
        viewAdapter = ResourceListDisplayAdapter(this)
        layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = viewAdapter
        recyclerView.layoutManager = layoutManager

        viewModel.entries.observe(this, Observer {
            entries -> viewAdapter.setData(entries)
        })
    }
}
