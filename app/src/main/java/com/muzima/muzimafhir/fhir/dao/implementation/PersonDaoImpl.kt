package com.muzima.muzimafhir.fhir.dao.implementation

import com.muzima.muzimafhir.fhir.dao.PersonDao
import com.muzima.muzimafhir.fhir.client.ResourceClient
import com.muzima.muzimafhir.data.Person

class PersonDaoImpl : PersonDao {

    private lateinit var client: ResourceClient

    override fun getPerson(id: String) : Person {
        return Person()
    }

    override fun getPersonList() : List<Person> {
        return listOf<Person>()
    }

    override fun deletePerson(id: String, p: Person) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updatePerson(id: String, p: Person) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}