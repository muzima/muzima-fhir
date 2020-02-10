package com.muzima.muzimafhir.fhir.dao

import com.muzima.muzimafhir.data.Person

interface PersonDao {
    fun getPerson(id: String) : Person
    fun getPersonList() : List<Person>
    fun deletePerson(id: String, p: Person)
    fun updatePerson(id: String, p: Person)
}