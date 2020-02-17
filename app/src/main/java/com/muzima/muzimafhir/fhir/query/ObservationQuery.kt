package com.muzima.muzimafhir.fhir.query

import android.util.Log
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.muzima.muzimafhir.data.fhir.Observation
import com.muzima.muzimafhir.data.fhir.types.CodeableConcept
import com.muzima.muzimafhir.data.fhir.types.Coding
import com.muzima.muzimafhir.fhir.client.ApplicationGraphQLClient
import typeFixFolder.GetObservationByIdQuery
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class ObservationQuery {
    var TAG = "ObservationQuery"
    val client = ApplicationGraphQLClient.getClient()

    suspend fun queryObservationById(id: String): Observation {
        var q = GetObservationByIdQuery
                .builder()
                .id(id)
                .build()
        return suspendCoroutine { continuation ->
            client.query(q).enqueue(
                    object : ApolloCall.Callback<GetObservationByIdQuery.Data>() {
                        override fun onResponse(response: Response<GetObservationByIdQuery.Data>) {
                            var observation = Observation()
                            response.data().let { data ->
                                Log.d(TAG, "raw response: " + data.toString())
                                data!!.Observation().let { o ->
                                    Log.d(TAG, "Observation() has data")
                                    observation.status = o?.status().toString()
                                    observation.valueString = o?.valueString()
                                    observation.valueInteger = o?.valueInteger()
                                    if (o?.code() != null) {
                                        observation.code = parseCode(o.code())
                                    }
                                }
                            }
                            Log.d(TAG, observation.toString())
                            continuation.resume(observation)
                        }

                        override fun onFailure(e: ApolloException) {
                            continuation.resumeWithException(e)
                        }
                    }
            )
        }
    }

    // TODO: queryObservationList()


    // parsers
    fun parseCode(code: GetObservationByIdQuery.Code?): CodeableConcept {
        var mCodeableConcept = CodeableConcept()
        var codingList = mutableListOf<Coding?>()
        if (code?.coding() != null) {
            code.coding()?.forEach {
                codingList.add(parseCoding(it))
            }
        }
        mCodeableConcept.coding = codingList
        mCodeableConcept.text = code?.text()
        return mCodeableConcept
    }

    fun parseCoding(coding: GetObservationByIdQuery.Coding?): Coding {
        var mCoding = Coding()
        mCoding.code = coding?.code().toString()
        mCoding.display = coding?.display()
        mCoding.system = coding?.system().toString()
        mCoding.version = coding?.version()
        mCoding.userSelected = coding?.userSelected()
        return mCoding
    }
}