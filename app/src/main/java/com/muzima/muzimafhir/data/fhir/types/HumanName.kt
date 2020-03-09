package com.muzima.muzimafhir.data.fhir.types

data class HumanName(
    var use: String? = null,
    var text: String? = null,
    var family: String? = null,
    var given: MutableList<String> = mutableListOf(),
    var prefix: String? = null,
    var period: Period? = null
) {
    override fun toString(): String {
        return "HumanName(use=$use, text=$text, family=$family, given=$given, prefix=$prefix, period=$period)"
    }

    /*
    fun toMap() : MutableMap<String?, String?> {
        return mutableMapOf("use" to use, "text" to text, "family" to family, "given" to given, "prefix" to prefix)
    }
    */
}