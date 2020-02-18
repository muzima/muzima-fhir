package com.muzima.muzimafhir.fhir.dao.implementation

import android.util.Log
import com.muzima.muzimafhir.data.fhir.Location
import com.muzima.muzimafhir.fhir.dao.LocationDao
import com.muzima.muzimafhir.fhir.query.LocationQuery

class LocationDaoImpl : LocationDao{

    private val TAG = "LocationDao"
    private val locationQuery = LocationQuery()

    override suspend fun getLocation(id: String): Location {
        val location = locationQuery.queryLocationById(id)
        Log.d(TAG, "getLocation returned location: $location")
        return location
    }

    override suspend fun getLocationList(): List<Location> {
        val locationList = locationQuery.queryLocationList()
        Log.d(TAG, "getLocationList returned with ${locationList.size} elements")
        return locationList
    }

    override fun deleteLocation(id: String, p: Location) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateLocation(id: String, p: Location) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}