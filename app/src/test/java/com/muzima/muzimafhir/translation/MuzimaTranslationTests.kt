package com.muzima.muzimafhir.translation

import com.muzima.muzimafhir.data.fhir.types.Address
import com.muzima.muzimafhir.data.fhir.types.ContactPoint
import com.muzima.muzimafhir.data.fhir.types.HumanName
import com.muzima.muzimafhir.data.muzima.PersonAddress
import com.muzima.muzimafhir.data.muzima.PersonAttribute
import com.muzima.muzimafhir.data.muzima.PersonAttributeType
import com.muzima.muzimafhir.data.muzima.PersonName
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*
import com.muzima.muzimafhir.data.muzima.Person as MuzimaPerson
import com.muzima.muzimafhir.data.fhir.Person as FhirPerson
import com.muzima.muzimafhir.data.muzima.Patient as MuzimaPatient
import com.muzima.muzimafhir.data.fhir.Patient as FhirPatient


class MuzimaTranslationTests {


    @Test
    fun validFhirPersonObjectMapsToValidMuzimaObject(){
        var muzimaPerson = MuzimaPerson()
        var fhirPerson = FhirPerson("adf32428943893874RT")

        var personName = PersonName()
        personName.givenName = "Salina"
        personName.middleName = "Galma"
        personName.familyName = "Alfani"
        personName.isPreferred = true
        muzimaPerson.names.add(personName)

        var personAddress = PersonAddress()
        personAddress.address1 = "test"
        personAddress.country = "Mozambique"
        personAddress.cityVillage = "Tuiyo"
        personAddress.latitude = "-25.961127"
        personAddress.stateProvince = "test"
        muzimaPerson.addresses = mutableListOf(personAddress)
        muzimaPerson.gender = "female"

        muzimaPerson.isVoided = false

        var dateString = "20/12/1998"
        val format = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
        val date = format.parse(dateString)
        muzimaPerson.birthdate = date
        muzimaPerson.birthdateEstimated = false

        var phone = PersonAttribute()
        var phoneType = PersonAttributeType()
        phoneType.name = "phone"
        phone.attributeType = phoneType
        phone.attribute = "99999999"

        muzimaPerson.atributes.add(phone)

        var fhirPersonAddress = Address()
        fhirPersonAddress.line = mutableListOf("test")
        fhirPersonAddress.city = "Tuiyo"
        fhirPersonAddress.country = "Mozambique"
        fhirPersonAddress.state = "test"

        fhirPerson.active = true
        fhirPerson.address = mutableListOf(fhirPersonAddress)
        fhirPerson.gender = "female"

        var fhirPersonContactPoint = ContactPoint()
        fhirPersonContactPoint.value = "99999999"
        fhirPerson.telecom = mutableListOf(fhirPersonContactPoint)

        fhirPerson.birthDate = date

        var fhirPersonName = HumanName()
        fhirPersonName.given = mutableListOf("Salina", "Galma")
        fhirPersonName.family = "Alfani"
        fhirPersonName.use = "usual"

        fhirPerson.name = mutableListOf(fhirPersonName)


        var convertedPerson = FhirTranslation.toMuzimaObject(fhirPerson)

        assertTrue(convertedPerson is MuzimaPerson)
        var convertedMuzimaPerson = convertedPerson as MuzimaPerson

        assertEquals(convertedMuzimaPerson.gender, fhirPerson.gender) // Test gender -> gender
        assertEquals(convertedMuzimaPerson.birthdate, fhirPerson.birthDate) // Test birthDate -> birthdate
        assertEquals(!convertedMuzimaPerson.isVoided, fhirPerson.active!!) //Test isActive -> voided
        assertTrue(fhirPerson.address!!.isNotEmpty()) // Control check source object
        assertTrue(convertedMuzimaPerson.addresses.isNotEmpty()) // Address has data
        assertEquals(convertedMuzimaPerson.addresses[0].stateProvince, fhirPerson.address!![0].state) // Test state -> stateProvince
        assertEquals(convertedMuzimaPerson.addresses[0].country, fhirPerson.address!![0].country) // Test country -> country
        assertEquals(convertedMuzimaPerson.addresses[0].cityVillage, fhirPerson.address!![0].city) // Test city -> cityVillage
        assertEquals(convertedMuzimaPerson.addresses[0].address1, fhirPerson.address!![0].line!![0]) // Test city -> cityVillage
        assertEquals(convertedMuzimaPerson.atributes[0].attribute, fhirPerson.telecom!![0].value) // Test telecom/value -> attribute/phone
        assertEquals(convertedMuzimaPerson.names[0].givenName, fhirPerson.name!![0].given.joinToString(" ")) // Test given -> givenName
        assertEquals(convertedMuzimaPerson.names[0].familyName, fhirPerson.name!![0].family)
        assertEquals(convertedMuzimaPerson.uuid, fhirPerson.id)
    }

    @Test
    fun validFhirPatientObjectMapsToValidMuzimaObject(){
        var muzimaPatient = MuzimaPatient()
        var fhirPatient = FhirPatient("adf32428943893874RT")

        muzimaPatient.uuid = fhirPatient.id

        var personName = PersonName()
        personName.givenName = "Salina"
        personName.middleName = "Galma"
        personName.familyName = "Alfani"
        personName.isPreferred = true
        muzimaPatient.names.add(personName)

        var personAddress = PersonAddress()
        personAddress.address1 = "test"
        personAddress.country = "Mozambique"
        personAddress.cityVillage = "Tuiyo"
        personAddress.latitude = "-25.961127"
        personAddress.stateProvince = "test"
        muzimaPatient.addresses = mutableListOf(personAddress)

        muzimaPatient.gender = "female"

        muzimaPatient.isVoided = false

        var dateString = "20/12/1998"
        val format = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
        val date = format.parse(dateString)
        muzimaPatient.birthdate = date
        muzimaPatient.birthdateEstimated = false

        var phone = PersonAttribute()
        var phoneType = PersonAttributeType()
        phoneType.name = "phone"
        phone.attributeType = phoneType
        phone.attribute = "99999999"

        muzimaPatient.atributes.add(phone)

        var fhirPersonAddress = Address()
        fhirPersonAddress.line = mutableListOf("test")
        fhirPersonAddress.city = "Tuiyo"
        fhirPersonAddress.country = "Mozambique"
        fhirPersonAddress.state = "test"

        fhirPatient.active = true
        fhirPatient.address = mutableListOf(fhirPersonAddress)
        fhirPatient.gender = "female"

        var fhirPersonContactPoint = ContactPoint()
        fhirPersonContactPoint.value = "99999999"
        fhirPatient.telecom = mutableListOf(fhirPersonContactPoint)

        fhirPatient.birthDate = date

        var fhirPersonName = HumanName()
        fhirPersonName.given = mutableListOf("Salina", "Galma")
        fhirPersonName.family = "Alfani"
        fhirPersonName.use = "usual"

        fhirPatient.name = mutableListOf(fhirPersonName)


        var convertedPatient = FhirTranslation.toMuzimaObject(fhirPatient)

        assertTrue(convertedPatient is MuzimaPatient)
        var convertedMuzimaPatient = convertedPatient as MuzimaPatient

        assertEquals(convertedMuzimaPatient.gender, fhirPatient.gender) // Test gender -> gender
        assertEquals(convertedMuzimaPatient.birthdate, fhirPatient.birthDate) // Test birthDate -> birthdate
        assertEquals(!convertedMuzimaPatient.isVoided, fhirPatient.active!!) //Test isActive -> voided
        assertTrue(fhirPatient.address!!.isNotEmpty()) // Control check source object
        assertTrue(convertedMuzimaPatient.addresses.isNotEmpty()) // Address has data
        assertEquals(convertedMuzimaPatient.addresses[0].stateProvince, fhirPatient.address!![0].state) // Test state -> stateProvince
        assertEquals(convertedMuzimaPatient.addresses[0].country, fhirPatient.address!![0].country) // Test country -> country
        assertEquals(convertedMuzimaPatient.addresses[0].cityVillage, fhirPatient.address!![0].city) // Test city -> cityVillage
        assertEquals(convertedMuzimaPatient.addresses[0].address1, fhirPatient.address!![0].line!![0]) // Test city -> cityVillage
        assertEquals(convertedMuzimaPatient.atributes[0].attribute, fhirPatient.telecom!![0].value) // Test telecom/value -> attribute/phone
        assertEquals(convertedMuzimaPatient.names[0].givenName, fhirPatient.name!![0].given.joinToString(" ")) // Test given -> givenName
        assertEquals(convertedMuzimaPatient.names[0].familyName, fhirPatient.name!![0].family) // Test family -> familyName
        assertEquals(convertedMuzimaPatient.uuid, fhirPatient.id) // Test id -> uuid
    }

}