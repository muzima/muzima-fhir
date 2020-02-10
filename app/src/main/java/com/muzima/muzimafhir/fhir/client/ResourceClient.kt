package com.muzima.muzimafhir.fhir.client


interface ResourceClient {
    fun<T> getResource(type: Class<Any>, id: String)
    fun<T> getAllResources(type: Class<Any>)
    fun<T> deleteResource(type: Class<Any>, id: String)
    fun<T> updateResource(type:Class<Any>, id: String)
}