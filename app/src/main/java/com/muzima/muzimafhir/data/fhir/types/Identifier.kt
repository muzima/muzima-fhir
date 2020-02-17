package com.muzima.muzimafhir.data.fhir.types

data class Identifier(
        val use: String? = null,
        val type: CodeableConcept? = null,
        val system: String? = null,
        val value: String? = null,
        val period: Period? = null
) {
    override fun toString(): String {
        return "Identifier(use=$use, type=$type, system=$system, value=$value, period=$period)"
    }
}