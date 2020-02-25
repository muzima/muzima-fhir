package com.muzima.muzimafhir.fhir.dao.implementation

import android.util.Log
import com.muzima.muzimafhir.data.fhir.Patient
import com.muzima.muzimafhir.fhir.dao.PatientDao
import com.muzima.muzimafhir.fhir.mutation.PatientMutation
import com.muzima.muzimafhir.fhir.query.PatientQuery
import typeFixFolder.type.Patient_Input

class PatientDaoImpl : PatientDao {

    private val TAG = "PatientDao"
    private val patientQuery = PatientQuery()
    private val patientMutation = PatientMutation()

    override suspend fun getPatient(id: String): Patient {
        val patient = patientQuery.queryPatientById(id)
        Log.d(TAG, "getPatient returned patient: $patient")
        return patientQuery.queryPatientById(id)
    }

    override suspend fun getPatientList(): List<Patient> {
        val patientList = patientQuery.queryPatientList()
        Log.d(TAG, "getPatientList returned with ${patientList.size} elements")
        return patientList
    }

    override suspend fun deletePatient(id: String, p: Patient) {
        var deleteReturnData = patientMutation.deletePatient(id)
        Log.d(TAG, "deletePatient with id: $id and return data ${deleteReturnData?.PatientRemove()?.id()}")
    }

    override suspend fun updatePatient(id: String, p: Patient) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun createPatient(id: String, p: Patient_Input) {
        var returnData = patientMutation.createPatient(p)
        Log.d(TAG, "createPatient called, return data ${returnData?.id()}")
    }
}