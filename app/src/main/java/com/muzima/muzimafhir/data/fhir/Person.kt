package com.muzima.muzimafhir.data.fhir


import android.util.Log
import com.muzima.muzimafhir.data.fhir.types.*
import java.util.*

data class Person(
        var identifier: MutableList<Identifier>? = mutableListOf(),
        var name: MutableList<HumanName>? = mutableListOf(),
        var telecom: MutableList<ContactPoint>? = mutableListOf(),
        var gender: String? = null,
        var birthDate: Date? = null,
        var address: MutableList<Address>? = mutableListOf(),
        var photo: Attachment? = null,
        var active: Boolean? = null
){
    val TAG = "Person"

    fun getFieldsAndValues() : MutableMap<String, String>{
        return mutableMapOf(
        "identifier" to identifier.toString(),
        "name" to name.toString(),
        "telecom" to telecom.toString(),
        "gender" to gender.toString(),
        "birthDate" to birthDate.toString(),
        "address" to address.toString(),
        "photo" to photo.toString(),
        "active" to active.toString())
    }

    fun mGetFieldsAndValues() : MutableMap<String, String> {
        val map =  mutableMapOf(
                //"nameUse1" to name?.get(0)?.use.toString(),
                //"nameFamily1" to name?.get(0)?.family.toString(),
                //"nameUse2" to name?.get(1)?.use.toString(),
                //"addressLine" to address?.get(0)?.line?.get(0).toString(),
                "birthDate" to birthDate.toString(),
                "gender" to gender.toString(),
                "active" to active.toString()
        )
        Log.d(TAG, toString())
        Log.d(TAG, "person converted to map: $map")
        return map
    }

    override fun toString(): String {
        return "identifier :" + identifier.toString() + "\n" +
                "name :" + name.toString() + "\n" +
                "telecom :" + telecom.toString() + "\n" +
                "gender :" + gender.toString() + "\n" +
                "birthDate :" + birthDate.toString() + "\n" +
                "address :" + address.toString() + "\n" +
                "photo :" + photo.toString() + "\n" +
                "active :" + active.toString()
    }
}