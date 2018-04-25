package com.arkavquarium;

import java.io.Serializable;

/**
 * class to replace boolean.
 */
public class Bool implements Serializable {
    /**
     * atribut class.
     */
    private boolean value;

    /**
     * constructor.
     */
    public Bool() {
        value = false;
    }

    /**
     * constuctor with parameter.
     * @param val = initialize value
     */
    public Bool(final boolean val) {
        value = val;
    }

    /**
     * getter for atribute vaue.
     * @return value
     */
    public boolean getValue() {
        return value;
    }

    /**
     * setter for atribute value.
     * @param val will be assigned to value
     */
    public void setValue(final boolean val) {
        value = val;
    }
}
