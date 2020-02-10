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

public class PatientIdentifier {

    private boolean preferred;

    private String identifier;

    private PatientIdentifierType identifierType;

    private Location location;

    public boolean isPreferred() {
        return preferred;
    }

    public void setPreferred(final boolean preferred) {
        this.preferred = preferred;
    }

    /**
     * Get the patient identifier
     *
     * @return the patient identifier
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Set the patient identifier
     *
     * @param identifier the patient identifier
     */
    public void setIdentifier(final String identifier) {
        this.identifier = identifier;
    }

    public PatientIdentifierType getIdentifierType() {
        return identifierType;
    }

    public void setIdentifierType(final PatientIdentifierType identifierType) {
        this.identifierType = identifierType;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
