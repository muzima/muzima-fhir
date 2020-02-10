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
import java.util.List;

/**
 * TODO: Write brief description about the class here.
 */
public class Concept {

    public static final String NUMERIC_TYPE = "Numeric";

    public static final String CODED_TYPE = "Coded";

    public static final String DATETIME_TYPE = "Datetime";

    public static final String DATE_TYPE = "Date";

    private String unit;

    private int id;

    private boolean precise;

    private boolean set;

    private ConceptType conceptType;

    private List<ConceptName> conceptNames;

    public String getUnit() {
        return unit;
    }

    public void setUnit(final String unit) {
        this.unit = unit;
    }

    public boolean isPrecise() {
        return precise;
    }

    public void setPrecise(final boolean precise) {
        this.precise = precise;
    }

    public boolean isSet() {
        return set;
    }

    public void setSet(boolean set) {
        this.set = set;
    }

    public ConceptType getConceptType() {
        return conceptType;
    }

    public void setConceptType(final ConceptType conceptType) {
        this.conceptType = conceptType;
    }

    public void addName(final ConceptName conceptName) {
        getConceptNames().add(conceptName);
    }

    public List<ConceptName> getConceptNames() {
        if (conceptNames == null) {
            conceptNames = new ArrayList<ConceptName>();
        }
        return conceptNames;
    }

    public void setConceptNames(final List<ConceptName> conceptNames) {
        this.conceptNames = conceptNames;
    }

    public String getSynonyms() {
        int size = this.getConceptNames().size();
        switch (size) {
            case 0:
            case 1:
                return "";
            case 2:
                return getFirstConceptName();
            default:
                return getFirstConceptName() + " (" + (size - 2) + " more.)";
        }
    }

    private String getFirstConceptName() {
        return this.getConceptNames().get(0).getName();
    }


    public int getId() { return id; }

    public void setId(int id) { this.id = id; }
}
