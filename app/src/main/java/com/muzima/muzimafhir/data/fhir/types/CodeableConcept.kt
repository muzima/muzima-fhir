package com.muzima.muzimafhir.data.fhir.types

data class CodeableConcept(
        val coding: Coding? = null,
        val text: String? = null
) {
    override fun toString(): String {
        return "CodeableConcept(coding=$coding, text=$text)"
    }
}
