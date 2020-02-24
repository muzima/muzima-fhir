package com.muzima.muzimafhir.fhir.mutation

import android.util.Log
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.muzima.muzimafhir.data.fhir.Patient
import com.muzima.muzimafhir.data.fhir.types.Address
import com.muzima.muzimafhir.data.fhir.types.HumanName
import com.muzima.muzimafhir.fhir.client.ApplicationGraphQLClient
import typeFixFolder.DeletePatientMutation
import typeFixFolder.GetPatientByIdQuery
import typeFixFolder.PatientCreateMutation
import typeFixFolder.type.Patient_Input
import java.time.Instant
import java.util.*
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class PatientMutation {
    var TAG = "PatientMutation"
    val client = ApplicationGraphQLClient.getClient()

    // TODO: map from patient model to Patient_Input before calling this method
    // TODO: updatePatient()
    suspend fun createPatient(patient: Patient_Input) : PatientCreateMutation.PatientCreate? {

        var m = PatientCreateMutation
                .builder()
                .patient(patient)
                .build()

        return suspendCoroutine { continuation ->
            client.mutate(m).enqueue(
                    object : ApolloCall.Callback<PatientCreateMutation.Data>() {
                        override fun onResponse(response: Response<PatientCreateMutation.Data>) {
                            println("Callback onResponse called!")
                            var dataPatient = response.data()?.PatientCreate()
                            //var ret = "The callback returned successfully!"
                            println("PatientCreateMutationResponse: " + dataPatient.toString())
                            //println("Patient as string: " + parsePatient(dataPatient).toString())
                            // var ret = dataPatient?.name().toString()
                            //onSuccess(ret, parsePatient(dataPatient))
                            continuation.resume(dataPatient)
                        }

                        override fun onFailure(e: ApolloException) {
                            continuation.resumeWithException(e)
                        }
                    }
            )
        }
    }

    suspend fun deletePatient(id: String) : DeletePatientMutation.Data? {

        var m = DeletePatientMutation
                .builder()
                .id(id)
                .build()

        return suspendCoroutine { continuation ->
            client.mutate(m).enqueue(
                    object : ApolloCall.Callback<DeletePatientMutation.Data>() {
                        override fun onResponse(response: Response<DeletePatientMutation.Data>) {
                            println("Callback onResponse called!")
                            var dataPatient = response.data()
                            println("DeletePatientMutationResponse: " + dataPatient.toString())

                            continuation.resume(dataPatient)
                        }

                        override fun onFailure(e: ApolloException) {
                            continuation.resumeWithException(e)
                        }
                    }
            )
        }
    }
}