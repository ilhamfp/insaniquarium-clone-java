package com.ArkavQuarium;

public class Bool {
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
