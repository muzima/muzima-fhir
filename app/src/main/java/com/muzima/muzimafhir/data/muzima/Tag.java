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
 * A tag is a class to hold tag reference in the server. Each form can have multiple tags attached to it.
 */
public class Tag {

    private String name;

    /**
     * Get the name for the tag.
     *
     * @return the name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name for the tag.
     *
     * @param name the name to tag.
     */
    public void setName(String name) {
        this.name = name;
    }
}
