package com.muzima.muzimafhir.data.fhir.types

data class CodeableConcept(
        var coding: MutableList<Coding?> = mutableListOf(),
        var text: String? = null
) {
    override fun toString(): String {
        return "CodeableConcept(coding=$coding, text=$text)"
    }
}
