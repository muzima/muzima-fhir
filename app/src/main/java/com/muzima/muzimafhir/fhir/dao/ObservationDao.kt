package com.muzima.muzimafhir.fhir.dao;

import com.muzima.muzimafhir.data.fhir.Observation;

interface ObservationDao {
    suspend fun getObservation(id: String) : Observation
    suspend fun getObservationList() : List<Observation>
    fun deleteObservation(id: String, o: Observation)
    fun updateObservation(id: String, o:Observation)
    // TODO: createObservation
}
