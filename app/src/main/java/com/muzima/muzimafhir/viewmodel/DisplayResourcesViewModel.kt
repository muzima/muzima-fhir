package com.muzima.muzimafhir.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.muzima.muzimafhir.activity.ResourceListEntry
import com.muzima.muzimafhir.data.fhir.Person
import com.muzima.muzimafhir.fhir.dao.PersonDao
import com.muzima.muzimafhir.fhir.dao.implementation.PersonDaoImpl
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DisplayResourcesViewModel : ViewModel() {

    val availableResources = listOf("Person", "Patient", "Observation", "Encounter")
    val TAG = "DisplayResourcesViewModel"
    private val personDao: PersonDao
    val entries: MutableLiveData<MutableList<ResourceListEntry>> = MutableLiveData()

    init {
        personDao = PersonDaoImpl()
        getPersonList()
        getPerson()
        Log.d(TAG, "viewModel created")
    }

    private fun getPerson() = GlobalScope.launch {
        personDao.getPerson("5dcaa7f02eb0b70e90761396")
    }

    private fun getPersonList() = GlobalScope.launch {
        Log.d(TAG, "getPersonList called")
        val people = personDao.getPersonList()
        val personEntries = personMapToResourceEntry(people)
        Log.d(TAG, "getPersonList returned ${personEntries.size} items")
        entries.postValue(personEntries)
    }

    private fun personMapToResourceEntry(people: List<Person>): MutableList<ResourceListEntry> {
        val entries = mutableListOf<ResourceListEntry>()
        people.forEach { person ->
            val personMap = person.mGetFieldsAndValues()
            val title = "Person Instance"
            val entry = ResourceListEntry(title, personMap.keys.toList(),
                    personMap.values.toList() as List<String>)
            entries.add(entry)
        }
        return entries
    }
}