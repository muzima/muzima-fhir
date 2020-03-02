package com.muzima.muzimafhir.fhir.query

import android.util.Log
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.muzima.muzimafhir.data.fhir.Practitioner
import com.muzima.muzimafhir.data.fhir.types.Address
import com.muzima.muzimafhir.data.fhir.types.ContactPoint
import com.muzima.muzimafhir.data.fhir.types.HumanName
import com.muzima.muzimafhir.data.fhir.types.Identifier
import com.muzima.muzimafhir.fhir.client.ApplicationGraphQLClient
import typeFixFolder.GetPractitionerByIdQuery
import typeFixFolder.GetPractitionerListQuery
import java.time.Instant
import java.util.*
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class PractitionerQuery {

    var TAG = "PractitionerQuery"
    val client = ApplicationGraphQLClient.getClient()

    suspend fun queryPractitionerById(id: String): Practitioner {
        var q = GetPractitionerByIdQuery
                .builder()
                .id(id)
                .build()
        return suspendCoroutine { continuation ->
            client.query(q).enqueue(
                    object : ApolloCall.Callback<GetPractitionerByIdQuery.Data>() {
                        override fun onResponse(response: Response<GetPractitionerByIdQuery.Data>) {
                            var practitioner = Practitioner()
                            response.data().let { data ->
                                Log.d(TAG, "raw response: " + data.toString())
                                data!!.Practitioner().let { p ->
                                    Log.d(TAG, "Practitioner() has data")
                                    if (p?.id() != null) {
                                        practitioner.id = p.id().toString()
                                    }
                                    if (p?.identifier() != null) {
                                        p.identifier()?.forEach { i ->
                                            var identifier = Identifier()
                                            identifier.value = i.value().toString()
                                            practitioner.identifier?.add(identifier)
                                        }
                                    }
                                    if (p?.telecom() != null) {
                                        p.telecom()?.forEach { t ->
                                            var telecom = ContactPoint()
                                            telecom.value = t.value()
                                            practitioner.telecom?.add(telecom)
                                        }
                                    }
                                    if (p?.active() != null) {
                                        practitioner.active = p.active()
                                    }
                                    if (p?.gender() != null) {
                                        practitioner.gender = p.gender().toString()
                                    }
                                    if (p?.birthDate() != null) {
                                        practitioner.birthDate = Date.from(Instant.parse(p.birthDate() as String))
                                    }
                                    if (p?.name() != null) {
                                        p.name()?.forEach { name ->
                                            var humanName = HumanName()
                                            if (name?.text() != null) {
                                                humanName.text = name.text().toString()
                                            }
                                            humanName.family = name.family()
                                            practitioner.name?.add(humanName)
                                        }
                                    }
                                    if (p?.address() != null) {
                                        p.address()?.forEach { a ->
                                            var address = Address()
                                            address.line = a.line()
                                            practitioner.address?.add(address)
                                        }
                                    }

                                } ?: Log.d(TAG, "Practitioner was null")
                            } ?: Log.d(TAG, "data was null")
                            Log.d(TAG, practitioner.toString())
                            continuation.resume(practitioner)
                        }

                        override fun onFailure(e: ApolloException) {
                            continuation.resumeWithException(e)
                        }
                    }
            )
        }
    }

    suspend fun queryPractitionerList(): MutableList<Practitioner> {
        var q = GetPractitionerListQuery
                .builder()
                .build()
        Log.d(TAG, "queryPractitionerList query built")
        return suspendCoroutine { continuation ->
            client.query(q).enqueue(
                    object : ApolloCall.Callback<GetPractitionerListQuery.Data>() {
                        override fun onResponse(response: Response<GetPractitionerListQuery.Data>) {
                            Log.d(TAG, "query response received")
                            var practitionerList = mutableListOf<Practitioner>()

                            if(response.data() != null){
                                Log.d(TAG, "raw list response: ${response.data().toString()}")
                                if(response.data()!!.PractitionerList() != null){
                                    if(response.data()!!.PractitionerList()!!.entry() != null){
                                        val entries = response.data()!!.PractitionerList()!!.entry()
                                        entries!!.forEach { entry ->
                                            val p = entry.resource()?.fragments()?.practitionerEntry()
                                            if(p == null){
                                                Log.d(TAG, "the practitioner entry was null")
                                            }

                                            val practitioner = Practitioner()

                                            if (p?.id() != null) {
                                                practitioner.id = p.id().toString()
                                            }
                                            if (p?.identifier() != null) {
                                                p.identifier()?.forEach { i ->
                                                    var identifier = Identifier()
                                                    identifier.value = i.value().toString()
                                                    practitioner.identifier?.add(identifier)
                                                }
                                            }
                                            if(p?.active() != null){
                                                practitioner.active = p.active()
                                                Log.d(TAG, "field \"active\" for a practitioner entry was set to ${p.active()}")
                                            } else{
                                                Log.d(TAG, "field \"active\" was null")
                                            }
                                            if(p?.gender() != null){
                                                practitioner.gender = p.gender().toString()
                                                Log.d(TAG, "field \"gender\" for a practitioner entry was set to ${p.gender()}")
                                            } else {
                                                Log.d(TAG, "field \"gender\" was null")
                                            }
                                            if(p?.birthDate() != null){
                                                practitioner.birthDate = Date.from(Instant.parse(p.birthDate() as String))
                                                Log.d(TAG, "field \"birthDate\" for a practitioner entry was set to ${p.birthDate()}")
                                            } else {
                                                Log.d(TAG, "field \"birthDate\" was null")
                                            }
                                            if (p?.telecom() != null) {
                                                p.telecom()?.forEach { t ->
                                                    var telecom = ContactPoint()
                                                    telecom.value = t.value()
                                                    practitioner.telecom?.add(telecom)
                                                }
                                            }
                                            if (p?.name() != null) {
                                                p.name()?.forEach { name ->
                                                    var humanName = HumanName()
                                                    if (name?.text() != null) {
                                                        humanName.text = name.text().toString()
                                                    }
                                                    humanName.family = name.family()
                                                    practitioner.name?.add(humanName)
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
                                                    practitioner.address?.add(address)
                                                    Log.d(TAG, "field \"address.line\" for a practitioner entry was set to ${address.line}")
                                                    //Log.d(TAG, "field \"address\" for a practitioner entry was set to $a")
                                                }
                                            } else {
                                                Log.d(TAG, "field \"address\" was null")
                                            }
                                            practitionerList.add(practitioner)
                                        }
                                    } else {Log.d(TAG, "entry was null")}
                                } else{Log.d(TAG, "PractitionerList was null")}
                            } else{ Log.d(TAG, "data was null") }

                            Log.d(TAG, "continuation resuming with ${practitionerList.size} entries")
                            continuation.resume(practitionerList)
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