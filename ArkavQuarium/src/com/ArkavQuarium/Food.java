package com.ArkavQuarium;

public class Food extends Item {
    private int price;

    public Food(){
        super(-1.0,-1.0);
        price = 5;
    }

    public Food(Point p){
        super(p.getX(),p.getY());
        price = 5;
    }
}
