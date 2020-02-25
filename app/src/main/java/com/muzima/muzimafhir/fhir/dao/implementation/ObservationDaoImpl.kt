package com.muzima.muzimafhir.fhir.dao.implementation

import android.util.Log
import com.muzima.muzimafhir.data.fhir.Observation
import com.muzima.muzimafhir.fhir.dao.ObservationDao
import com.muzima.muzimafhir.fhir.mutation.ObservationMutation
import com.muzima.muzimafhir.fhir.query.ObservationQuery
import typeFixFolder.type.Observation_Input

class ObservationDaoImpl : ObservationDao {
    private val TAG = "ObservationDao"
    private val observationQuery = ObservationQuery()
    private val observationMutation = ObservationMutation()

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

    override suspend fun deleteObservation(id: String, o: Observation) {
        var deleteReturnData = observationMutation.deleteObservation(id)
        Log.d(TAG, "deleteObservation with id: $id and return data ${deleteReturnData?.ObservationRemove()?.id()}")
    }

    override suspend fun updateObservation(id: String, o: Observation) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun createObservation(id: String, o: Observation_Input) {
        var returnData = observationMutation.createObservation(o)
        Log.d(TAG, "createObservation called, return data ${returnData?.id()}")

    }
}