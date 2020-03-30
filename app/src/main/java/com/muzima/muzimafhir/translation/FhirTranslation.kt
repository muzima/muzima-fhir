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


        private fun fhirLocationToMuzimaLocation(l: fhirLocation) : muzimaLocation {
            var a = muzimaLocation()
            a.name = l.name


            return a
        }


        private fun fhirPatientToMuzimaPatient(p: fhirPatient) : muzimaPatient {
            var mPatient = muzimaPatient()
            // FHIR -> OpenMRS
            /*List: Patient.address -> List: Patient.address
                address.use == preferred -> address.isPreferred
                address.country -> address.country
                address.postalCode -> address.postalCode
                address.line[0] -> address.address1
                address.city -> address.cityVillage
            */
            var addresses = mutableListOf<PersonAddress>()
            p.address?.forEach {fAddress ->
                var mAddress = PersonAddress()
                mAddress.isPreferred = fAddress.use == "usual"
                mAddress.country = fAddress.country
                mAddress.postalCode = fAddress.postalCode
                mAddress.address1 = fAddress.line?.get(0)
                mAddress.cityVillage = fAddress.city
                /*
                mAddress.address2 = fAddress.line?.get(1)
                mAddress.address3 = fAddress.line?.get(2)
                mAddress.address4 = fAddress.line?.get(3)
                mAddress.address5 = fAddress.line?.get(4)
                mAddress.address6 = fAddress.line?.get(5)
                */
                mAddress.stateProvince = fAddress.state
                addresses.add(mAddress)
            }
            mPatient.addresses = addresses
            //Address END
            // patient.gender -> patient.gender
            mPatient.gender = p.gender
            // patient.birthdate -> patient.birthdate
            mPatient.birthdate = p.birthDate
            /* List: Patient.names -> Patient.names
                name.family -> name.familyName
                name.use == preferred -> name.isPreferred
                (List) name.given (concatenated) -> name.givenName
             */
            var names = mutableListOf<PersonName>()
            p.name?.forEach { fName ->
                var mName = PersonName()
                mName.familyName = fName.family
                mName.isPreferred = fName.use == "usual"
                mName.givenName = fName.given.joinToString(" ")
                names.add(mName)
            }
            mPatient.names = names
            //Names END
            /*  List: Patient.identifier -> Patient.identifiers
                  identifier.value -> PatientIdentifer.identifier
             */
            var patientIdentifiers = mutableListOf<PatientIdentifier>()
            p.identifier?.forEach { identifier ->
                var patientIdentifier = PatientIdentifier()
                patientIdentifier.identifier = identifier.value
                patientIdentifiers.add(patientIdentifier)
            }
            mPatient.identifiers = patientIdentifiers
            // not patient.active -> patient.isVoided
            mPatient.isVoided = !p.active!!

            //Missing mappings: uuid, deathDate/dead,
            return mPatient
        }

        /*
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