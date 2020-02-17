package com.muzima.muzimafhir.fhir.dao

import com.muzima.muzimafhir.data.fhir.Patient

interface PatientDao {
    suspend fun getPatient(id: String) : Patient
    suspend fun getPatientList() : List<Patient>
    fun deletePatient(id: String, p: Patient)
    fun updatePatient(id: String, p: Patient)
}