package com.ArkavQuarium;

import java.io.Serializable;

public class Bool implements Serializable {
    private boolean value;

    public Bool(){
        value = false;
    }

    public Bool(boolean val) {
        value = val;
    }

    public boolean getValue(){
        return value;
    }

    public void setValue(boolean val){
        value = val;
    }
}
