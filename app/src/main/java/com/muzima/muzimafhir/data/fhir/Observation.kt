package com.muzima.muzimafhir.data.fhir

import com.muzima.muzimafhir.data.fhir.types.Identifier
import com.muzima.muzimafhir.data.fhir.types.CodeableConcept
import java.util.*

data class Observation(
        var identifier: MutableList<Identifier>? = mutableListOf(),
        var status: String? = null,
        var category: CodeableConcept? = null,
        var code: CodeableConcept? = null,
        var issued: Date? = null,
        var valueCodeableConcept: CodeableConcept? = null,
        var valueString: String? = null,
        var valueInteger: Number? = null,
        var valueDateTime: Date? = null
) {
    override fun toString(): String {
        return "identifier :" + identifier.toString() + "\n" +
                "status :" + status.toString() + "\n" +
                "category :" + category.toString() + "\n" +
                "code :" + code.toString() + "\n" +
                "issued :" + issued.toString() + "\n" +
                "valueCodeableConcept :" + valueCodeableConcept.toString() + "\n" +
                "valueString :" + valueString.toString() + "\n" +
                "valueInteger :" + valueInteger.toString() + "\n" +
                "valueDateTime :" + valueDateTime.toString() + "\n"

    }
}