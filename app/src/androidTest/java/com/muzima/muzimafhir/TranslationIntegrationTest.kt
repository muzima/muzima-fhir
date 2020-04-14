package com.muzima.muzimafhir

import android.util.Log
import com.google.gson.Gson
import com.muzima.muzimafhir.translation.FhirTranslation
import com.muzima.muzimafhir.translation.MuzimaTranslation
import org.junit.Test

class TranslationIntegrationTest {

    val TAG = "TranslationIntegrationTest"

    val gson = Gson()

    @Test
    fun muzimaObjectCanBeTranslatedAndInserted(){
        val jsonPatient = PatientData.patient1
        MuzimaTranslation.translateAndInsert("patient", jsonPatient)
    }

    @Test
    fun muzimaObjectCanBeFetchedAndTranslated(){
        val patient = FhirTranslation.fetchByIdAndTranslate("Patient", "dd9aacb4-1691-11df-97a5-7038c432aabf")
        Log.d(TAG, gson.toJson(patient))
    }

}