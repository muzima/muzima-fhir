package com.muzima.muzimafhir.fhir.dao.implementation

import android.util.Log
import com.muzima.muzimafhir.data.fhir.Encounter
import com.muzima.muzimafhir.fhir.dao.EncounterDao
import com.muzima.muzimafhir.fhir.query.EncounterQuery

class EncounterDaoImpl : EncounterDao{



    override suspend fun getEncounter(id: String): Encounter {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getEncounterList(): List<Encounter> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteEncounter(id: String, p: Encounter) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateEncounter(id: String, p: Encounter) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}