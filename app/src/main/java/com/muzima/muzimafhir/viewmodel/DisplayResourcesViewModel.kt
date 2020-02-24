package com.muzima.muzimafhir.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.muzima.muzimafhir.activity.ResourceListEntry
import com.muzima.muzimafhir.data.fhir.Patient
import com.muzima.muzimafhir.data.fhir.Person
import com.muzima.muzimafhir.data.fhir.Location
import com.muzima.muzimafhir.data.fhir.Observation
import com.muzima.muzimafhir.fhir.dao.ObservationDao
import com.muzima.muzimafhir.fhir.dao.PatientDao
import com.muzima.muzimafhir.fhir.dao.PersonDao
import com.muzima.muzimafhir.fhir.dao.LocationDao
import com.muzima.muzimafhir.fhir.dao.implementation.ObservationDaoImpl
import com.muzima.muzimafhir.fhir.dao.implementation.PatientDaoImpl
import com.muzima.muzimafhir.fhir.dao.implementation.PersonDaoImpl
import com.muzima.muzimafhir.fhir.dao.implementation.LocationDaoImpl
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/***
 *   A class containing the data and business logic required for the view to perform its tasks.
 *   A ViewModel class must be registered with an activity, and survives configuration changes
 *   and lifecycle events. Do not reference this class from within a lifecycle event.
 */
class DisplayResourcesViewModel : ViewModel() {

    // Available resource that should be visible in the activity spinner.
    val availableResources = listOf("Person", "Patient", "Observation", "Encounter", "Location")
    val TAG = "DisplayResourcesViewModel" // Debugging tag
    private val personDao: PersonDao // DAO interface
    private val patientDao: PatientDao
    private val observationDao: ObservationDao
    private val locationDao: LocationDao

    // LiveData lets the registered activity listen for changes in the dataset.
    // Call 'postValue' to update the dataset and notify all listeners.
    // postValue must be called asynchronously.
    val entries: MutableLiveData<MutableList<ResourceListEntry>> = MutableLiveData()

    init {
        personDao = PersonDaoImpl()
        patientDao = PatientDaoImpl()
        observationDao = ObservationDaoImpl()
        locationDao = LocationDaoImpl()
        getPersonList()
        getPerson()
        getLocationList()
        getLocation()
        getPatientList()
        Log.d(TAG, "viewModel created")
    }

    /**
     *  Get a location by launching a coroutine within the scope of the application's lifespan
     */
    private fun getLocation() = GlobalScope.launch {
        locationDao.getLocation("5e4a77cc58b312549ee0e1c9")
    }

    /**
     *  Get a list of people by launching a coroutine within the scope of the application's lifespan
     *  Maps the result of the operation to the entries dataset.
     */
    private fun getLocationList() = GlobalScope.launch {
        Log.d(TAG, "getLocationList called")
        val locations = locationDao.getLocationList()
        val locationEntries = locationMapToResourceEntry(locations)
        Log.d(TAG, "getLocationList returned ${locationEntries.size} items")
        entries.postValue(locationEntries)
    }

    /**
     * Maps a list of people to a list of entries.
     * NB: Likely doesn't behave as expected due to all the nullable fields in a location object.
     */
    private fun locationMapToResourceEntry(people: List<Location>): MutableList<ResourceListEntry> {
        val entries = mutableListOf<ResourceListEntry>()
        people.forEach { location ->
            val locationMap = location.mGetFieldsAndValues()
            val title = "Location Instance"
            val entry = ResourceListEntry(title, locationMap.keys.toList(),
                    locationMap.values.toList() as List<String>)
            entries.add(entry)
        }
        return entries
    }

    /**
     *  Get a person by launching a coroutine within the scope of the application's lifespan
     */
    private fun getPerson() = GlobalScope.launch {
        personDao.getPerson("5dcaa7f02eb0b70e90761396")
    }

    /**
     *  Get a list of people by launching a coroutine within the scope of the application's lifespan
     *  Maps the result of the operation to the entries dataset.
     */
    private fun getPersonList() = GlobalScope.launch {
        Log.d(TAG, "getPersonList called")
        val people = personDao.getPersonList()
        val personEntries = personMapToResourceEntry(people)
        Log.d(TAG, "getPersonList returned ${personEntries.size} items")
        entries.postValue(personEntries)
    }

    /**
     * Maps a list of people to a list of entries.
     * NB: Likely doesn't behave as expected due to all the nullable fields in a person object.
     */
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

    /**
     *  Get an patient by launching a coroutine within the scope of the application's lifespan
     */
    private fun getPatient() = GlobalScope.launch {
        patientDao.getPatient("5e2eb69b21c7a2122726889f")
    }

    /**
     *  Get a list of patients by launching a coroutine within the scope of the application's lifespan
     *  Maps the result of the operation to the entries dataset.
     */
    private fun getPatientList() = GlobalScope.launch {
        Log.d(TAG, "getPatientList called")
        val patients = patientDao.getPatientList()
        val patientEntries = patientMapToResourceEntry(patients)
        Log.d(TAG, "getPatientList returned ${patientEntries.size} items")
        entries.postValue(patientEntries)
    }

    /**
     * Maps a list of patients to a list of entries.
     * NB: Likely doesn't behave as expected due to all the nullable fields in a patient object.
     */
    private fun patientMapToResourceEntry(patients: List<Patient>): MutableList<ResourceListEntry> {
        val entries = mutableListOf<ResourceListEntry>()
        patients.forEach { patient ->
            val patientMap = patient.mGetFieldsAndValues()
            val title = "Patient Instance"
            val entry = ResourceListEntry(title, patientMap.keys.toList(),
                    patientMap.values.toList() as List<String>)
            entries.add(entry)
        }
        return entries
    }

    /**
     *  Get an observation by launching a coroutine within the scope of the application's lifespan
     */
    private fun getObservation() = GlobalScope.launch {
        observationDao.getObservation("5e45550f58b312549ee0e1c3")
    }

    /**
     *  Get a list of observations by launching a coroutine within the scope of the application's lifespan
     *  Maps the result of the operation to the entries dataset.
     */
    private fun getObservationList() = GlobalScope.launch {
        Log.d(TAG, "getObservationList called")
        val observations = observationDao.getObservationList()
        val observationEntries = observationMapToResourceEntry(observations)
        Log.d(TAG, "getObservationList returned ${observationEntries.size} items")
        entries.postValue(observationEntries)
    }

    /**
     * Maps a list of patients to a list of entries.
     * NB: Likely doesn't behave as expected due to all the nullable fields in a patient object.
     */
    private fun observationMapToResourceEntry(observations: List<Observation>): MutableList<ResourceListEntry> {
        val entries = mutableListOf<ResourceListEntry>()
        observations.forEach { observation ->
            val observationMap = observation.mGetFieldsAndValues()
            val title = "Observation Instance"
            val entry = ResourceListEntry(title, observationMap.keys.toList(),
                    observationMap.values.toList() as List<String>)
            entries.add(entry)
        }
        return entries
    }


    /**
     * Replaces the viewmodel dataset with the argument dataset.
     */
    fun replaceDataset(mEntries: MutableList<ResourceListEntry>) = GlobalScope.launch {
        entries.postValue(mEntries)
    }

    fun getSelectedResource(resourceName: String){
        entries.postValue(mutableListOf())
        when(resourceName){
            "Person" -> getPersonList()
            "Patient" -> getPatientList()
            "Observation" -> getObservationList()
        }
    }

}