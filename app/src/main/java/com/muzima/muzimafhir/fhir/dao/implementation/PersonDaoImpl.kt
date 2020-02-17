package com.muzima.muzimafhir.fhir.dao.implementation

import android.util.Log
import com.muzima.muzimafhir.fhir.dao.PersonDao
import com.muzima.muzimafhir.data.fhir.Person
import com.muzima.muzimafhir.fhir.query.PersonQuery

class PersonDaoImpl : PersonDao {

    private val TAG = "PersonDao"
    private val personQuery = PersonQuery()

    override suspend fun getPerson(id: String) : Person {
        val person = personQuery.queryPersonById(id)
        Log.d(TAG, "getPerson returned person: $person")
        return personQuery.queryPersonById(id)
    }

    override suspend fun getPersonList() : List<Person> {
        val personList = personQuery.queryPersonList()
        Log.d(TAG, "getPersonList returned with ${personList.size} elements")
        return personList
    }

    override fun deletePerson(id: String, p: Person) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updatePerson(id: String, p: Person) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}