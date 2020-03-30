package com.muzima.muzimafhir.fhir.fragment

import com.muzima.muzimafhir.data.fhir.types.Address

class AddressFragment {
    companion object {

        /***
         * Returns a FHIR Address object from the argument query fragment.
         * @param a FHIR Address fragment
         * @return a FHIR Address object
         */
        fun getAddress(address: typeFixFolder.fragment.Address) : Address {
            var newAddress = Address()
            if(address.city() != null){
                newAddress.city = address.city()
            }
            if(address.postalCode() != null){
                newAddress.postalCode = address.postalCode()
            }
            if(address.state() != null){
                newAddress.state = address.state()
            }
            if(!address.line().isNullOrEmpty()){
                address.line()!!.forEach { line ->
                    newAddress.line!!.add(line)
                }
            }
            if(address.city() != null){
                newAddress.city = address.city()
            }
            return newAddress
        }
    }
}