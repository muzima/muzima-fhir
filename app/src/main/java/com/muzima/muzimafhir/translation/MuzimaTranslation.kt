package com.muzima.muzimafhir.translation

import android.util.Log
import com.google.gson.Gson
import com.muzima.muzimafhir.data.fhir.types.Address
import com.muzima.muzimafhir.data.fhir.types.ContactPoint
import com.muzima.muzimafhir.data.fhir.types.Extension
import com.muzima.muzimafhir.data.fhir.types.HumanName
import com.muzima.muzimafhir.fhir.dao.PatientDao
import com.muzima.muzimafhir.fhir.dao.implementation.PatientDaoImpl
import graphqlcontent.type.*
import kotlinx.coroutines.runBlocking

// Aliasing imports for clarity

import com.muzima.muzimafhir.data.fhir.Patient as fhirPatient
import com.muzima.muzimafhir.data.muzima.Patient as muzimaPatient


/***
 * A class containing the translation logic for converting a mUzima entitiy to a FHIR resource.
 */
class MuzimaTranslation {
    companion object {
        val TAG = "MuzimaTranslation"
        val gson = Gson()
        val patientDao: PatientDao = PatientDaoImpl()

        fun toFhirObject(muzimaObject: Any) : Any {
            if(muzimaObject is muzimaPatient){
                return muzimaPatientToFhirPatient(muzimaObject)
            }
            throw IllegalArgumentException("Valid argument required")
        }

        /***
         * Converts the argument Muzima patient to a FHIR Patient resource.
         */
        fun muzimaPatientToFhirPatient(muzimaPatient: muzimaPatient) : fhirPatient {
            var fhirPatient = fhirPatient()

            // Address
            var fhirAddresses = mutableListOf<Address>()
            muzimaPatient.addresses.forEach {muzimaAddress ->
                var fhirAddress = Address()
                fhirAddress.city = muzimaAddress.cityVillage
                fhirAddress.country = muzimaAddress.country
                fhirAddress.line?.add((muzimaAddress.address1))
                fhirAddress.state = muzimaAddress.stateProvince
                fhirAddress.postalCode = muzimaAddress.postalCode
                if(muzimaAddress.isPreferred) fhirAddress.use = "usual"
                fhirAddresses.add(fhirAddress)
            }
            fhirPatient.address = fhirAddresses

            if(muzimaPatient.gender == "F") fhirPatient.gender = "female"
            if(muzimaPatient.gender == "M") fhirPatient.gender = "male"

            fhirPatient.birthDate = muzimaPatient.birthdate

            // Name
            var fhirNames = mutableListOf<HumanName>()
            muzimaPatient.names.forEach{ name ->
                var fhirName = HumanName()
                fhirName.family = name.familyName
                var givenName = name.givenName.split(" ")
                givenName.forEach{givenName ->
                    fhirName.given.add(givenName)
                }
                if(name.isPreferred) fhirName.use = "usual"
                fhirNames.add(fhirName)
            }
            fhirPatient.name = fhirNames
            fhirPatient.active = !muzimaPatient.isVoided

            var fhirTelecom = ContactPoint()
            var muzimaAttribute = muzimaPatient.atributes.find { attribute -> attribute.attributeType.name == "phone" }
            fhirTelecom.value = muzimaAttribute?.attribute
            fhirPatient.id = muzimaPatient.uuid

            var extension = Extension()
            extension.url = "com.muzima.Patient.birthdateEstimated"
            extension.value = muzimaPatient.birthdateEstimated
            fhirPatient.extension?.add(extension)

            return fhirPatient
        }


        /***
         * Converts the argument string to an object before calling the appropriate dao insert method.
         */
        fun translateAndInsert(resourceType: String, resourceJson: String){
            if(resourceType == "patient"){
                val muzimaPatient = gson.fromJson(resourceJson, muzimaPatient::class.java)
                Log.d(TAG, gson.toJson(muzimaPatient))
                val fhirPatient: fhirPatient = toFhirObject(muzimaPatient) as fhirPatient

                val addressInputList = mutableListOf<Address_Input>()
                val nameInputList = mutableListOf<HumanName_Input>()
                val telecomInputList = mutableListOf<ContactPoint_Input>()
                val extensionInputList = mutableListOf<Extension_Input>()

                fhirPatient.address?.forEach { address ->
                    var temp = Address_Input.builder()
                            .city(address.city)
                            .country(address.country)
                            .line(mutableListOf(address.line?.get(0)))
                            .state(address.state)
                            .postalCode(address.postalCode)
                            .build()
                    addressInputList.add(temp)
                }

                fhirPatient.name?.forEach { name ->
                    var temp = HumanName_Input.builder()
                            .family(name.family)
                            .given(name.given)
                            .use(name.use)
                            .build()
                    nameInputList.add(temp)
                }

                fhirPatient.telecom?.forEach {contactPoint ->
                    val temp = ContactPoint_Input.builder()
                            .value(contactPoint.value)
                            .build()
                    telecomInputList.add(temp)
                }

                fhirPatient.extension?.forEach { extension ->
                    if(extension.url == "com.muzima.Patient.birthdateEstimated"){
                        val temp = Extension_Input.builder()
                                .url("com.muzima.Patient.birthdateEstimated")
                                .valueBoolean(extension.value as Boolean)
                                .build()
                        extensionInputList.add(temp)
                    }
                }

                val input = Patient_Input.builder()
                        .active(fhirPatient.active)
                        .gender(fhirPatient.gender)
                        .birthDate(fhirPatient.birthDate)
                        .address(addressInputList)
                        .name(nameInputList)
                        .id(fhirPatient.id)
                        .telecom(telecomInputList)
                        .resourceType(Patient_Enum_input.PATIENT)
                        .extension(extensionInputList)
                        .build()

                val id = fhirPatient.id

                runBlocking {
                    patientDao.createPatient(input, id)
                }
            }
        }
    }
}