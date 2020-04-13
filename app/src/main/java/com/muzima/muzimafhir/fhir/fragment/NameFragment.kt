package com.muzima.muzimafhir.fhir.fragment

import com.muzima.muzimafhir.data.fhir.types.Address
import com.muzima.muzimafhir.data.fhir.types.HumanName

class NameFragment {
    companion object {

        /***
         * Returns a FHIR Name object from the argument query fragment.
         * @param a FHIR Name fragment
         * @return a FHIR Name object
         */
        fun getName(name: graphqlcontent.fragment.Name) : HumanName {
            var newName = HumanName()
            if(name.family() != null){
                newName.family = name.family()
            }
            if(!name.given().isNullOrEmpty()){
                newName.given = name.given()?.toMutableList() ?: mutableListOf()
            }
            if(name.use() != null){
                newName.use = name.use().toString()
            }
            if(!name.prefix().isNullOrEmpty()){
                newName.prefix = name.prefix()?.toMutableList() ?: mutableListOf()
            }
            if(!name.suffix().isNullOrEmpty()){
                newName.suffix = name.suffix()?.toMutableList() ?: mutableListOf()
            }
            return newName
        }

    }
}