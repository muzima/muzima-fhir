package com.muzima.muzimafhir

import com.muzima.muzimafhir.translation.MuzimaTranslation
import org.junit.Test

class TranslationIntegrationTest {

    @Test
    fun muzimaObjectCanBeTranslatedAndInserted(){
        val jsonPatient = PatientData.patient1
        MuzimaTranslation.translateAndInsert("patient", jsonPatient)
    }

}