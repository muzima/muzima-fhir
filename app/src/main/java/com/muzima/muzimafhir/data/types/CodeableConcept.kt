package com.muzima.muzimafhir.data.types
import com.muzima.muzimafhir.data.fhir.types.Coding

data class CodeableConcept(
        var coding: List<Coding>? = null,
        var text: String? = null
)