package com.muzima.muzimafhir.fhir.query

import android.util.Log
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.muzima.muzimafhir.data.fhir.Patient
import com.muzima.muzimafhir.data.fhir.types.Address
import com.muzima.muzimafhir.data.fhir.types.HumanName
import com.muzima.muzimafhir.fhir.client.ApplicationGraphQLClient
import typeFixFolder.GetPatientByIdQuery
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
                                    patient.active = p?.active()
                                    patient.gender = p?.gender() as String
                                    patient.birthDate = Date.from(Instant.parse(p?.birthDate() as String))
                                    if (p.name() != null) {
                                        p.name()?.forEach { name ->
                                            var humanName = HumanName()
                                            humanName.family = name.family()
                                            patient.name?.add(humanName)
                                        }
                                    }
                                    if (p.address() != null) {
                                        p.address()?.forEach { a ->
                                            var address = Address()
                                            address.line = a.line()
                                            patient.address?.add(address)
                                        }
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

    fun queryPatientList(){

    }

}