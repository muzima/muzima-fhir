package com.muzima.muzimafhir.translation

// Aliasing out imports for clarity
import com.muzima.muzimafhir.data.fhir.types.Address
import com.muzima.muzimafhir.data.fhir.types.ContactPoint
import com.muzima.muzimafhir.data.fhir.types.HumanName
import com.muzima.muzimafhir.data.fhir.Patient as fhirPatient
import com.muzima.muzimafhir.data.muzima.Patient as muzimaPatient

class MuzimaTranslation {
    companion object {
        fun toFhirObject(muzimaObject: Any) : Any {
            if(muzimaObject is muzimaPatient){
                return muzimaPatientToFhirPatient(muzimaObject)
            }
            throw IllegalArgumentException("Valid argument required")
        }

        fun muzimaPatientToFhirPatient(muzimaPatient: muzimaPatient) : fhirPatient {
            var fhirPatient = fhirPatient()

            // Address
            var fhirAddress = Address()
            fhirAddress.city = muzimaPatient.addresses[0].cityVillage
            fhirAddress.country = muzimaPatient.addresses[0].country
            fhirAddress.line?.set(0, muzimaPatient.addresses[0].address1)
            fhirAddress.state = muzimaPatient.addresses[0].stateProvince
            fhirAddress.postalCode = muzimaPatient.addresses[0].postalCode
            if(muzimaPatient.addresses[0].isPreferred) fhirAddress.use = "usual"
            fhirPatient.address?.add(fhirAddress)

            fhirPatient.gender = muzimaPatient.gender
            fhirPatient.birthDate = muzimaPatient.birthdate

            // Name
            var fhirName = HumanName()
            fhirName.family = muzimaPatient.names[0].familyName
            if(muzimaPatient.names[0].isPreferred) fhirName.use = "usual"
            var given = muzimaPatient.names[0].givenName.split(" ")
            given.forEach{givenName ->
                fhirName.given.add(givenName)
            }

            fhirPatient.active = !muzimaPatient.isVoided

            var fhirTelecom = ContactPoint()
            var muzimaAttribute = muzimaPatient.atributes.find { attribute -> attribute.attributeType.name == "phone" }
            fhirTelecom.value = muzimaAttribute?.attribute

            fhirPatient.id = muzimaPatient.uuid

            return fhirPatient
        }
    }
}