package com.muzima.muzimafhir.fhir.query

import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.muzima.muzimafhir.data.Person
import com.muzima.muzimafhir.fhir.client.ApplicationGraphQLClient
import typeFixFolder.GetPersonByIdQuery
import java.time.Instant
import java.util.*


class PersonQuery {

    val client = ApplicationGraphQLClient.getClient()

    fun queryPersonById(id: String){
        var q = GetPersonByIdQuery
                .builder()
                .id(id)
                .build()
        val callBack = object : ApolloCall.Callback<GetPersonByIdQuery.Data>(){
            override fun onResponse(response: Response<GetPersonByIdQuery.Data>) {
                var person = Person()
                response.data().let{
                    it?.Person()?.let { p -> {
                            person.active = p.active()
                            person.gender = p.gender() as String
                            person.birthDate = Date.from(Instant.parse(p.birthDate() as String))
                            if(p.name() != null){
                                p.name()?.forEach {

                                }
                            }
                        }
                    }
                }
            }
            override fun onFailure(e: ApolloException) {
                e.printStackTrace()
            }
        }
        client.query(q).enqueue(callBack)
    }

    fun queryPersonList(){


    }

}