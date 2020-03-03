package com.muzima.muzimafhir.fhir.mutation

import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.muzima.muzimafhir.fhir.client.ApplicationGraphQLClient
import typeFixFolder.DeletePractitionerMutation
import typeFixFolder.PractitionerCreateMutation
import typeFixFolder.UpdatePractitionerMutation
import typeFixFolder.type.Practitioner_Input
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class PractitionerMutation {
    var TAG = "PractitionerMutation"
    val client = ApplicationGraphQLClient.getClient()

    // TODO: map from practitioner model to Practitioner_Input before calling this method

    suspend fun createPractitioner(practitioner: Practitioner_Input) : PractitionerCreateMutation.PractitionerCreate? {

        var m = PractitionerCreateMutation
                .builder()
                .practitioner(practitioner)
                .build()

        return suspendCoroutine { continuation ->
            client.mutate(m).enqueue(
                    object : ApolloCall.Callback<PractitionerCreateMutation.Data>() {
                        override fun onResponse(response: Response<PractitionerCreateMutation.Data>) {
                            println("Callback onResponse called!")
                            var dataPractitioner = response.data()?.PractitionerCreate()
                            //var ret = "The callback returned successfully!"
                            println("PractitionerCreateMutationResponse: " + dataPractitioner.toString())
                            //println("Practitioner as string: " + parsePractitioner(dataPractitioner).toString())
                            // var ret = dataPractitioner?.name().toString()
                            //onSuccess(ret, parsePractitioner(dataPractitioner))
                            continuation.resume(dataPractitioner)
                        }

                        override fun onFailure(e: ApolloException) {
                            continuation.resumeWithException(e)
                        }
                    }
            )
        }
    }

    suspend fun deletePractitioner(id: String) : DeletePractitionerMutation.Data? {

        var m = DeletePractitionerMutation
                .builder()
                .id(id)
                .build()

        return suspendCoroutine { continuation ->
            client.mutate(m).enqueue(
                    object : ApolloCall.Callback<DeletePractitionerMutation.Data>() {
                        override fun onResponse(response: Response<DeletePractitionerMutation.Data>) {
                            println("Callback onResponse called!")
                            var dataPractitioner = response.data()
                            println("DeletePractitionerMutationResponse: " + dataPractitioner.toString())

                            continuation.resume(dataPractitioner)
                        }

                        override fun onFailure(e: ApolloException) {
                            continuation.resumeWithException(e)
                        }
                    }
            )
        }
    }

    suspend fun updatePractitioner(practitioner: Practitioner_Input) : UpdatePractitionerMutation.PractitionerUpdate? {

        var m = UpdatePractitionerMutation
                .builder()
                .practitioner(practitioner)
                .build()

        return suspendCoroutine { continuation ->
            client.mutate(m).enqueue(
                    object : ApolloCall.Callback<UpdatePractitionerMutation.Data>() {
                        override fun onResponse(response: Response<UpdatePractitionerMutation.Data>) {
                            println("Callback onResponse called!")
                            var dataPractitioner = response.data()?.PractitionerUpdate()
                            //var ret = "The callback returned successfully!"
                            println("UpdatePractitionerMutationResponse: " + dataPractitioner.toString())
                            //println("Practitioner as string: " + parsePractitioner(dataPractitioner).toString())
                            // var ret = dataPractitioner?.name().toString()
                            //onSuccess(ret, parsePractitioner(dataPractitioner))
                            continuation.resume(dataPractitioner)
                        }

                        override fun onFailure(e: ApolloException) {
                            continuation.resumeWithException(e)
                        }
                    }
            )
        }
    }
}