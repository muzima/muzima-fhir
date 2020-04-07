# muzima-fhir
A mobile application serving as a translation layer between FHIR and OpenMRS for Muzima for Providers

The muzima-fhir project serves as the translation layer between the Muzima for Providers Android application(https://github.com/muzima/muzima-android) and the intromat-fhir project(https://github.com/sureshHARDIYA/intromat-fhir)

The project is powered by Apollo GraphQL for supporting GraphQL functionality.

# Creating queries

GraphQL qureries are defined within the `graphqlcontent` folder. A GraphQL query carries the file extension `.graphql`, and are defined like regular GraphQL queries:

```
query GetPatientById($id:token) {
    Patient(_id:$id){
        id
        identifier {
            value
        }
        gender
        birthDate
        active
        name {
            use
            text
            given
            family
        }
        address {
            line
        }
        deceasedBoolean
        deceasedDateTime
    }
}
```

Queries are executed by an instance of the `ApolloClient:
```
val client = ApplicationGraphQLClient.getClient()
```

Queries are built by passing the query to the client in addition to any additional arguments before executing it:

```
suspend fun queryPatientById(id: String): Patient {
        var q = GetPatientByIdQuery
                .builder()
                .id(id)
                .build()
        return suspendCoroutine { continuation ->
            client.query(q).enqueue(
                    object : ApolloCall.Callback<GetPatientByIdQuery.Data>() {
                        override fun onResponse(response: Response<GetPatientByIdQuery.Data>) {
                            var patient = ... // Get the data from the query and return it
                            continuation.resume(patient)
                        }

                        override fun onFailure(e: ApolloException) {
                            continuation.resumeWithException(e)
                        }
                    }
            )
        }
    }
```
