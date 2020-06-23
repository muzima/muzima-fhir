package com.muzima.muzimafhir.translation

import com.muzima.muzimafhir.data.fhir.Patient
import com.muzima.muzimafhir.data.fhir.types.Address
import com.muzima.muzimafhir.data.fhir.types.ContactPoint
import com.muzima.muzimafhir.data.fhir.types.Extension
import com.muzima.muzimafhir.data.fhir.types.HumanName
import com.muzima.muzimafhir.data.muzima.*
import com.muzima.muzimafhir.data.fhir.Patient as fhirPatient
import com.muzima.muzimafhir.data.muzima.Patient as muzimaPatient

class PatientTranslation{
    // Since the translation code is independent of state we can probably
    // keep it a singleton.
    companion object {

        /***
         * Converts the argument FHIR patient to a mUzima patient.
         */
        fun fhirPatientToMuzimaPatient(p: Patient) : com.muzima.muzimafhir.data.muzima.Patient {
            val mPatient = com.muzima.muzimafhir.data.muzima.Patient()
            val addresses = mutableListOf<PersonAddress>()
            p.address?.forEach {fAddress ->
                var mAddress = PersonAddress()
                mAddress.isPreferred = fAddress.use == "usual"
                mAddress.country = fAddress.country
                mAddress.postalCode = fAddress.postalCode
                mAddress.address1 = fAddress.line?.get(0)
                mAddress.cityVillage = fAddress.city
                mAddress.stateProvince = fAddress.state
                addresses.add(mAddress)
            }
            mPatient.addresses = addresses
            mPatient.gender = p.gender
            mPatient.birthdate = p.birthDate
            val names = mutableListOf<PersonName>()
            p.name?.forEach { fName ->
                var mName = PersonName()
                mName.familyName = fName.family
                mName.isPreferred = fName.use == "usual"
                mName.givenName = fName.given.joinToString(" ")
                names.add(mName)
            }
            mPatient.names = names
            val patientIdentifiers = mutableListOf<PatientIdentifier>()
            p.identifier?.forEach { identifier ->
                val patientIdentifier = PatientIdentifier()
                patientIdentifier.identifier = identifier.value
                patientIdentifiers.add(patientIdentifier)
            }
            mPatient.identifiers = patientIdentifiers
            if (p.active != null) { mPatient.isVoided = !p.active!! }
            if(p.telecom?.size!! > 0) {
                val personAttribute = PersonAttribute()
                val personAttributeType = PersonAttributeType()
                personAttributeType.name = "phone"
                personAttribute.attributeType = personAttributeType
                if (p.telecom?.size!! > 0) personAttribute.attribute = p.telecom?.get(0)?.value
                mPatient.atributes.add(personAttribute)
            }
            mPatient.uuid = p.id
            p.extension?.forEach { extension ->
                if(extension.url == "com.muzima.Patient.birthdateEstimated"){
                    mPatient.birthdateEstimated = extension.value == true
                }
            }
            return mPatient
        }

        /***
         * Converts the argument Muzima patient to a FHIR Patient resource.
         */
        fun muzimaPatientToFhirPatient(muzimaPatient: com.muzima.muzimafhir.data.muzima.Patient) : Patient {
            val fhirPatient = Patient()
            val fhirAddresses = mutableListOf<Address>()
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
            val fhirNames = mutableListOf<HumanName>()
            muzimaPatient.names.forEach{ name ->
                val fhirName = HumanName()
                fhirName.family = name.familyName
                val givenName = name.givenName.split(" ")
                givenName.forEach{givenName ->
                    fhirName.given.add(givenName)
                }
                if(name.isPreferred) fhirName.use = "usual"
                fhirNames.add(fhirName)
            }
            fhirPatient.name = fhirNames
            fhirPatient.active = !muzimaPatient.isVoided
            val fhirTelecom = ContactPoint()
            val muzimaAttribute = muzimaPatient.atributes.find { attribute -> attribute.attributeType.name == "phone" }
            fhirTelecom.value = muzimaAttribute?.attribute
            fhirPatient.id = muzimaPatient.uuid
            val extension = Extension()
            extension.url = "com.muzima.Patient.birthdateEstimated"
            extension.value = muzimaPatient.birthdateEstimated
            fhirPatient.extension?.add(extension)

            return fhirPatient
        }
    }
}