package com.muzima.muzimafhir.data.fhir.types

import java.util.*

data class Period(
    var start: Date? = null,
    var end: Date? = null
) {
    override fun toString(): String {
        return "Period(start=$start, end=$end)"
    }
}