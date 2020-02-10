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

/**
 * TODO: Write brief description about the class here.
 */
public class PersonName {

    private boolean preferred;

    private String givenName;

    private String middleName;

    private String familyName;

    public boolean isPreferred() {
        return preferred;
    }

    public void setPreferred(final boolean preferred) {
        this.preferred = preferred;
    }

    /**
     * Get the given name for the patient.
     *
     * @return the given name for the patient.
     */
    public String getGivenName() {
        return givenName;
    }

    /**
     * Set the given name for the patient.
     *
     * @param givenName the given name for the patient.
     */
    public void setGivenName(final String givenName) {
        this.givenName = givenName;
    }

    /**
     * Get the middle name for the patient.
     *
     * @return the middle name for the patient.
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * Set the middle name for the patient.
     *
     * @param middleName the middle name for the patient.
     */
    public void setMiddleName(final String middleName) {
        this.middleName = middleName;
    }

    /**
     * Get the family name for the patient.
     *
     * @return the family name for the patient.
     */
    public String getFamilyName() {
        return familyName;
    }

    /**
     * Set the family name for the patient.
     *
     * @param familyName the family name for the patient.
     */
    public void setFamilyName(final String familyName) {
        this.familyName = familyName;
    }
}
