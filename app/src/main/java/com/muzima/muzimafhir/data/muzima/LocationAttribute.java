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

public class LocationAttribute {

    private String attribute;

    private LocationAttributeType attributeType;

    /* get the location attribute
       * @return the location attribute
       */
    public String getAttribute() {
        return attribute;
    }

    /* set the location attribute
      * @param the location attribute
      */
    public void setAttribute(final String attribute) {
        this.attribute = attribute;
    }

    /* get the location attributeType
        * @return the location attributeType
        */
    public LocationAttributeType getAttributeType() {
        return attributeType;
    }

    /* set the location attributeType
        * @param the location attributeType
        */
    public void setAttributeType(LocationAttributeType attributeType) {
        this.attributeType = attributeType;
    }
}
