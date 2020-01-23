package com.muzima.muzimafhir.data

import com.muzima.muzimafhir.data.types.*
import java.util.*

data class Person(
        var identifier: List<Identifier>? = null,
        var name: List<HumanName>? = null,
        var telecom: List<ContactPoint>? = null,
        var gender: String? = null,
        var birthDate: Date? = null,
        var address: List<Address>? = null,
        var photo: Attachment? = null,
        var active: Boolean? = null
){
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