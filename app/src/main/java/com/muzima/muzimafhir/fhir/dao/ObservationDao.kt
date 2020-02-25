package com.muzima.muzimafhir.fhir.dao;

import com.muzima.muzimafhir.data.fhir.Observation;
import typeFixFolder.type.Observation_Input

interface ObservationDao {
    suspend fun getObservation(id: String) : Observation
    suspend fun getObservationList() : List<Observation>
    suspend fun deleteObservation(id: String, o: Observation)
    suspend fun updateObservation(id: String, o: Observation)
    suspend fun createObservation(id: String, o: Observation_Input)
}
