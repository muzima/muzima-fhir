package com.muzima.muzimafhir.data.fhir.types

data class Element(
    var id: String? = null,
    var extension: Extension? = null
) {
    override fun toString(): String {
        return "Element(id=$id, extension=$extension)"
    }
}