package com.muzima.muzimafhir.fhir.query

import android.util.Log
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.muzima.muzimafhir.data.fhir.Person
import com.muzima.muzimafhir.data.fhir.types.Address
import com.muzima.muzimafhir.data.fhir.types.HumanName
import com.muzima.muzimafhir.fhir.client.ApplicationGraphQLClient
import typeFixFolder.GetPersonByIdQuery
import typeFixFolder.GetPersonListQuery
import java.time.Instant
import java.util.*
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine


class PersonQuery {
    var TAG = "PersonQuery"
    val client = ApplicationGraphQLClient.getClient()

    suspend fun queryPersonById(id: String): Person {
        var q = GetPersonByIdQuery
                .builder()
                .id(id)
                .build()
        return suspendCoroutine { continuation ->
            client.query(q).enqueue(
                    object : ApolloCall.Callback<GetPersonByIdQuery.Data>() {
                        override fun onResponse(response: Response<GetPersonByIdQuery.Data>) {
                            var person = Person()
                            response.data().let { data ->
                                Log.d(TAG, "raw response: " + data.toString())
                                data!!.Person().let { p ->
                                        Log.d(TAG, "Person() has data")
                                        person.active = p?.active()
                                        person.gender = p?.gender() as String
                                        person.birthDate = Date.from(Instant.parse(p?.birthDate() as String))
                                        if (p.name() != null) {
                                            p.name()?.forEach { name ->
                                                var humanName = HumanName()
                                                humanName.use = name.use() as String
                                                humanName.family = name.family()
                                                humanName.text = name.text()
                                                person.name?.add(humanName)
                                            }
                                        }
                                        if (p.address() != null) {
                                            p.address()?.forEach { a ->
                                                var address = Address()
                                                address.line = a.line()
                                                person.address?.add(address)
                                            }
                                        }

                                } ?: Log.d(TAG, "Person was null")
                            } ?: Log.d(TAG, "data was null")
                            Log.d(TAG, person.toString())
                            continuation.resume(person)
                        }

                        override fun onFailure(e: ApolloException) {
                            continuation.resumeWithException(e)
                        }
                    }
            )
        }
    }

    suspend fun queryPersonList(): MutableList<Person> {
        var q = GetPersonListQuery
                .builder()
                .build()
        Log.d(TAG, "queryPersonList query built")
        return suspendCoroutine { continuation ->
            client.query(q).enqueue(
                    object : ApolloCall.Callback<GetPersonListQuery.Data>() {
                        override fun onResponse(response: Response<GetPersonListQuery.Data>) {
                            Log.d(TAG, "query response received")
                            var personList = mutableListOf<Person>()

                            if(response.data() != null){
                                Log.d(TAG, "raw list response: ${response.data().toString()}")
                                if(response.data()!!.PersonList() != null){
                                    if(response.data()!!.PersonList()!!.entry() != null){
                                        val entries = response.data()!!.PersonList()!!.entry()
                                        entries!!.forEach { entry ->
                                            val p = entry.resource()?.fragments()?.personEntry()
                                            if(p == null){
                                                Log.d(TAG, "the person entry was null")
                                            }

                                            val person = Person()
                                            if(p?.active() != null){
                                                person.active = p.active()
                                                Log.d(TAG, "field \"active\" for a person entry was set to ${p.active()}")
                                            } else{
                                                Log.d(TAG, "field \"active\" was null")
                                            }
                                            if(p?.gender() != null){
                                                person.gender = p?.gender() as String
                                                Log.d(TAG, "field \"gender\" for a person entry was set to ${p.gender()}")
                                            } else {
                                                Log.d(TAG, "field \"gender\" was null")
                                            }
                                            if(p?.birthDate() != null){
                                                person.birthDate = Date.from(Instant.parse(p.birthDate() as String))
                                                Log.d(TAG, "field \"birthDate\" for a person entry was set to ${p.birthDate()}")
                                            } else {
                                                Log.d(TAG, "field \"birthDate\" was null")
                                            }
                                            if (p?.name() != null) {
                                                p.name()?.forEach { name ->
                                                    var humanName = HumanName()
                                                    humanName.use = name.use().toString()
                                                    Log.d(TAG, "field \"humanName.use\" for a person entry was set to ${humanName.use}")
                                                    humanName.family = name.family().toString()
                                                    Log.d(TAG, "field \"humanName.family\" for a person entry was set to ${humanName.family}")
                                                    humanName.text = name.text()
                                                    Log.d(TAG, "field \"humanName.text\" for a person entry was set to ${humanName.text}")
                                                    person.name?.add(humanName)
                                                    //Log.d(TAG, "field \"humanName\" for a person entry was set to $name")
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
                                                    person.address?.add(address)
                                                    Log.d(TAG, "field \"address.line\" for a person entry was set to ${address.line}")
                                                    //Log.d(TAG, "field \"address\" for a person entry was set to $a")
                                                }
                                            } else {
                                                Log.d(TAG, "field \"address\" was null")
                                            }
                                            personList.add(person)
                                        }
                                    } else {Log.d(TAG, "entry was null")}
                                } else{Log.d(TAG, "PersonList was null")}
                            } else{ Log.d(TAG, "data was null") }

                            Log.d(TAG, "continuation resuming with ${personList.size} entries")
                            continuation.resume(personList)
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