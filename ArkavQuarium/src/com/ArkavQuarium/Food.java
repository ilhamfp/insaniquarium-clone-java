package com.ArkavQuarium;
import static com.ArkavQuarium.Constants.*;


public class Food extends Item {
    private int price;

    public Food(){
        super(-1.0,-1.0);
        price = FOOD_PRICE;
    }

    public Food(Point p){
        super(p.getX(),p.getY());
        price = FOOD_PRICE;
    }

    public int getPrice() {
        return price;
    }
}
