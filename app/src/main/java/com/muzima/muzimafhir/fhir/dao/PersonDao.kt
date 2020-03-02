package com.muzima.muzimafhir.fhir.dao

import com.muzima.muzimafhir.data.fhir.Person
import typeFixFolder.type.Person_Input

interface PersonDao {
    suspend fun getPerson(id: String) : Person
    suspend fun getPersonList() : List<Person>
    suspend fun deletePerson(id: String) //, p: Person)
    suspend fun updatePerson(id: String, p: Person_Input)
    suspend fun createPerson(id: String, p: Person_Input)
}