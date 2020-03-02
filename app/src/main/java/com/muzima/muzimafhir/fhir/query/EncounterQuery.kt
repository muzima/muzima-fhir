package com.muzima.muzimafhir.fhir.query

import android.util.Log
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.muzima.muzimafhir.data.fhir.Encounter
import com.muzima.muzimafhir.data.fhir.types.CodeableConcept
import com.muzima.muzimafhir.fhir.client.ApplicationGraphQLClient
import typeFixFolder.GetEncounterByIdQuery
import typeFixFolder.GetEncounterListQuery
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine


class EncounterQuery {
    var TAG = "EncounterQuery"
    val client = ApplicationGraphQLClient.getClient()


    suspend fun queryEncounterById(id: String): Encounter {
        var q = GetEncounterByIdQuery
                .builder()
                .id(id)
                .build()
        return suspendCoroutine { continuation ->
            client.query(q).enqueue(
                    object : ApolloCall.Callback<GetEncounterByIdQuery.Data>() {
                        override fun onResponse(response: Response<GetEncounterByIdQuery.Data>) {
                            var encounter = Encounter()
                            response.data().let { data ->
                                Log.d(TAG, "raw response: " + data.toString())
                                data!!.Encounter().let { e ->
                                    Log.d(TAG, "Encounter() has data")
                                    encounter.status = e?.status().toString()
                                    if(e?.type() != null){
                                        e.type()?.forEach { t ->
                                            var codeableConcept = CodeableConcept()
                                            codeableConcept.text = t.text()
                                            encounter.type?.add(codeableConcept)
                                        }
                                    }
                                    if(e?.serviceType()!=null){
                                        var mCodeableConcept = CodeableConcept()
                                        if(e?.serviceType()?.text() != null){
                                            mCodeableConcept.text = e.serviceType()?.text()
                                        }
                                        encounter.serviceType = mCodeableConcept
                                    }
                                    if(e?.priority()!=null){
                                        var mCodeableConcept = CodeableConcept()
                                        if(e?.priority()?.text() != null){
                                            mCodeableConcept.text = e.priority()?.text()
                                        }
                                        encounter.priority = mCodeableConcept
                                    }


                                } ?: Log.d(TAG, "Encounter was null")
                            } ?: Log.d(TAG, "data was null")
                            Log.d(TAG, encounter.toString())
                            continuation.resume(encounter)
                        }

                        override fun onFailure(e: ApolloException) {
                            continuation.resumeWithException(e)
                        }
                    }
            )
        }
    }


    suspend fun queryEncounterList(): MutableList<Encounter> {
        var q = GetEncounterListQuery
                .builder()
                .build()
        Log.d(TAG, "queryEncounterList query built")
        return suspendCoroutine { continuation ->
            client.query(q).enqueue(
                    object : ApolloCall.Callback<GetEncounterListQuery.Data>() {
                        override fun onResponse(response: Response<GetEncounterListQuery.Data>) {
                            Log.d(TAG, "query response received")
                            var encounterList = mutableListOf<Encounter>()

                            if(response.data() != null){
                                Log.d(TAG, "raw list response: ${response.data().toString()}")
                                if(response.data()!!.EncounterList() != null){
                                    if(response.data()!!.EncounterList()!!.entry() != null){
                                        val entries = response.data()!!.EncounterList()!!.entry()
                                        entries!!.forEach { entry ->
                                            val e = entry.resource()?.fragments()?.encounterEntry()
                                            if(e == null){
                                                Log.d(TAG, "the encounter entry was null")
                                            }

                                            val encounter = Encounter()
                                            if(e?.status() != null){
                                                encounter.status = e.status().toString()
                                                Log.d(TAG, "field \"name\" for a encounter entry was set to ${e.status()}")
                                            } else{
                                                Log.d(TAG, "field \"name\" was null")
                                            }

                                            if(e?.type() != null){
                                                e.type()?.forEach { t ->
                                                    var codeableConcept = CodeableConcept()
                                                    codeableConcept.text = t.text()
                                                    encounter.type?.add(codeableConcept)
                                                }
                                                Log.d(TAG, "field \"name\" for a encounter entry was set to ${e.type()}")

                                            }else{
                                                Log.d(TAG, "field \"name\" was null")
                                            }

                                            if(e?.serviceType()!=null){
                                                var mCodeableConcept = CodeableConcept()
                                                if(e?.serviceType()?.text() != null){
                                                    mCodeableConcept.text = e.serviceType()?.text()
                                                }
                                                encounter.serviceType = mCodeableConcept
                                                Log.d(TAG, "field \"name\" for a encounter entry was set to ${e.serviceType()}")
                                            }else {
                                                Log.d(TAG, "field \"name\" was null")
                                            }

                                            if(e?.priority()!=null){
                                                var mCodeableConcept = CodeableConcept()
                                                if(e?.priority()?.text() != null){
                                                    mCodeableConcept.text = e.priority()?.text()
                                                }
                                                encounter.priority = mCodeableConcept
                                                Log.d(TAG, "field \"name\" for a encounter entry was set to ${e.priority()}")

                                            }else{
                                                Log.d(TAG, "field \"name\" was null")
                                            }




                                            encounterList.add(encounter)
                                        }
                                    } else {Log.d(TAG, "entry was null")}
                                } else{Log.d(TAG, "EncounterList was null")}
                            } else{ Log.d(TAG, "data was null") }

                            Log.d(TAG, "continuation resuming with ${encounterList.size} entries")
                            continuation.resume(encounterList)
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