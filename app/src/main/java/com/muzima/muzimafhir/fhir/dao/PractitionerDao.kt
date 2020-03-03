package com.muzima.muzimafhir.fhir.dao

import com.muzima.muzimafhir.data.fhir.Practitioner
import typeFixFolder.type.Practitioner_Input

//import typeFixFolder.type.Practitioner_Input

interface PractitionerDao {
    suspend fun getPractitioner(id: String) : Practitioner
    suspend fun getPractitionerList() : List<Practitioner>
    suspend fun deletePractitioner(id: String) //, p: Practitioner)
    suspend fun updatePractitioner(id: String, p: Practitioner_Input)
    suspend fun createPractitioner(id: String, p: Practitioner_Input)
}