package com.muzima.muzimafhir.fhir.dao
import com.muzima.muzimafhir.data.fhir.Encounter
import typeFixFolder.type.Encounter_Input

interface EncounterDao {

    suspend fun getEncounter(id: String) : Encounter
    suspend fun getEncounterList() : List<Encounter>
    suspend fun deleteEncounter(id: String) // e: Encounter)
    suspend fun updateEncounter(id: String, e: Encounter_Input)
    suspend fun createEncounter(id: String, e: Encounter_Input)
}