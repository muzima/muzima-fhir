package com.muzima.muzimafhir.fhir.client

import android.util.Log
import com.apollographql.apollo.ApolloClient
import okhttp3.OkHttpClient


class ApplicationGraphQLClient {
    companion object{
        val instance = ApplicationGraphQLClient()
        private var graphQLClient: ApolloClient
        //private val BASE_URL = "http://45.79.198.132:3000/4_0_0/\$graphql"
        private val BASE_URL = "http://10.0.2.2:3000/4_0_0/\$graphql"
        private val TAG = "ApplicationGraphQLClient"

        init {
            graphQLClient = initializeClient()
        }

        fun getClient() : ApolloClient{
            return graphQLClient
        }

        private fun initializeClient() : ApolloClient {
            Log.d(TAG,"Building Apollo client...")
            var okHttpClient = OkHttpClient.Builder().build()
            graphQLClient = ApolloClient
                    .builder()
                    .serverUrl(BASE_URL)
                    .okHttpClient(okHttpClient)
                    .build()
            Log.d(TAG, "Apollo client built!")
            return graphQLClient
        }

    }
}