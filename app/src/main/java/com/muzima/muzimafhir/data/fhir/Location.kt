package com.muzima.muzimafhir.data.fhir

import com.muzima.muzimafhir.data.fhir.types.Address
import com.muzima.muzimafhir.data.fhir.types.ContactPoint
import com.muzima.muzimafhir.data.fhir.types.Identifier

class Location (
    var identifier: MutableList<Identifier>? = null,
    var status: String? = null,
    var name: String? = null,
    var alias: MutableList<String>? = null,
    var description: String? = null,
    var telecom: MutableList<ContactPoint>? = null,
    var address: Address? = null
) {
    override fun toString(): String{
        return "identifier : " + identifier.toString() + "\n" +
                "status : " + status.toString() + "\n" +
                "name : " + name.toString() + "\n" +
                "alias : " + alias.toString() + "\n" +
                "description : " + description.toString() + "\n" +
                "telecom : " + telecom.toString() + "\n" +
                "address : " + address.toString()
    }
}