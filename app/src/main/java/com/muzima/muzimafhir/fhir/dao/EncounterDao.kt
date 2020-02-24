package com.muzima.muzimafhir.fhir.dao
import com.muzima.muzimafhir.data.fhir.Encounter

interface EncounterDao {

    suspend fun getEncounter(id: String) : Encounter
    suspend fun getEncounterList() : List<Encounter>
    fun deleteEncounter(id: String, p: Encounter)
    fun updateEncounter(id: String, p: Encounter)

}