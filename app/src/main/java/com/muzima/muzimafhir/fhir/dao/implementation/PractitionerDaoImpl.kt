package com.muzima.muzimafhir.fhir.dao.implementation

import android.util.Log
import com.muzima.muzimafhir.fhir.dao.PractitionerDao
import com.muzima.muzimafhir.data.fhir.Practitioner
import com.muzima.muzimafhir.fhir.mutation.PractitionerMutation
import com.muzima.muzimafhir.fhir.query.PractitionerQuery
import graphqlcontent.type.Practitioner_Input

class PractitionerDaoImpl : PractitionerDao {

    private val TAG = "PractitionerDao"
    private val practitionerQuery = PractitionerQuery()
    private val practitionerMutation = PractitionerMutation()

    override suspend fun getPractitioner(id: String) : Practitioner {
        val practitioner = practitionerQuery.queryPractitionerById(id)
        Log.d(TAG, "getPractitioner returned practitioner: $practitioner")
        return practitionerQuery.queryPractitionerById(id)
    }

    override suspend fun getPractitionerList() : List<Practitioner> {
        val practitionerList = practitionerQuery.queryPractitionerList()
        Log.d(TAG, "getPractitionerList returned with ${practitionerList.size} elements")
        return practitionerList
    }


    override suspend fun deletePractitioner(id: String) { //, p: Practitioner) {
        var deleteReturnData = practitionerMutation.deletePractitioner(id)
        Log.d(TAG, "deletePractitioner with id: $id and return data ${deleteReturnData?.PractitionerRemove()?.id()}")
    }

    override suspend fun updatePractitioner(id: String, p: Practitioner_Input) {
        var returnData = practitionerMutation.updatePractitioner(p)
        Log.d(TAG, "updatePractitioner called, return data ${returnData?.id()}")
    }

    override suspend fun createPractitioner(/*id: String, */p: Practitioner_Input) {
        var returnData = practitionerMutation.createPractitioner(p)
        Log.d(TAG, "createPractitioner called, return data ${returnData?.id()}")

    }

}