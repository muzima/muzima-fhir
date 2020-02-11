package com.muzima.muzimafhir.data.types

data class Identifier(
        var use: String? = null,
        var type: CodeableConcept? = null,
        var system: String? = null,
        var value: String? = null,
        var period: Period? = null
)