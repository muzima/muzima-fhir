package com.muzima.muzimafhir.fhir.dao.implementation

import android.util.Log
import com.muzima.muzimafhir.fhir.dao.PersonDao
import com.muzima.muzimafhir.data.fhir.Person
import com.muzima.muzimafhir.fhir.mutation.PersonMutation
import com.muzima.muzimafhir.fhir.query.PersonQuery
import typeFixFolder.type.Person_Input

class PersonDaoImpl : PersonDao {

    private val TAG = "PersonDao"
    private val personQuery = PersonQuery()
    private val personMutation = PersonMutation()

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

    override suspend fun deletePerson(id: String, p: Person) {
        var deleteReturnData = personMutation.deletePerson(id)
        Log.d(TAG, "deletePerson with id: $id and return data ${deleteReturnData?.PersonRemove()?.id()}")

    }

    override suspend fun updatePerson(id: String, p: Person) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun createPerson(id: String, p: Person_Input) {
        var returnData = personMutation.createPerson(p)
        Log.d(TAG, "createPerson called, return data ${returnData?.id()}")

    }
}