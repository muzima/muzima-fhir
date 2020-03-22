package com.muzima.muzimafhir.translation

import com.muzima.muzimafhir.data.muzima.*
import com.muzima.muzimafhir.data.fhir.Person as fhirPerson
import com.muzima.muzimafhir.data.muzima.Person as muzimaPerson
import com.muzima.muzimafhir.data.fhir.Location as fhirLocation
import com.muzima.muzimafhir.data.muzima.Location as muzimaLocation
import com.muzima.muzimafhir.data.fhir.Encounter as fhirEncounter
import com.muzima.muzimafhir.data.muzima.Encounter as muzimaEncounter
import com.muzima.muzimafhir.data.fhir.Observation as fhirObservation
import com.muzima.muzimafhir.data.muzima.Observation as muzimaObservation
import com.muzima.muzimafhir.data.fhir.Patient as fhirPatient
import com.muzima.muzimafhir.data.muzima.Patient as muzimaPatient


class FhirTranslation {
    companion object {
        fun toMuzimaObject(fhirObject: Any) : Any {
            if(fhirObject is fhirPerson){
                return fhirPersonToMuzimaPerson(fhirObject)
            }
            if(fhirObject is fhirLocation){
                //return fhirLocationToMuzimaLocation(fhirObject)
            }
            throw IllegalArgumentException("Valid argument required")
        }

        /*
        private fun fhirLocationToMuzimaLocation(l: fhirLocation) : muzimaLocation {
            var a = muzimaLocation()
            a.name = l.name


            return a
        }

        private fun fhirPatientToMuzimaPatient(p: fhirPatient) : muzimaPatient {

        }

        private fun fhirEncounterToMuzimaEncounter(e: fhirEncounter) : muzimaEncounter {
            val a = muzimaEncounter()

        }

        private fun fhirObservationToMuzimaObservation(o: fhirObservation) : muzimaObservation {
            val a = muzimaObservation()

        }

        */

        private fun fhirPersonToMuzimaPerson(p: fhirPerson) : muzimaPerson {
            var a = muzimaPerson()
            //Names
            var names = mutableListOf<PersonName>()
            p.name?.forEach { fName ->
                var mName = PersonName()
                mName.familyName = fName.family
                mName.isPreferred = fName.use == "usual"
                mName.givenName = fName.given.joinToString(" ")
                mName.middleName = fName.family
                names.add(mName)
            }
            a.names = names

            //Addresses
            var addresses = mutableListOf<PersonAddress>()
            p.address?.forEach {fAddress ->
                var mAddress = PersonAddress()
                mAddress.isPreferred = fAddress.use == "usual"
                mAddress.country = fAddress.country
                mAddress.postalCode = fAddress.postalCode
                mAddress.address1 = fAddress.line?.get(0)
                mAddress.cityVillage = fAddress.city
                /*mAddress.address2 = fAddress.line?.get(1)
                mAddress.address3 = fAddress.line?.get(2)
                mAddress.address4 = fAddress.line?.get(3)
                mAddress.address5 = fAddress.line?.get(4)
                mAddress.address6 = fAddress.line?.get(5)*/
                mAddress.stateProvince = fAddress.state
                addresses.add(mAddress)
            }
            a.addresses = addresses
            a.birthdate = p.birthDate
            a.gender = p.gender
            a.isVoided = !p.active!!

            var personAttribute = PersonAttribute()
            var personAttributeType = PersonAttributeType()
            personAttributeType.name = "phone"
            personAttribute.attributeType = personAttributeType
            personAttribute.attribute = p.telecom?.get(0)?.value
            a.atributes.add(personAttribute)

            return a
        }
    }

}