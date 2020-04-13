package com.muzima.muzimafhir.fhir.dao.implementation

import android.util.Log
import com.muzima.muzimafhir.data.fhir.Encounter
import com.muzima.muzimafhir.fhir.dao.EncounterDao
import com.muzima.muzimafhir.fhir.mutation.EncounterMutation
import com.muzima.muzimafhir.fhir.query.EncounterQuery
import graphqlcontent.type.Encounter_Input

class EncounterDaoImpl : EncounterDao{

    private val TAG = "EncounterDao"
    private val encounterQuery = EncounterQuery()
    private val encounterMutation = EncounterMutation()

    override suspend fun getEncounter(id: String): Encounter {
        val encounter = encounterQuery.queryEncounterById(id)
        Log.d(TAG, "getEncounter returned encounter: $encounter")
        return encounter    }

    override suspend fun getEncounterList(): List<Encounter> {
        val encounterList = encounterQuery.queryEncounterList()
        Log.d(TAG, "getEncounterList returned with ${encounterList.size} elements")
        return encounterList    }

    override suspend fun deleteEncounter(id: String) {
        var deleteReturnData = encounterMutation.deleteEncounter(id)
        Log.d(TAG, "deleteEncounter with id: $id and return data ${deleteReturnData?.EncounterRemove()?.id()}")
    }

    override suspend fun updateEncounter(id: String, e: Encounter_Input) {
        var returnData = encounterMutation.updateEncounter(e)
        Log.d(TAG, "updateEncounter called, return data ${returnData?.id()}")
    }

    override suspend fun createEncounter(id: String, e: Encounter_Input) {
        var returnData = encounterMutation.createEncounter(e)
        Log.d(TAG,"createEncounter called, return data ${returnData?.id()}")
    }

}