package com.muzima.muzimafhir.data.fhir.types

data class Identifier(
        var use: String? = null,
        var type: CodeableConcept? = null,
        var system: String? = null,
        var value: String? = null,
        var period: Period? = null
) {
    override fun toString(): String {
        return "Identifier(use=$use, type=$type, system=$system, value=$value, period=$period)"
    }
}