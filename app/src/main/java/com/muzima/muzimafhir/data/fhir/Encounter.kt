package com.muzima.muzimafhir.data.fhir

import com.muzima.muzimafhir.data.fhir.types.CodeableConcept
import com.muzima.muzimafhir.data.fhir.types.Identifier
import com.muzima.muzimafhir.data.fhir.types.Period
import java.util.*

data class Encounter(
        var identifier: MutableList<Identifier>? = mutableListOf(),
        var status: String? = null,
        var type: MutableList<CodeableConcept>? = mutableListOf(),
        var serviceType: CodeableConcept? = null,
        var priority: CodeableConcept? = null

){
    override fun toString(): String {
        return "identifier : " + identifier.toString() + "\n" +
                "status : " + status.toString() + "\n" +
                "type : " + type.toString() + "\n" +
                "serviceType : " + serviceType.toString() + "\n" +
                "priority : " + priority.toString() + "\n"


    }
    fun mGetFieldsAndValues() : MutableMap<String, String?> {
        return mutableMapOf(
                "identifier" to identifier.toString(),
                "status" to status.toString(),
                "type" to type.toString(),
                "serviceType" to serviceType.toString(),
                "priority" to priority.toString()


        )
    }



}