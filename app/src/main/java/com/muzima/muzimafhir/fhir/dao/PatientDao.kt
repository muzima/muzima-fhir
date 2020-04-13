package com.muzima.muzimafhir.fhir.dao

import com.muzima.muzimafhir.data.fhir.Patient
import graphqlcontent.type.Patient_Input

interface PatientDao {
    suspend fun getPatient(id: String) : Patient
    suspend fun getPatientList() : List<Patient>
    suspend fun deletePatient(id: String)//, p: Patient)
    suspend fun updatePatient(id: String, p: Patient)
    suspend fun createPatient(p: Patient_Input, id: String)
}