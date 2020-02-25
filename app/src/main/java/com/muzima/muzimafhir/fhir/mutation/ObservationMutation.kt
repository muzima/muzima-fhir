package com.muzima.muzimafhir.fhir.mutation

import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.muzima.muzimafhir.fhir.client.ApplicationGraphQLClient
import typeFixFolder.DeleteObservationMutation
import typeFixFolder.ObservationCreateMutation
import typeFixFolder.type.Observation_Input
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class ObservationMutation {
    var TAG = "ObservationMutation"
    val client = ApplicationGraphQLClient.getClient()

    // TODO: map from observation model to Observation_Input before calling this method
    // TODO: updateObservation()
    suspend fun createObservation(observation: Observation_Input) : ObservationCreateMutation.ObservationCreate? {

        var m = ObservationCreateMutation
                .builder()
                .observation(observation)
                .build()

        return suspendCoroutine { continuation ->
            client.mutate(m).enqueue(
                    object : ApolloCall.Callback<ObservationCreateMutation.Data>() {
                        override fun onResponse(response: Response<ObservationCreateMutation.Data>) {
                            println("Callback onResponse called!")
                            var dataObservation = response.data()?.ObservationCreate()
                            //var ret = "The callback returned successfully!"
                            println("ObservationCreateMutationResponse: " + dataObservation.toString())
                            //println("Observation as string: " + parseObservation(dataObservation).toString())
                            // var ret = dataObservation?.name().toString()
                            //onSuccess(ret, parseObservation(dataObservation))
                            continuation.resume(dataObservation)
                        }

                        override fun onFailure(e: ApolloException) {
                            continuation.resumeWithException(e)
                        }
                    }
            )
        }
    }

    suspend fun deleteObservation(id: String) : DeleteObservationMutation.Data? {

        var m = DeleteObservationMutation
                .builder()
                .id(id)
                .build()

        return suspendCoroutine { continuation ->
            client.mutate(m).enqueue(
                    object : ApolloCall.Callback<DeleteObservationMutation.Data>() {
                        override fun onResponse(response: Response<DeleteObservationMutation.Data>) {
                            println("Callback onResponse called!")
                            var dataObservation = response.data()
                            println("DeleteObservationMutationResponse: " + dataObservation.toString())

                            continuation.resume(dataObservation)
                        }

                        override fun onFailure(e: ApolloException) {
                            continuation.resumeWithException(e)
                        }
                    }
            )
        }
    }
}