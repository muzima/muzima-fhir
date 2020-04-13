package com.muzima.muzimafhir.fhir.query

import android.util.Log
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.muzima.muzimafhir.data.fhir.Patient
import com.muzima.muzimafhir.data.fhir.types.Address
import com.muzima.muzimafhir.data.fhir.types.HumanName
import com.muzima.muzimafhir.data.fhir.types.Identifier
import com.muzima.muzimafhir.fhir.client.ApplicationGraphQLClient
import graphqlcontent.GetPatientByIdQuery
import graphqlcontent.GetPatientListQuery
import java.time.Instant
import java.util.*
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class PatientQuery {

    var TAG = "PatientQuery"
    val client = ApplicationGraphQLClient.getClient()

    suspend fun queryPatientById(id: String): Patient {
        var q = GetPatientByIdQuery
                .builder()
                .id(id)
                .build()
        return suspendCoroutine { continuation ->
            client.query(q).enqueue(
                    object : ApolloCall.Callback<GetPatientByIdQuery.Data>() {
                        override fun onResponse(response: Response<GetPatientByIdQuery.Data>) {
                            var patient = Patient()
                            response.data().let { data ->
                                Log.d(TAG, "raw response: " + data.toString())
                                data!!.Patient().let { p ->
                                    Log.d(TAG, "Patient() has data")
                                    if (p?.id() != null) {
                                        patient.id = p.id().toString()
                                    }
                                    if (p?.identifier() != null) {
                                        p.identifier()?.forEach { i ->
                                            var identifier = Identifier()
                                            identifier.value = i.value().toString()
                                            patient.identifier?.add(identifier)
                                        }
                                    }
                                    patient.active = p?.active()
                                    patient.gender = p?.gender().toString()
                                    if (p?.birthDate() != null) {
                                        patient.birthDate = Date.from(Instant.parse(p?.birthDate() as String))
                                    }
                                    if (p?.name() != null) {
                                        p?.name()?.forEach { name ->
                                            var humanName = HumanName()
                                            if (name?.text() != null) {
                                                humanName.text = name.text().toString()
                                            }
                                            if (name?.given() != null) {
                                                name.given()?.forEach { givenName ->
                                                    humanName.given.add(givenName)
                                                }
                                            }
                                            humanName.family = name.family()
                                            patient.name?.add(humanName)
                                        }
                                    }
                                    if (p?.address() != null) {
                                        p?.address()?.forEach { a ->
                                            var address = Address()
                                            address.line = a.line()
                                            patient.address?.add(address)
                                        }
                                    }
                                    if (p?.deceasedBoolean() != null) {
                                        patient.deceasedBoolean = p.deceasedBoolean()
                                    }
                                    if (p?.deceasedDateTime() != null) {
                                        patient.deceasedDateTime = Date.from(Instant.parse(p.deceasedDateTime().toString()))
                                    }

                                } ?: Log.d(TAG, "Patient was null")
                            } ?: Log.d(TAG, "data was null")
                            Log.d(TAG, patient.toString())
                            continuation.resume(patient)
                        }

                        override fun onFailure(e: ApolloException) {
                            continuation.resumeWithException(e)
                        }
                    }
            )
        }
    }

    suspend fun queryPatientList(): MutableList<Patient> {
        var q = GetPatientListQuery
                .builder()
                .build()
        Log.d(TAG, "queryPatientList query built")
        return suspendCoroutine { continuation ->
            client.query(q).enqueue(
                    object : ApolloCall.Callback<GetPatientListQuery.Data>() {
                        override fun onResponse(response: Response<GetPatientListQuery.Data>) {
                            Log.d(TAG, "query response received")
                            var patientList = mutableListOf<Patient>()

                            if(response.data() != null){
                                Log.d(TAG, "raw list response: ${response.data().toString()}")
                                if(response.data()!!.PatientList() != null){
                                    if(response.data()!!.PatientList()!!.entry() != null){
                                        val entries = response.data()!!.PatientList()!!.entry()
                                        entries!!.forEach { entry ->
                                            val p = entry.resource()?.fragments()?.patientEntry()
                                            if(p == null){
                                                Log.d(TAG, "the patient entry was null")
                                            }

                                            val patient = Patient()
                                            if (p?.id() != null) {
                                                patient.id = p.id().toString()
                                            }
                                            if (p?.identifier() != null) {
                                                p.identifier()?.forEach { i ->
                                                    var identifier = Identifier()
                                                    identifier.value = i.value().toString()
                                                    patient.identifier?.add(identifier)
                                                }
                                            }
                                            if(p?.active() != null){
                                                patient.active = p.active()
                                                Log.d(TAG, "field \"active\" for a patient entry was set to ${p.active()}")
                                            } else{
                                                Log.d(TAG, "field \"active\" was null")
                                            }
                                            if(p?.gender() != null){
                                                patient.gender = p?.gender() as String
                                                Log.d(TAG, "field \"gender\" for a patient entry was set to ${p.gender()}")
                                            } else {
                                                Log.d(TAG, "field \"gender\" was null")
                                            }
                                            if(p?.birthDate() != null){
                                                patient.birthDate = Date.from(Instant.parse(p.birthDate() as String))
                                                Log.d(TAG, "field \"birthDate\" for a patient entry was set to ${p.birthDate()}")
                                            } else {
                                                Log.d(TAG, "field \"birthDate\" was null")
                                            }
                                            if (p?.name() != null) {
                                                p.name()?.forEach { name ->
                                                    var humanName = HumanName()
                                                    humanName.family = name.family().toString()
                                                    if (name?.text() != null) {
                                                        humanName.text = name.text().toString()
                                                    }
                                                    if (name?.given() != null) {
                                                        name.given()?.forEach { givenName ->
                                                            humanName.given.add(givenName)
                                                        }
                                                    }
                                                    Log.d(TAG, "field \"humanName.family\" for a patient entry was set to ${humanName.family}")
                                                    patient.name?.add(humanName)
                                                    //Log.d(TAG, "field \"humanName\" for a patient entry was set to $name")
                                                }
                                            } else {
                                                Log.d(TAG, "field \"name\" was null")
                                            }
                                            if (p?.address() != null) {
                                                p.address()?.forEach { a ->
                                                    var address = Address()
                                                    a.line()?.forEach {line ->
                                                        address.line?.add(line)
                                                    }
                                                    patient.address?.add(address)
                                                    Log.d(TAG, "field \"address.line\" for a patient entry was set to ${address.line}")
                                                    //Log.d(TAG, "field \"address\" for a patient entry was set to $a")
                                                }
                                            } else {
                                                Log.d(TAG, "field \"address\" was null")
                                            }

                                            if (p?.deceasedBoolean() != null) {
                                                patient.deceasedBoolean = p.deceasedBoolean()
                                            }
                                            if (p?.deceasedDateTime() != null) {
                                                patient.deceasedDateTime = Date.from(Instant.parse(p.deceasedDateTime().toString()))
                                            }
                                            patientList.add(patient)
                                        }
                                    } else {Log.d(TAG, "entry was null")}
                                } else{Log.d(TAG, "PatientList was null")}
                            } else{ Log.d(TAG, "data was null") }

                            Log.d(TAG, "continuation resuming with ${patientList.size} entries")
                            continuation.resume(patientList)
                        }

                        override fun onFailure(e: ApolloException) {
                            Log.d(TAG, "failed with exception ${e.message}")
                            continuation.resumeWithException(e)
                        }
                    }
            )
        }
    }

}