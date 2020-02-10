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
public class Location {

    private int id;

    private String name;

    private List<LocationAttribute> attributes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public List<LocationAttribute> getAttributes() {
        if (attributes == null) {
            attributes = new ArrayList<LocationAttribute>();
        }
        return attributes;
    }

    public void addAttribute(final LocationAttribute attribute) {
        getAttributes().add(attribute);
    }

    public void setAttributes(List<LocationAttribute> attributes) {
        this.attributes = attributes;
    }

}
