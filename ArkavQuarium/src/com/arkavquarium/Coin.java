package com.arkavquarium;
import static com.arkavquarium.Constants.*;

public class Coin extends Item {
    private int baseVal;
    private int value;

    public Coin(){
        super(-1,-1);
        baseVal = COIN_BASE_VALUE;
        value = 0;
    }

    public Coin(Point p, int level){
        super(p.getX(),p.getY());
        baseVal = COIN_BASE_VALUE;
        value = baseVal*level;
    }

    /*** setter, getter ***/
    // setter untuk nilai value
    public void setValue(int val){
        value = val;
    }

    // getter untuk nilai value
    public int getValue(){
        return value;
    }

    public int getBaseVal(){
        return baseVal;
    }
}
