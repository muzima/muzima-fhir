package com.muzima.muzimafhir.data

import com.muzima.muzimafhir.data.fhir.types.CodeableConcept
import com.muzima.muzimafhir.data.fhir.types.Identifier
import java.util.*

data class Encounter(
        var identifier: List<Identifier>? = null,
        var status: String? = null,
        var type: List<CodeableConcept>? = null,
        var serviceType: CodeableConcept? = null,
        var priority: CodeableConcept? = null,
        var subject: String? = null,
        var basedOn: String? = null,
        var period: Date? = null,
        var length: String? = null,
        var reasonReference: String? = null,
        var diagnosis: List<String>? = null,
        var location: String? = null
){
    override fun toString(): String {
        return "identifier : " + identifier.toString() + "\n" +
                "status : " + status.toString() + "\n" +
                "type : " + type.toString() + "\n" +
                "serviceType : " + serviceType.toString() + "\n" +
                "priority : " + priority.toString() + "\n" +
                "subject : " + subject.toString() + "\n" +
                "basedOn : " + basedOn.toString() + "\n" +
                "period : " + period.toString() + "\n" +
                "length : " + length.toString() + "\n" +
                "reasonReference : " + reasonReference.toString() + "\n" +
                "diagnosis : " + diagnosis.toString() + "\n" +
                "location : " + location.toString()
    }



}