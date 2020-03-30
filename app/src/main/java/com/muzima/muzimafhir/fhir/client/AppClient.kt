package com.muzima.muzimafhir.fhir.client

import typeFixFolder.*
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.google.gson.Gson
import com.muzima.muzimafhir.data.fhir.Encounter
import com.muzima.muzimafhir.data.fhir.Observation
import com.muzima.muzimafhir.data.fhir.Patient
import com.muzima.muzimafhir.data.fhir.Location
import com.muzima.muzimafhir.data.fhir.Person
import com.muzima.muzimafhir.data.fhir.types.*
import okhttp3.OkHttpClient
import typeFixFolder.type.Observation_Input
import typeFixFolder.type.Patient_Input
import typeFixFolder.type.Person_Input
import typeFixFolder.type.Encounter_Input
import java.time.Instant
import java.util.*

class AppClient {
    var gson: Gson = Gson()
    //private var BASE_URL = "http://10.0.2.2:3000/4_0_0/\$graphql"
    // Android VM's host address, use either a remote server IP
    // or local IPv4 address for integration testing.
    private var BASE_URL = "http://45.79.198.132:3000/4_0_0/\$graphql" //GraphQL endpoint
    private var apolloClient: ApolloClient

    init {
        apolloClient = getClient()
    }

    /***
     * Builds the Apollo client and assigns it locally
     */
    private fun getClient(): ApolloClient {
        println("Building Apollo client...")
        var okHttpClient = OkHttpClient.Builder().build()
        apolloClient = ApolloClient
                .builder()
                .serverUrl(BASE_URL)
                .okHttpClient(okHttpClient)
                .build()
        println("Apollo client built!")
        return apolloClient
    }

    /***
     * Calls the query GetPersonByIdQuery with the Apollo client.
     * Initialized Apollo client required.
     */
    fun getPerson(id: String, onSuccess: (String, Person) -> Unit) {
        println("Calling getPerson for Person with id $id...")
        println("Building query...")
        // Build the query to be executed from the class generated by Apollo
        var q = GetPersonByIdQuery
                .builder()
                .id(id)
                .build()
        println("Query built!")
        // Define a callback for the client to execute on completion
        // Both onResponse and onFailure are required
        val callBack = object : ApolloCall.Callback<GetPersonByIdQuery.Data>() {
            override fun onResponse(response: Response<GetPersonByIdQuery.Data>) {
                println("Callback onResponse called!")
                var dataPerson = response.data()?.Person()
                //var ret = "The callback returned successfully!"
            }
            override fun onFailure(e: ApolloException) {
                println("Callback onFailure called!")
                println("exception was: ${e.message}")
                println("exception was: ${e.stackTrace}")
                throw e
            }
        }
        // Execute the query with the specified callback
        apolloClient.query(q).enqueue(callBack)
    }

    /***
     * Calls the query GetPersonByIdQuery with the Apollo client.
     * Initialized Apollo client required.
     */
    fun getPersonList(id: String, onSuccess: (String, Person) -> Unit) {
        println("Calling getPerson for Person with id $id...")
        println("Building query...")
        // Build the query to be executed from the class generated by Apollo
        var q = GetPersonListQuery
                .builder()
                .build()
        println("Query built!")
        // Define a callback for the client to execute on completion
        // Both onResponse and onFailure are required
        val callBack = object : ApolloCall.Callback<GetPersonListQuery.Data>() {
            override fun onResponse(response: Response<GetPersonListQuery.Data>) {
                println("Callback onResponse called!")
                var dataPersonList = response.data()?.PersonList()?.entry()
                //var ret = "The callback returned successfully!"
                var l = mutableListOf<Person>()
                dataPersonList?.forEach {

                }
                //println("person as string: " + parsePerson(dataPerson).toString())
                //var ret = dataPerson?.name().toString()
                //onSuccess(ret, parsePerson(dataPerson))
            }
            override fun onFailure(e: ApolloException) {
                println("Callback onFailure called!")
                println("exception was: ${e.message}")
                println("exception was: ${e.stackTrace}")
                throw e
            }
        }
        // Execute the query with the specified callback
        apolloClient.query(q).enqueue(callBack)
    }

    /**
     * PersonCreateMutation
     * Create Person and send resource to server
     */
    fun createPerson(person: Person_Input, onSuccess: (String, Person) -> Unit) {
        println("Building query...")
        // Build the query to be executed from the class generated by Apollo
        var m = PersonCreateMutation
                .builder()
                .person(person)
                .build()
        println("Query built!")
        // Define a callback for the client to execute on completion
        // Both onResponse and onFailure are required
        val callBack = object : ApolloCall.Callback<PersonCreateMutation.Data>() {
            override fun onResponse(response: Response<PersonCreateMutation.Data>) {
                println("Callback onResponse called!")
                var dataPerson = response.data()?.PersonCreate()
                //var ret = "The callback returned successfully!"
                println("PersonCreateMutationResponse: " + dataPerson.toString())
                //println("person as string: " + parsePerson(dataPerson).toString())
                // var ret = dataPerson?.name().toString()
                //onSuccess(ret, parsePerson(dataPerson))
            }

            override fun onFailure(e: ApolloException) {
                println("Callback onFailure called!")
                println("exception was: ${e.message}")
                println("exception was: ${e.stackTrace}")
                throw e
            }
        }
        // Execute the query with the specified callback
        apolloClient.mutate(m).enqueue(callBack)
    }


    /***
     * Gets a Patient from the server set at the beginning of this doc
     * with the ID specified in the PatientActivity class
     * Initialized Apollo client required.
     */
    fun getPatient(id: String, onSuccess: (String, Patient) -> Unit) {
        println("Calling getPatient for Patient with id $id...")
        println("Building query...")
        // Build the query to be executed from the class generated by Apollo
        var q = GetPatientByIdQuery
                .builder()
                .id(id)
                .build()
        println("Query built!")
        // Define a callback for the client to execute on completion
        // Both onResponse and onFailure are required
        val callBack = object : ApolloCall.Callback<GetPatientByIdQuery.Data>() {
            override fun onResponse(response: Response<GetPatientByIdQuery.Data>) {
                println("Callback onResponse called!")
                var dataPatient = response.data()?.Patient()
                //var ret = "The callback returned successfully!"
                println("Patient as string: " + parsePatient(dataPatient).toString())
                var ret = dataPatient?.name().toString()
                onSuccess(ret, parsePatient(dataPatient))
            }

            override fun onFailure(e: ApolloException) {
                println("Callback onFailure called!")
                println("exception was: ${e.message}")
                println("exception was: ${e.stackTrace}")
                throw e
            }
        }
        // Execute the query with the specified callback
        apolloClient.query(q).enqueue(callBack)
    }
    /***
     * Gets a Encounter from the server set at the beginning of this doc
     * with the ID specified in the EncounterActivity class
     * Initialized Apollo client required.
     */
    fun getEncounter(id: String, onSuccess: (String, Encounter) -> Unit) {
        println("Calling getEncounter for Encounter with id $id...")
        println("Building query...")
        // Build the query to be executed from the class generated by Apollo
        var q = typeFixFolder.GetEncounterByIdQuery
                .builder()
                .id(id)
                .build()
        println("Query built!")
        // Define a callback for the client to execute on completion
        // Both onResponse and onFailure are required
        val callBack = object : ApolloCall.Callback<GetEncounterByIdQuery.Data>() {
            override fun onResponse(response: Response<GetEncounterByIdQuery.Data>) {
                println("Callback onResponse called!")
                var dataEncounter = response.data()?.Encounter()
                //var ret = "The callback returned successfully!"
                println("Encounter as string: " + parseEncounter(dataEncounter).toString())
                var ret = dataEncounter?.id().toString()
                onSuccess(ret, parseEncounter(dataEncounter))
            }

            override fun onFailure(e: ApolloException) {
                println("Callback onFailure called!")
                println("exception was: ${e.message}")
                println("exception was: ${e.stackTrace}")
                throw e
            }
        }
        // Execute the query with the specified callback
        apolloClient.query(q).enqueue(callBack)
    }

    /**
     * EncounterCreateMutation
     * Create Encounter and send resource to server
     */
    fun createEncounter(encounter: Encounter_Input, onSuccess: (String, Encounter) -> Unit) {
        println("Building query...")
        // Build the query to be executed from the class generated by Apollo
        var m = EncounterCreateMutation
                .builder()
                .encounter(encounter)
                .build()
        println("Query built!")
        // Define a callback for the client to execute on completion
        // Both onResponse and onFailure are required
        val callBack = object : ApolloCall.Callback<EncounterCreateMutation.Data>() {
            override fun onResponse(response: Response<EncounterCreateMutation.Data>) {
                println("Callback onResponse called!")
                var dataEncounter = response.data()?.EncounterCreate()
                //var ret = "The callback returned successfully!"
                println("EncounterCreateMutationResponse: " + dataEncounter.toString())
                //println("Encounter as string: " + parseEncounter(dataEncounter).toString())
                // var ret = dataEncounter?.name().toString()
                //onSuccess(ret, parseEncounter(dataEncounter))
            }

            override fun onFailure(e: ApolloException) {
                println("Callback onFailure called!")
                println("exception was: ${e.message}")
                println("exception was: ${e.stackTrace}")
                throw e
            }
        }
        // Execute the query with the specified callback
        apolloClient.mutate(m).enqueue(callBack)
    }

    /**
     * PersonCreateMutation
     * Create Person and send resource to server
     */
    fun createPatient(patient: Patient_Input, onSuccess: (String, Patient) -> Unit ){
        println("Building query...")
        // Build the query to be executed from the class generated by Apollo
        var m = PatientCreateMutation
                .builder()
                .patient(patient)
                .build()
        println("Query built!")
        // Define a callback for the client to execute on completion
        // Both onResponse and onFailure are required
        val callBack = object : ApolloCall.Callback<PatientCreateMutation.Data>(){
            override fun onResponse(response: Response<PatientCreateMutation.Data>) {
                println("Callback onResponse called!")
                var dataPatient = response.data()?.PatientCreate()
                //var ret = "The callback returned successfully!"
                println("PatientCreateMutationResponse: " + dataPatient.toString())
                //println("Patient as string: " + parsePatient(dataPatient).toString())
                // var ret = dataPatient?.name().toString()
                //onSuccess(ret, parsePatient(dataPatient))
            }
            override fun onFailure(e: ApolloException) {
                println("Callback onFailure called!")
                println("exception was: ${e.message}")
                println("exception was: ${e.stackTrace}")
                throw e
            }
        }
        // Execute the query with the specified callback
        apolloClient.mutate(m).enqueue(callBack)
    }

    /**
     * Get Observation
     */
    fun getObservation(id: String, onSuccess: (String, Observation) -> Unit) {
        println("Calling getObservation for Observation with id $id...")
        println("Building query...")
        // Build the query to be executed from the class generated by Apollo
        var q = GetObservationByIdQuery
                .builder()
                .id(id)
                .build()
        println("Query built!")
        // Define a callback for the client to execute on completion
        // Both onResponse and onFailure are required
        val callBack = object : ApolloCall.Callback<GetObservationByIdQuery.Data>() {
            override fun onResponse(response: Response<GetObservationByIdQuery.Data>) {
                println("Callback onResponse called!")
                var dataObservation = response.data()?.Observation()
                //var ret = "The callback returned successfully!"
                println("Observation as string: " + parseObservation(dataObservation).toString())
                var ret = dataObservation?.id().toString()
                onSuccess(ret, parseObservation(dataObservation))
            }
            override fun onFailure(e: ApolloException) {
                println("Callback onFailure called!")
                println("exception was: ${e.message}")
                println("exception was: ${e.stackTrace}")
                throw e
            }
        }
        // Execute the query with the specified callback
        apolloClient.query(q).enqueue(callBack)
    }

    /**
     * ObservationCreateMutation
     * Create Observation and send resource to server
     */
    fun createObservation(observation: Observation_Input, onSuccess: (String, Observation) -> Unit ){
        println("Building query...")
        // Build the query to be executed from the class generated by Apollo
        var m = ObservationCreateMutation
                .builder()
                .observation(observation)
                .build()
        println("Query built!")
        // Define a callback for the client to execute on completion
        // Both onResponse and onFailure are required
        val callBack = object : ApolloCall.Callback<ObservationCreateMutation.Data>(){
            override fun onResponse(response: Response<ObservationCreateMutation.Data>) {
                println("Callback onResponse called!")
                var dataObservation = response.data()?.ObservationCreate()
                //var ret = "The callback returned successfully!"
                println("ObservationCreateMutationResponse: " + dataObservation.toString())
                //println("Observation as string: " + parseObservation(dataObservation).toString())
                // var ret = dataObservation?.name().toString()
                //onSuccess(ret, parsePatient(dataObservation))
            }
            override fun onFailure(e: ApolloException) {
                println("Callback onFailure called!")
                println("exception was: ${e.message}")
                println("exception was: ${e.stackTrace}")
                throw e
            }
        }
        // Execute the query with the specified callback
        apolloClient.mutate(m).enqueue(callBack)
    }

    /***
     * Calls the query GetLocationByIdQuery with the Apollo client.
     * Initialized Apollo client required.
     */
    fun getLocation(id: String, onSuccess: (String, Location) -> Unit) {
        println("Calling getLocation for Location with id $id...")
        println("Building query...")
        // Build the query to be executed from the class generated by Apollo
        var q = GetLocationByIdQuery
                .builder()
                .id(id)
                .build()
        println("Query built!")
        // Define a callback for the client to execute on completion
        // Both onResponse and onFailure are required
        val callBack = object : ApolloCall.Callback<GetLocationByIdQuery.Data>() {
            override fun onResponse(response: Response<GetLocationByIdQuery.Data>) {
                println("Callback onResponse called!")
                var dataLocation = response.data()?.Location()
                //var ret = "The callback returned successfully!"
                println("Location as string: " + parseLocation(dataLocation).toString())
                var ret = dataLocation?.name().toString()
                onSuccess(ret, parseLocation(dataLocation))
            }
            override fun onFailure(e: ApolloException) {
                println("Callback onFailure called!")
                println("exception was: ${e.message}")
                println("exception was: ${e.stackTrace}")
                throw e
            }
        }
        // Execute the query with the specified callback
        apolloClient.query(q).enqueue(callBack)
    }

    /***
     * Calls the query GetLocationListQuery with the Apollo client.
     * Initialized Apollo client required.
     */
    fun getLocationList(id: String, onSuccess: (String, Location) -> Unit) {
        println("Calling getLocation for Location with id $id...")
        println("Building query...")
        // Build the query to be executed from the class generated by Apollo
        var q = GetLocationListQuery
                .builder()
                .build()
        println("Query built!")
        // Define a callback for the client to execute on completion
        // Both onResponse and onFailure are required
        val callBack = object : ApolloCall.Callback<GetLocationListQuery.Data>() {
            override fun onResponse(response: Response<GetLocationListQuery.Data>) {
                println("Callback onResponse called!")
                var dataLocationList = response.data()?.LocationList()?.entry()
                //var ret = "The callback returned successfully!"
                var l = mutableListOf<Location>()
                dataLocationList?.forEach {

                }
                //println("Location as string: " + parseLocation(dataLocation).toString())
                //var ret = dataLocation?.name().toString()
                //onSuccess(ret, parseLocation(dataLocation))
            }
            override fun onFailure(e: ApolloException) {
                println("Callback onFailure called!")
                println("exception was: ${e.message}")
                println("exception was: ${e.stackTrace}")
                throw e
            }
        }
        // Execute the query with the specified callback
        apolloClient.query(q).enqueue(callBack)
    }

    fun parseLocation(location: GetLocationByIdQuery.Location?): Location{

        var telecoms = mutableListOf<ContactPoint>()
        location?.telecom()?.forEach{
            telecoms.add(parseTelecom(it))
        }

        var mLocation = Location()
        mLocation.status = location?.status().toString()
        mLocation.name = location?.name().toString()
        mLocation.description = location?.description().toString()
        mLocation.telecom = telecoms
        mLocation.address = parseAddress(location?.address())
        return mLocation
    }

    fun parseTelecom(telecom: GetLocationByIdQuery.Telecom?) : ContactPoint {
        var c = ContactPoint()
        c.value = telecom?.value()
        return c
    }

    fun parseAddress(address: GetLocationByIdQuery.Address?): Address {
        var mAddress = Address()
        mAddress.line = address?.line()
        return mAddress
    }

    fun parseObservation(observation: GetObservationByIdQuery.Observation?): Observation {

        var mObservation = Observation()
        mObservation.status = observation?.status().toString()
        mObservation.code = parseCode(observation?.code())
        mObservation.valueString = observation?.valueString()
        mObservation.valueInteger = observation?.valueInteger()
        //mObservation.valueDateTime = parseDate(observation?.valueDateTime() as String)
        return mObservation
    }

    fun parseCode(code: GetObservationByIdQuery.Code?) : CodeableConcept {
        var mCodeableConcept = CodeableConcept()
        var codingList = mutableListOf<Coding?>()
        code?.coding()?.forEach {
            codingList.add(parseCoding(it))
        }
        mCodeableConcept.coding = codingList
        mCodeableConcept.text = code?.text()
        return mCodeableConcept
    }

    fun parseCoding(coding: GetObservationByIdQuery.Coding?) : Coding {
        var mCoding = Coding()
        mCoding.code = coding?.code().toString()
        mCoding.display = coding?.display()
        mCoding.system = coding?.system().toString()
        mCoding.version = coding?.version()
        mCoding.userSelected = coding?.userSelected()
        return mCoding
    }

//Patient Parse

    fun parsePatient(patient: GetPatientByIdQuery.Patient?): Patient {
        var humanNames = mutableListOf<HumanName>()
        var addresses = mutableListOf<Address>()
        patient?.name()?.forEach {
            humanNames.add(parseHumanName(it))
        }
        patient?.address()?.forEach {
            addresses.add(parseAddress(it))
        }

        var mPatient = Patient()
        mPatient.name = humanNames
        mPatient.address = addresses
        mPatient.birthDate = null //parseDate(patient?.birthDate() as String)
        mPatient.gender = patient?.gender().toString()
        mPatient.active = patient?.active()

        return mPatient
    }

    fun parseHumanName(name: GetPatientByIdQuery.Name): HumanName {
        var mHumanName = HumanName()
        mHumanName.family = name.family()
        return mHumanName
    }

    fun parseAddress(address: GetPatientByIdQuery.Address): Address {
        var mAddress = Address()
        mAddress.line = address.line()
        return mAddress
    }

    fun parseEncounter(encounter: GetEncounterByIdQuery.Encounter?): Encounter {
        var mEncounter = Encounter()
        mEncounter.status = encounter?.status().toString()
        return mEncounter;
    }














}