package com.muzima.muzimafhir.fhir.dao.implementation

import android.util.Log
import com.muzima.muzimafhir.data.fhir.Encounter
import com.muzima.muzimafhir.fhir.dao.EncounterDao
import com.muzima.muzimafhir.fhir.query.EncounterQuery

class EncounterDaoImpl : EncounterDao{

    private val TAG = "EncounterDao"
    private val encounterQuery = EncounterQuery()

    override suspend fun getEncounter(id: String): Encounter {
        val encounter = encounterQuery.queryEncounterById(id)
        Log.d(TAG, "getEncounter returned encounter: $encounter")
        return encounter    }

    override suspend fun getEncounterList(): List<Encounter> {
        val encounterList = encounterQuery.queryEncounterList()
        Log.d(TAG, "getEncounterList returned with ${encounterList.size} elements")
        return encounterList    }

    override fun deleteEncounter(id: String, p: Encounter) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateEncounter(id: String, p: Encounter) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}