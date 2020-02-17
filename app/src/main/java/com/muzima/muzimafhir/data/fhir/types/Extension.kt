package com.muzima.muzimafhir.data.fhir.types

data class Extension(
    var url: String? = null,
    var value: Any? = null
) {
    override fun toString(): String {
        return "Extension(url=$url, value=$value)"
    }
}