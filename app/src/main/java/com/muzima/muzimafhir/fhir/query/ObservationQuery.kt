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
import typeFixFolder.GetObservationListQuery
import typeFixFolder.fragment.ObservationEntry
import java.time.Instant
import java.util.*
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
                                    if (o?.status() != null) {
                                        observation.status = o?.status().toString()
                                    }
                                    if (o?.valueString() != null) {
                                        observation.valueString = o?.valueString()
                                    }
                                    observation.valueInteger = o?.valueInteger()
                                    if (o?.code() != null) {
                                        observation.code = parseCode(o.code())
                                    }
                                    if (o?.valueDateTime() != null) {
                                        observation.valueDateTime = Date.from(Instant.parse(o.valueDateTime() as String))
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

    suspend fun queryObservationList(): MutableList<Observation> {
        var q = GetObservationListQuery
                .builder()
                .build()
        Log.d(TAG, "queryObservationList query built")
        return suspendCoroutine { continuation ->
            client.query(q).enqueue(
                    object : ApolloCall.Callback<GetObservationListQuery.Data>() {
                        override fun onResponse(response: Response<GetObservationListQuery.Data>) {
                            Log.d(TAG, "query response received")
                            var observationList = mutableListOf<Observation>()

                            if(response.data() != null){
                                Log.d(TAG, "raw list response: ${response.data().toString()}")
                                if(response.data()!!.ObservationList() != null){
                                    if(response.data()!!.ObservationList()!!.entry() != null){
                                        val entries = response.data()!!.ObservationList()!!.entry()
                                        entries!!.forEach { entry ->
                                            val o = entry.resource()?.fragments()?.observationEntry()
                                            if(o == null){
                                                Log.d(TAG, "the observation entry was null")
                                            }

                                            val observation = Observation()
                                            if (o?.status() != null) {
                                                observation.status = o.status().toString()
                                            }
                                            if (o?.valueString() != null) {
                                                observation.valueString = o.valueString()
                                            }
                                            observation.valueInteger = o?.valueInteger()
                                            if (o?.code() != null) {
                                                observation.code = parseFragmentCode(o.code())
                                            }
                                            if (o?.valueDateTime() != null) {
                                                observation.valueDateTime = Date.from(Instant.parse(o.valueDateTime() as String))
                                            }
                                            observationList.add(observation)
                                        }
                                    } else {Log.d(TAG, "entry was null")}
                                } else{Log.d(TAG, "ObservationList was null")}
                            } else{ Log.d(TAG, "data was null") }

                            Log.d(TAG, "continuation resuming with ${observationList.size} entries")
                            continuation.resume(observationList)
                        }

                        override fun onFailure(e: ApolloException) {
                            Log.d(TAG, "failed with exception ${e.message}")
                            continuation.resumeWithException(e)
                        }
                    }
            )
        }
    }


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
        if (code?.text() != null) {
            mCodeableConcept.text = code?.text()
        }
        return mCodeableConcept
    }

    fun parseCoding(coding: GetObservationByIdQuery.Coding?): Coding {
        var mCoding = Coding()
        if (coding?.code() != null) {
            mCoding.code = coding.code().toString()
        }
        if (coding?.display() != null) {
            mCoding.display = coding.display()
        }
        if (coding?.system() != null) {
            mCoding.system = coding.system().toString()
        }
        if (coding?.version() != null) {
            mCoding.version = coding.version()
        }
        if (coding?.userSelected() != null) {
            mCoding.userSelected = coding.userSelected()
        }

        return mCoding
    }

    // parser for code from fragment
    fun parseFragmentCode(code: ObservationEntry.Code?): CodeableConcept {
        var mCodeableConcept = CodeableConcept()
        var codingList = mutableListOf<Coding?>()
        if (code?.coding() != null) {
            code.coding()?.forEach {
                codingList.add(parseFragmentCoding(it))
            }
        }
        mCodeableConcept.coding = codingList
        if (code?.text() != null) {
            mCodeableConcept.text = code.text()
        }
        return mCodeableConcept
    }

    fun parseFragmentCoding(coding: ObservationEntry.Coding?): Coding {
        var mCoding = Coding()
        if (coding?.code() != null) {
            mCoding.code = coding.code().toString()
        }
        if (coding?.display() != null) {
            mCoding.display = coding.display()
        }
        if (coding?.system() != null) {
            mCoding.system = coding.system().toString()
        }
        if (coding?.version() != null) {
            mCoding.version = coding.version()
        }
        if (coding?.userSelected() != null) {
            mCoding.userSelected = coding.userSelected()
        }

        return mCoding
    }
}