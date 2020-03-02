package com.muzima.muzimafhir.data.fhir

import com.muzima.muzimafhir.data.fhir.types.*
import java.util.*

data class Practitioner (
    var id: String? = null,
    var identifier: MutableList<Identifier>? = mutableListOf(),
    var name: MutableList<HumanName>? = mutableListOf(),
    var telecom: MutableList<ContactPoint>? = mutableListOf(),
    var gender: String? = null,
    var birthDate: Date? = null,
    var address: MutableList<Address>? = mutableListOf(),
    var active: Boolean? = null
) {
    val TAG = "Practitioner"

    fun getFieldsAndValues() : MutableMap<String, String>{
        return mutableMapOf(
                "id" to id.toString(),
                "identifier" to identifier.toString(),
                "name" to name.toString(),
                "telecom" to telecom.toString(),
                "gender" to gender.toString(),
                "birthDate" to birthDate.toString(),
                "address" to address.toString(),
                "active" to active.toString())
    }

    override fun toString(): String {
        return  "id: " + id.toString() + "\n" +
                "identifier :" + identifier.toString() + "\n" +
                "name :" + name.toString() + "\n" +
                "telecom :" + telecom.toString() + "\n" +
                "gender :" + gender.toString() + "\n" +
                "birthDate :" + birthDate.toString() + "\n" +
                "address :" + address.toString() + "\n" +
                "active :" + active.toString()
    }
}