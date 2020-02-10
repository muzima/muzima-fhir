/*
 * Copyright (c) The Trustees of Indiana University, Moi University
 * and Vanderbilt University Medical Center. All Rights Reserved.
 *
 * This version of the code is licensed under the MPL 2.0 Open Source license
 * with additional health care disclaimer.
 * If the user is an entity intending to commercialize any application that uses
 *  this code in a for-profit venture,please contact the copyright holder.
 */

package com.muzima.muzimafhir.data.muzima;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * TODO: Write brief description about the class here.
 */
public class Person {

    public static final String DISPLAY_NAME_FOR_ENCOUNTER_FOR_OBSERVATIONS_WITH_NULL_UUID = "";

    private String gender;

    private Date birthdate;
    private boolean birthdateEstimated;

    private List<PersonName> names;
    private List<PersonAttribute> attributes;
    private List<PersonAddress> addresses;

    private boolean voided;

    /**
     * Get the patient gender
     *
     * @return the patient gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * Set the patient gender
     *
     * @param gender the patient gender
     */
    public void setGender(final String gender) {
        this.gender = gender;
    }

    /**
     * Get the patient birthdate
     *
     * @return the birthdate
     */
    public Date getBirthdate() {
        return birthdate;
    }

    /**
     * Set the patient birthdate
     *
     * @param birthdate the patient birthdate
     */
    public void setBirthdate(final Date birthdate) {
        this.birthdate = birthdate;
    }

    public boolean getBirthdateEstimated() {
        return birthdateEstimated;
    }

    public void setBirthdateEstimated(boolean birthdateEstimated) {
        this.birthdateEstimated = birthdateEstimated;
    }

    public void addName(final PersonName personName) {
        getNames().add(personName);
    }

    public List<PersonName> getNames() {
        if (names == null) {
            names = new ArrayList<PersonName>();
        }
        return names;
    }

    public void setNames(final List<PersonName> names) {
        this.names = names;
    }



    public void addattribute(final PersonAttribute attribute) {
        getAtributes().add(attribute);
    }

    public List<PersonAttribute> getAtributes() {
        if (attributes == null) {
            attributes = new ArrayList<PersonAttribute>();
        }
        return attributes;
    }

    public void setAttributes(final List<PersonAttribute> attributes) {
        this.attributes = attributes;
    }


    /**
     * Gets the PersonAttribute with the given attribute type name.
     *
     * @param attributeName the name of the identifier type
     * @return the PersonAttribute with the given identifier type name
     */
    public PersonAttribute getAttribute(String attributeName) {
        for (PersonAttribute attribute : getAtributes()) {
            if (attribute.getAttributeType().getName().equals(attributeName)) {
                return attribute;
            }
        }
        return null;
    }

    public void setAddresses(List<PersonAddress> addresses){
        this.addresses = addresses;
    }

    public void addAddress(PersonAddress address){
        getAddresses().add(address);
    }

    public List<PersonAddress> getAddresses(){
        if(addresses == null){
            addresses = new ArrayList<PersonAddress>();
        }
        return addresses;
    }


    public PersonAddress getPreferredAddress(){
        for (PersonAddress address: addresses){
            if(address.isPreferred()){
                return address;
            }
        }
        return null;
    }

    public boolean isVoided() {
        return voided;
    }

    public void setVoided(final boolean voided) {
        this.voided = voided;
    }
}
