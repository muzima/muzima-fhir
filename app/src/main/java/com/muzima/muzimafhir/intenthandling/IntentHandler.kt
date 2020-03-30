package com.muzima.muzimafhir.intenthandling

import android.content.Intent
import com.google.gson.Gson
import com.muzima.muzimafhir.data.fhir.*
import com.muzima.muzimafhir.fhir.dao.implementation.*
import kotlinx.coroutines.runBlocking

class IntentHandler {

    private var patientDao = PatientDaoImpl()
    private var locationDao = LocationDaoImpl()
    private var encounterDao = EncounterDaoImpl()
    private var observationDao = ObservationDaoImpl()
    private var practitionerDao = PractitionerDaoImpl()

    private var gson = Gson()
    var resultIntent = Intent()

    fun handleIntent(intent: Intent): Intent? {
        if (intent?.action == "com.muzima.muzimafhir.ACTION_REQUEST_RESOURCE") {
            // Patient
            if (intent.getStringExtra("resourceType") == "patient") {
                if (intent.getStringExtra("queryType") == "getOne") {
                    var id = intent.getStringExtra("id")
                    resultIntent = getPatientWithIntent(id)
                    return resultIntent
                }
                if (intent.getStringExtra("queryType") == "getAll") {
                    resultIntent = getPatientListWithIntent()
                    return resultIntent
                }
            }

            // Practitioner
            if (intent.getStringExtra("resourceType") == "practitioner") {
                if (intent.getStringExtra("queryType") == "getOne") {
                    var id = intent.getStringExtra("id")
                    resultIntent = getPractitionerWithIntent(id)
                    return resultIntent
                }
                if (intent.getStringExtra("queryType") == "getAll") {
                    resultIntent = getPractitionerListWithIntent()
                    return resultIntent
                }
            }

            // Observation
            if (intent.getStringExtra("resourceType") == "observation") {
                if (intent.getStringExtra("queryType") == "getOne") {
                    var id = intent.getStringExtra("id")
                    resultIntent = getObservationWithIntent(id)
                    return resultIntent
                }
                if (intent.getStringExtra("queryType") == "getAll") {
                    resultIntent = getObservationListWithIntent()
                    return resultIntent
                }
            }

            // Encounter
            if (intent.getStringExtra("resourceType") == "encounter") {
                if (intent.getStringExtra("queryType") == "getOne") {
                    var id = intent.getStringExtra("id")
                    resultIntent = getEncounterWithIntent(id)
                    return resultIntent
                }
                if (intent.getStringExtra("queryType") == "getAll") {
                    resultIntent = getEncounterListWithIntent()
                    return resultIntent
                }
            }

            // Location
            if (intent.getStringExtra("resourceType") == "location") {
                if (intent.getStringExtra("queryType") == "getOne") {
                    var id = intent.getStringExtra("id")
                    resultIntent = getLocationWithIntent(id)
                    return resultIntent
                }
                if (intent.getStringExtra("queryType") == "getAll") {
                    resultIntent = getLocationListWithIntent()
                    return resultIntent
                }
            }
        }
        // just an empty result
        return null
    }

    // PATIENT
    private fun getPatientWithIntent(id: String): Intent {
        lateinit var patient: Patient
        var resultIntent = Intent()

        runBlocking {
            patient = patientDao.getPatient(id)
        }
        var patientJson = gson.toJson(patient)

        resultIntent.putExtra("resource", patientJson)
        resultIntent.putExtra("queryType", "getOne")
        resultIntent.putExtra("resourceType", "patient")
        return resultIntent
    }

    private fun getPatientListWithIntent(): Intent {
        lateinit var patientList: List<Patient>
        var resultIntent = Intent()

        runBlocking {
            patientList = patientDao.getPatientList()
        }
        var patientListJson = gson.toJson(patientList)

        resultIntent.putExtra("resource", patientListJson)
        resultIntent.putExtra("queryType", "getAll")
        resultIntent.putExtra("resourceType", "patient")
        return resultIntent
    }

    // PRACTITIONER
    private fun getPractitionerWithIntent(id: String): Intent {
        lateinit var practitioner: Practitioner
        var resultIntent = Intent()

        runBlocking {
            practitioner = practitionerDao.getPractitioner(id)
        }
        var practitionerJson = gson.toJson(practitioner)

        resultIntent.putExtra("resource", practitionerJson)
        resultIntent.putExtra("queryType", "getOne")
        resultIntent.putExtra("resourceType", "practitioner")
        return resultIntent
    }

    private fun getPractitionerListWithIntent(): Intent {
        lateinit var practitionerList: List<Practitioner>
        var resultIntent = Intent()

        runBlocking {
            practitionerList = practitionerDao.getPractitionerList()
        }
        var practitionerListJson = gson.toJson(practitionerList)

        resultIntent.putExtra("resource", practitionerListJson)
        resultIntent.putExtra("queryType", "getAll")
        resultIntent.putExtra("resourceType", "practitioner")
        return resultIntent
    }

    // OBSERVATION
    private fun getObservationWithIntent(id: String): Intent {
        lateinit var observation: Observation
        var resultIntent = Intent()

        runBlocking {
            observation = observationDao.getObservation(id)
        }
        var observationJson = gson.toJson(observation)

        resultIntent.putExtra("resource", observationJson)
        resultIntent.putExtra("queryType", "getOne")
        resultIntent.putExtra("resourceType", "observation")
        return resultIntent
    }

    private fun getObservationListWithIntent(): Intent {
        lateinit var observationList: List<Observation>
        var resultIntent = Intent()

        runBlocking {
            observationList = observationDao.getObservationList()
        }
        var observationListJson = gson.toJson(observationList)

        resultIntent.putExtra("resource", observationListJson)
        resultIntent.putExtra("queryType", "getAll")
        resultIntent.putExtra("resourceType", "observation")
        return resultIntent
    }

    // ENCOUNTER
    private fun getEncounterWithIntent(id: String): Intent {
        lateinit var encounter: Encounter
        var resultIntent = Intent()

        runBlocking {
            encounter = encounterDao.getEncounter(id)
        }
        var encounterJson = gson.toJson(encounter)

        resultIntent.putExtra("resource", encounterJson)
        resultIntent.putExtra("queryType", "getOne")
        resultIntent.putExtra("resourceType", "encounter")
        return resultIntent
    }

    private fun getEncounterListWithIntent(): Intent {
        lateinit var encounterList: List<Encounter>
        var resultIntent = Intent()

        runBlocking {
            encounterList = encounterDao.getEncounterList()
        }
        var encounterListJson = gson.toJson(encounterList)

        resultIntent.putExtra("resource", encounterListJson)
        resultIntent.putExtra("queryType", "getAll")
        resultIntent.putExtra("resourceType", "encounter")
        return resultIntent
    }

    // LOCATION
    private fun getLocationWithIntent(id: String): Intent {
        lateinit var location: Location
        var resultIntent = Intent()

        runBlocking {
            location = locationDao.getLocation(id)
        }
        var locationJson = gson.toJson(location)

        resultIntent.putExtra("resource", locationJson)
        resultIntent.putExtra("queryType", "getOne")
        resultIntent.putExtra("resourceType", "location")
        return resultIntent
    }

    private fun getLocationListWithIntent(): Intent {
        lateinit var locationList: List<Location>
        var resultIntent = Intent()

        runBlocking {
            locationList = locationDao.getLocationList()
        }
        var locationListJson = gson.toJson(locationList)

        resultIntent.putExtra("resource", locationListJson)
        resultIntent.putExtra("queryType", "getAll")
        resultIntent.putExtra("resourceType", "location")
        return resultIntent
    }

}