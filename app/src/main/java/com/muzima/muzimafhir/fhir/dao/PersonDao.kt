package com.muzima.muzimafhir.fhir.dao

import com.muzima.muzimafhir.data.fhir.Person

interface PersonDao {
    suspend fun getPerson(id: String) : Person
    suspend fun getPersonList() : List<Person>
    fun deletePerson(id: String, p: Person)
    fun updatePerson(id: String, p: Person)
}