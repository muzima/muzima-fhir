package com.muzima.muzimafhir.data.fhir.types

data class Coding(
    var system: String? = null,
    var version: String? = null,
    var code: String? = null,
    var display: String? = null,
    var userSelected: Boolean? = null
) {
    override fun toString(): String {
        return "Coding(system=$system, version=$version, code=$code, display=$display, userSelected=$userSelected)"
    }
}