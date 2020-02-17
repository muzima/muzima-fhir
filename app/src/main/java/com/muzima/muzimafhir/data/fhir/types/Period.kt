package com.muzima.muzimafhir.data.fhir.types

import java.util.*

data class Period(
    val start: Date? = null,
    val end: Date? = null
) {
    override fun toString(): String {
        return "Period(start=$start, end=$end)"
    }
}