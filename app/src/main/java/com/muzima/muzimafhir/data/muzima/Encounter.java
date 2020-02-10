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

import java.util.Date;

/**
 * TODO: Write brief description about the class here.
 */
public class Encounter  {

    private Patient patient;

    private Person provider;

    private Location location;

    private EncounterType encounterType;

    private Date encounterDatetime;

    private String formDataUuid;

    private boolean voided;

    private int id;

    private String userSystemId;

    public String getFormDataUuid() {
        return formDataUuid;
    }

    public void setFormDataUuid(String formDataUuid) {
        this.formDataUuid = formDataUuid;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(final Patient patient) {
        this.patient = patient;
    }

    public Person getProvider() {
        return provider;
    }

    public void setProvider(final Person provider) {
        this.provider = provider;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(final Location location) {
        this.location = location;
    }

    public EncounterType getEncounterType() {
        return encounterType;
    }

    public void setEncounterType(final EncounterType encounterType) {
        this.encounterType = encounterType;
    }

    public Date getEncounterDatetime() {
        return encounterDatetime;
    }

    public void setEncounterDatetime(final Date encounterDatetime) {
        this.encounterDatetime = encounterDatetime;
    }

    public boolean isVoided() {
        return voided;
    }

    public void setVoided(final boolean voided) {
        this.voided = voided;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getUserSystemId() {
        return userSystemId;
    }

    public void setUserSystemId(String userSystemId) {
        this.userSystemId = userSystemId;
    }
}
