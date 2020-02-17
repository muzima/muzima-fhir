package com.muzima.muzimafhir.data.fhir.types

data class Coding(
    val system: String? = null,
    val version: String? = null,
    val code: String? = null,
    val display: String? = null,
    val userSelected: Boolean? = null
) {
    override fun toString(): String {
        return "Coding(system=$system, version=$version, code=$code, display=$display, userSelected=$userSelected)"
    }
}