package com.muzima.muzimafhir.fhir.query

import android.util.Log
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.muzima.muzimafhir.data.fhir.Location
import com.muzima.muzimafhir.data.fhir.types.Address
import com.muzima.muzimafhir.data.fhir.types.ContactPoint
import com.muzima.muzimafhir.data.fhir.types.HumanName
import com.muzima.muzimafhir.fhir.client.ApplicationGraphQLClient
import typeFixFolder.GetLocationByIdQuery
import typeFixFolder.GetLocationListQuery
import java.time.Instant
import java.util.*
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine


class LocationQuery {
    var TAG = "LocationQuery"
    val client = ApplicationGraphQLClient.getClient()


    suspend fun queryLocationById(id: String): Location {
        var q = GetLocationByIdQuery
                .builder()
                .id(id)
                .build()
        return suspendCoroutine { continuation ->
            client.query(q).enqueue(
                    object : ApolloCall.Callback<GetLocationByIdQuery.Data>() {
                        override fun onResponse(response: Response<GetLocationByIdQuery.Data>) {
                            var location = Location()
                            response.data().let { data ->
                                Log.d(TAG, "raw response: " + data.toString())
                                data!!.Location().let { l ->
                                    Log.d(TAG, "Location() has data")
                                    location.name = l?.name()
                                    location.status = l?.status() as String
                                    location.description = l?.description()
                                    if(l.address()?.line() != null) {
                                        location.address?.line?.forEach { line ->
                                            location.address?.line = l.address()?.line()
                                        }
                                    }
                                    if(l.telecom() != null){
                                        l.telecom()?.forEach { t ->
                                            var contactPoint = ContactPoint()
                                            contactPoint.value = t.value()
                                            location.telecom?.add(contactPoint)
                                        }
                                    }
                                } ?: Log.d(TAG, "Location was null")
                            } ?: Log.d(TAG, "data was null")
                            Log.d(TAG, location.toString())
                            continuation.resume(location)
                        }

                        override fun onFailure(e: ApolloException) {
                            continuation.resumeWithException(e)
                        }
                    }
            )
        }
    }


    suspend fun queryLocationList(): MutableList<Location> {
        var q = GetLocationListQuery
                .builder()
                .build()
        Log.d(TAG, "queryLocationList query built")
        return suspendCoroutine { continuation ->
            client.query(q).enqueue(
                    object : ApolloCall.Callback<GetLocationListQuery.Data>() {
                        override fun onResponse(response: Response<GetLocationListQuery.Data>) {
                            Log.d(TAG, "query response received")
                            var locationList = mutableListOf<Location>()

                            if(response.data() != null){
                                Log.d(TAG, "raw list response: ${response.data().toString()}")
                                if(response.data()!!.LocationList() != null){
                                    if(response.data()!!.LocationList()!!.entry() != null){
                                        val entries = response.data()!!.LocationList()!!.entry()
                                        entries!!.forEach { entry ->
                                            val l = entry.resource()?.fragments()?.locationEntry()
                                            if(l == null){
                                                Log.d(TAG, "the location entry was null")
                                            }

                                            val location = Location()
                                            if(l?.name() != null){
                                                location.name = l.name()
                                                Log.d(TAG, "field \"name\" for a location entry was set to ${l.name()}")
                                            } else{
                                                Log.d(TAG, "field \"name\" was null")
                                            }
                                            if(l?.status() != null){
                                                location.status = l.status() as String
                                                Log.d(TAG, "field \"status\" for a location entry was set to ${l.status()}")
                                            } else{
                                                Log.d(TAG, "field \"status\" was null")
                                            }
                                            if(l?.description() != null){
                                                location.description = l.description()
                                                Log.d(TAG, "field \"description\" for a location entry was set to ${l.description()}")
                                            } else{
                                                Log.d(TAG, "field \"description\" was null")
                                            }
                                            if(l?.address()?.line() != null){
                                                location.address?.line?.forEach { line ->
                                                    location.address?.line = l?.address()?.line()
                                                }
                                                Log.d(TAG, "field \"line\" for a location entry was set to ${l?.address()?.line()}")
                                            } else{
                                                Log.d(TAG, "field \"line\" was null")
                                            }
                                            if(l?.telecom() != null){
                                                l.telecom()?.forEach { t ->
                                                    var contactPoint = ContactPoint()
                                                    contactPoint.value = t.value()
                                                    location.telecom?.add(contactPoint)
                                                }
                                            }
                                            locationList.add(location)
                                        }
                                    } else {Log.d(TAG, "entry was null")}
                                } else{Log.d(TAG, "LocationList was null")}
                            } else{ Log.d(TAG, "data was null") }

                            Log.d(TAG, "continuation resuming with ${locationList.size} entries")
                            continuation.resume(locationList)
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