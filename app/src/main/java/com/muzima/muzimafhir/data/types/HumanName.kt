package com.muzima.muzimafhir.data.types

data class HumanName(
    var use: String? = null,
    var text: String? = null,
    var family: String? = null,
    var given: String? = null,
    var prefix: String? = null,
    var period: Period? = null
)