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
import java.util.Iterator;
import java.util.List;

public class Patient extends Person {

    private String uuid;
    private List<PatientIdentifier> identifiers;
    private Tag[] tags;
    private String deletionStatus;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void addIdentifier(final PatientIdentifier identifier) {
        getIdentifiers().add(identifier);
    }

    public List<PatientIdentifier> getIdentifiers() {
        if (identifiers == null) {
            identifiers = new ArrayList<PatientIdentifier>();
        }
        return identifiers;
    }

    public void setIdentifiers(final List<PatientIdentifier> identifiers) {
        this.identifiers = identifiers;
    }

    /**
     * Gets the PatientIdentifier with the given identifier type name.
     *
     * @param identifierTypeName the name of the identifier type
     * @return the PatientIdentifier with the given identifier type name
     */
    public PatientIdentifier getIdentifier(String identifierTypeName) {
        for (PatientIdentifier identifier : getIdentifiers()) {
            if(identifier.getIdentifierType().getName() != null) {
                if (identifier.getIdentifierType().getName().equals(identifierTypeName)) {
                    return identifier;
                }
            }
        }
        return null;
    }

    /**
     * Removes the PatientIdentifier with the given identifier type name.
     *
     * @param identifierTypeName the name of the identifier type
     */
    public void removeIdentifier(String identifierTypeName) {
        Iterator<PatientIdentifier> iterator = getIdentifiers().iterator();
        while (iterator.hasNext()) {
            PatientIdentifier next = iterator.next();
            if (identifierTypeName.equals(next.getIdentifierType().getName())) {
                iterator.remove();
            }
        }
    }


    public Tag[] getTags() {
        return tags;
    }

    public void setTags(Tag[] tags) {
        this.tags = tags;
    }

    public String getDeletionStatus() {
        return deletionStatus;
    }

    public void setDeletionStatus(String deletionStatus) {
        this.deletionStatus = deletionStatus;
    }
}
