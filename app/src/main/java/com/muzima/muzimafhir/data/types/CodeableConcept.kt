package com.muzima.muzimafhir.data.types

data class CodeableConcept(
        var coding: List<Coding>? = null,
        var text: String? = null
)