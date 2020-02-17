package com.muzima.muzimafhir.data.fhir.types

data class ContactPoint(
    var system: String? = null,
    var value: String? = null,
    var use: String? = null,
    var rank: Int? = null,
    var period: Period? = null
) {
    override fun toString(): String {
        return "ContactPoint(system=$system, value=$value, use=$use, rank=$rank, period=$period)"
    }
}