package com.muzima.muzimafhir.fhir.mutation

import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.muzima.muzimafhir.fhir.client.ApplicationGraphQLClient
import graphqlcontent.DeletePersonMutation
import graphqlcontent.PersonCreateMutation
import graphqlcontent.UpdatePersonMutation
import graphqlcontent.type.Person_Input
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class PersonMutation {
    var TAG = "PersonMutation"
    val client = ApplicationGraphQLClient.getClient()

    // TODO: map from person model to Person_Input before calling this method

    suspend fun createPerson(person: Person_Input) : PersonCreateMutation.PersonCreate? {

        var m = PersonCreateMutation
                .builder()
                .person(person)
                .build()

        return suspendCoroutine { continuation ->
            client.mutate(m).enqueue(
                    object : ApolloCall.Callback<PersonCreateMutation.Data>() {
                        override fun onResponse(response: Response<PersonCreateMutation.Data>) {
                            println("Callback onResponse called!")
                            var dataPerson = response.data()?.PersonCreate()
                            //var ret = "The callback returned successfully!"
                            println("PersonCreateMutationResponse: " + dataPerson.toString())
                            //println("Person as string: " + parsePerson(dataPerson).toString())
                            // var ret = dataPerson?.name().toString()
                            //onSuccess(ret, parsePerson(dataPerson))
                            continuation.resume(dataPerson)
                        }

                        override fun onFailure(e: ApolloException) {
                            continuation.resumeWithException(e)
                        }
                    }
            )
        }
    }

    suspend fun deletePerson(id: String) : DeletePersonMutation.Data? {

        var m = DeletePersonMutation
                .builder()
                .id(id)
                .build()

        return suspendCoroutine { continuation ->
            client.mutate(m).enqueue(
                    object : ApolloCall.Callback<DeletePersonMutation.Data>() {
                        override fun onResponse(response: Response<DeletePersonMutation.Data>) {
                            println("Callback onResponse called!")
                            var dataPerson = response.data()
                            println("DeletePersonMutationResponse: " + dataPerson.toString())

                            continuation.resume(dataPerson)
                        }

                        override fun onFailure(e: ApolloException) {
                            continuation.resumeWithException(e)
                        }
                    }
            )
        }
    }

    suspend fun updatePerson(person: Person_Input) : UpdatePersonMutation.PersonUpdate? {

        var m = UpdatePersonMutation
                .builder()
                .person(person)
                .build()

        return suspendCoroutine { continuation ->
            client.mutate(m).enqueue(
                    object : ApolloCall.Callback<UpdatePersonMutation.Data>() {
                        override fun onResponse(response: Response<UpdatePersonMutation.Data>) {
                            println("Callback onResponse called!")
                            var dataPerson = response.data()?.PersonUpdate()
                            //var ret = "The callback returned successfully!"
                            println("UpdatePersonMutationResponse: " + dataPerson.toString())
                            //println("Person as string: " + parsePerson(dataPerson).toString())
                            // var ret = dataPerson?.name().toString()
                            //onSuccess(ret, parsePerson(dataPerson))
                            continuation.resume(dataPerson)
                        }

                        override fun onFailure(e: ApolloException) {
                            continuation.resumeWithException(e)
                        }
                    }
            )
        }
    }

}