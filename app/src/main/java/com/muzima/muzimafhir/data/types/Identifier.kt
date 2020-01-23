package com.muzima.muzimafhir.data.types

data class Identifier(
        val use: String? = null,
        val type: CodeableConcept? = null,
        val system: String? = null,
        val value: String? = null,
        val period: Period? = null
)