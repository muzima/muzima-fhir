package com.muzima.muzimafhir.data.fhir

import com.muzima.muzimafhir.data.fhir.types.Identifier
import com.muzima.muzimafhir.data.fhir.types.CodeableConcept
import graphqlcontent.GetObservationByIdQuery
import java.util.*

data class Observation(
        var id: String? = null,
        var identifier: MutableList<Identifier>? = mutableListOf(),
        var status: String? = null,
        var category: CodeableConcept? = null,
        var code: CodeableConcept? = null,
        var issued: Date? = null,
        var subject: Patient? = null,
        var encounter: Encounter? = null,
        var valueCodeableConcept: CodeableConcept? = null,
        var valueString: String? = null,
        var valueInteger: Number? = null,
        var valueDateTime: Date? = null
) {

    fun mGetFieldsAndValues() : MutableMap<String, String?> {
        return mutableMapOf(
                "identifier" to identifier.toString(),
                "status" to status.toString(),
                "category" to category.toString(),
                "code" to code.toString(),
                "issued" to issued.toString(),
                "valueCodeableConcept" to valueCodeableConcept.toString(),
                "valueString" to valueString.toString(),
                "valueInteger" to valueInteger.toString(),
                "valueDateTime" to valueDateTime.toString()
        )
    }

    override fun toString(): String {
        return "Observation(id='$id', identifier=$identifier, status=$status, category=$category, code=$code, issued=$issued, subject=$subject, encounter=$encounter, valueCodeableConcept=$valueCodeableConcept, valueString=$valueString, valueInteger=$valueInteger, valueDateTime=$valueDateTime)"
    }
}