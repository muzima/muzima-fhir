package com.muzima.muzimafhir.fhir.dao.implementation

import android.util.Log
import com.muzima.muzimafhir.data.fhir.Observation
import com.muzima.muzimafhir.fhir.dao.ObservationDao
import com.muzima.muzimafhir.fhir.query.ObservationQuery

class ObservationDaoImpl : ObservationDao {
    private val TAG = "ObservationDao"
    private val observationQuery = ObservationQuery()

    override suspend fun getObservation(id: String): Observation {
        val observation = observationQuery.queryObservationById(id)
        Log.d(TAG, "getObservation returned observation: $observation")
        return observation
    }

    override suspend fun getObservationList(): List<Observation> {
        val observationList = observationQuery.queryObservationList()
        Log.d(TAG, "getObservationList returned with ${observationList.size} elements")
        return observationList
    }

    override fun deleteObservation(id: String, o: Observation) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateObservation(id: String, o: Observation) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}