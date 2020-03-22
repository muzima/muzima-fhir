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


class MuzimaTranslationTests {


    @Test
    fun validFhirPersonObjectMapsToValidMuzimaObject(){
        var muzimaPerson = MuzimaPerson()
        var fhirPerson = FhirPerson()

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
    }

}