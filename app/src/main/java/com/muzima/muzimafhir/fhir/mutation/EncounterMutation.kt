package com.muzima.muzimafhir.fhir.mutation

import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.muzima.muzimafhir.fhir.client.ApplicationGraphQLClient
import typeFixFolder.DeleteEncounterMutation
import typeFixFolder.EncounterCreateMutation
import typeFixFolder.UpdateEncounterMutation
import typeFixFolder.type.Encounter_Input
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class EncounterMutation {
    var TAG = "EncounterMutation"
    val client = ApplicationGraphQLClient.getClient()

    // TODO: map from encounter model to Encounter_Input before calling this method

    suspend fun createEncounter(encounter: Encounter_Input) : EncounterCreateMutation.EncounterCreate? {

        var m = EncounterCreateMutation
                .builder()
                .encounter(encounter)
                .build()

        return suspendCoroutine { continuation ->
            client.mutate(m).enqueue(
                    object : ApolloCall.Callback<EncounterCreateMutation.Data>() {
                        override fun onResponse(response: Response<EncounterCreateMutation.Data>) {
                            println("Callback onResponse called!")
                            var dataEncounter = response.data()?.EncounterCreate()
                            //var ret = "The callback returned successfully!"
                            println("EncounterCreateMutationResponse: " + dataEncounter.toString())
                            //println("Encounter as string: " + parseEncounter(dataEncounter).toString())
                            // var ret = dataEncounter?.name().toString()
                            //onSuccess(ret, parseEncounter(dataEncounter))
                            continuation.resume(dataEncounter)
                        }

                        override fun onFailure(e: ApolloException) {
                            continuation.resumeWithException(e)
                        }
                    }
            )
        }
    }

    suspend fun deleteEncounter(id: String) : DeleteEncounterMutation.Data? {

        var m = DeleteEncounterMutation
                .builder()
                .id(id)
                .build()

        return suspendCoroutine { continuation ->
            client.mutate(m).enqueue(
                    object : ApolloCall.Callback<DeleteEncounterMutation.Data>() {
                        override fun onResponse(response: Response<DeleteEncounterMutation.Data>) {
                            println("Callback onResponse called!")
                            var dataEncounter = response.data()
                            println("DeleteEncounterMutationResponse: " + dataEncounter.toString())

                            continuation.resume(dataEncounter)
                        }

                        override fun onFailure(e: ApolloException) {
                            continuation.resumeWithException(e)
                        }
                    }
            )
        }
    }

    suspend fun updateEncounter(encounter: Encounter_Input) : UpdateEncounterMutation.EncounterUpdate? {

        var m = UpdateEncounterMutation
                .builder()
                .encounter(encounter)
                .build()

        return suspendCoroutine { continuation ->
            client.mutate(m).enqueue(
                    object : ApolloCall.Callback<UpdateEncounterMutation.Data>() {
                        override fun onResponse(response: Response<UpdateEncounterMutation.Data>) {
                            println("Callback onResponse called!")
                            var dataEncounter = response.data()?.EncounterUpdate()
                            //var ret = "The callback returned successfully!"
                            println("UpdateEncounterMutationResponse: " + dataEncounter.toString())
                            //println("Encounter as string: " + parseEncounter(dataEncounter).toString())
                            // var ret = dataEncounter?.name().toString()
                            //onSuccess(ret, parseEncounter(dataEncounter))
                            continuation.resume(dataEncounter)
                        }

                        override fun onFailure(e: ApolloException) {
                            continuation.resumeWithException(e)
                        }
                    }
            )
        }
    }

}