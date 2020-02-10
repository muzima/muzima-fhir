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

public class Observation {

    private Person person;

    private Encounter encounter;

    private Concept concept;

    private Concept valueCoded;

    private Date valueDatetime;

    private Double valueNumeric;

    private String valueText;

    private Date observationDatetime;

    private boolean voided;

    public Person getPerson() {
        return person;
    }

    public void setPerson(final Person person) {
        this.person = person;
    }

    public Encounter getEncounter() {
        return encounter;
    }

    public void setEncounter(final Encounter encounter) {
        this.encounter = encounter;
    }

    public Concept getConcept() {
        return concept;
    }

    public void setConcept(final Concept concept) {
        this.concept = concept;
    }

    public Concept getValueCoded() {
        return valueCoded;
    }

    public void setValueCoded(final Concept valueCoded) {
        this.valueCoded = valueCoded;
    }

    public Date getValueDatetime() {
        return valueDatetime;
    }

    public void setValueDatetime(final Date valueDatetime) {
        this.valueDatetime = valueDatetime;
    }

    public Double getValueNumeric() {
        return valueNumeric;
    }

    public void setValueNumeric(final Double valueNumeric) {
        this.valueNumeric = valueNumeric;
    }

    public String getValueText() {
        return valueText;
    }

    public void setValueText(final String valueText) {
        this.valueText = valueText;
    }

    /**
     * Get the date and time of the observation.
     *
     * @return the date and time of the observation.
     */
    public Date getObservationDatetime() {
        return observationDatetime;
    }

    /**
     * Set the date and time of the observation.
     *
     * @param observationDatetime the date and time of the observation.
     */
    public void setObservationDatetime(final Date observationDatetime) {
        this.observationDatetime = observationDatetime;
    }


    private String getStringOfNumeric() {
        if (getConcept().isPrecise())
            return valueNumeric.toString();
        else
            return String.valueOf(valueNumeric.intValue());
    }

    public boolean isVoided() {
        return voided;
    }

    public void setVoided(final boolean voided) {
        this.voided = voided;
    }
}
