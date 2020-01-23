package com.muzima.muzimafhir.data.types

data class ContactPoint(
    var system: String? = null,
    var value: String? = null,
    var use: String? = null,
    var rank: Int? = null,
    var period: Period? = null
)