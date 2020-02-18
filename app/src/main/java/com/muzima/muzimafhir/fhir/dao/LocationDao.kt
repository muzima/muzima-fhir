package com.muzima.muzimafhir.fhir.dao
import com.muzima.muzimafhir.data.fhir.Location


interface LocationDao {
    suspend fun getLocation(id: String) : Location
    suspend fun getLocationList() : List<Location>
    fun deleteLocation(id: String, p: Location)
    fun updateLocation(id: String, p: Location)

}