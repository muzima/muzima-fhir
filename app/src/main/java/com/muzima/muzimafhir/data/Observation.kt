package com.muzima.muzimafhir.data

import com.muzima.muzimafhir.data.types.CodeableConcept
import com.muzima.muzimafhir.data.types.Identifier
import com.muzima.muzimafhir.data.types.Period
import java.util.*

data class Observation(
        var identifier: Identifier,
        var status: String,
        var category: CodeableConcept,
        var code: CodeableConcept,
        var effectiveDateTime: Date,
        var effectivePeriod: Period
)