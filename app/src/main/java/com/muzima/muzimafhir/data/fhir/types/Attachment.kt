package com.muzima.muzimafhir.data.fhir.types

import java.util.*

data class Attachment(
    var contentType: String? = null,
    var language: String? = null,
    var data: Base64? = null,
    var url: String? = null,
    var size: Int? = null,
    var hash: Base64? = null,
    var title: String? = null,
    var creation: Date? = null
) {
    override fun toString(): String {
        return "Attachment(contentType=$contentType, language=$language, data=$data, url=$url, size=$size, hash=$hash, title=$title, creation=$creation)"
    }
}