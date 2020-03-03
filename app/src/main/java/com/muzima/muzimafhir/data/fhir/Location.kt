package com.muzima.muzimafhir.data.fhir

import android.util.Log
import com.muzima.muzimafhir.data.fhir.types.Address
import com.muzima.muzimafhir.data.fhir.types.ContactPoint
import com.muzima.muzimafhir.data.fhir.types.Identifier

class Location (
    var identifier: MutableList<Identifier>? = null,
    var status: String? = null,
    var name: String? = null,
    var description: String? = null,
    var telecom: MutableList<ContactPoint>? = null,
    var address: Address? = null
) {
    val TAG = "Location"
    fun mGetFieldsAndValues() : MutableMap<String, String>{
        val map = mutableMapOf(
                "status" to status.toString(),
                "name" to name.toString(),
                "description" to description.toString(),
                //"telecom" to telecom.get(0).toString(),
                "address" to address.toString()
        )
        Log.d(TAG, toString())
        Log.d(TAG, "Location converted to map: $map")
        return map
    }


    override fun toString(): String{
        return "identifier : " + identifier.toString() + "\n" +
                "status : " + status.toString() + "\n" +
                "name : " + name.toString() + "\n" +
                "description : " + description.toString() + "\n" +
                "telecom : " + telecom.toString() + "\n" +
                "address : " + address.toString()
    }
}