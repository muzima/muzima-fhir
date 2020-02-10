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

public class PersonAttribute  {

    private String attribute;

    private PersonAttributeType attributeType;

    /* get the patient attribute
       * @return the patient attribute
       */
    public String getAttribute() {
        return attribute;
    }

    /* set the patient attribute
      * @param the patient attribute
      */
    public void setAttribute(final String attribute) {
        this.attribute = attribute;
    }

    /* get the patient attributeType
        * @return the patient attributeType
        */
    public PersonAttributeType getAttributeType() {
        return attributeType;
    }

    /* set the patient attributeType
        * @param the patient attributeType
        */
    public void setAttributeType(PersonAttributeType attributeType) {
        this.attributeType = attributeType;
    }
}
