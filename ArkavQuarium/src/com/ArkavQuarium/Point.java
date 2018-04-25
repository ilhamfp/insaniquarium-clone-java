package com.ArkavQuarium;

import java.io.Serializable;

public class Point implements Serializable {
    private double x,y;

    public Point(){
        x = 0.0;
        y = 0.0;
    }

    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public Point getLocation(){
        return new Point(x,y);
    }

    public void setX(double x){
        this.x = x;
    }

    public void setY(double y){
        this.y = y;
    }

    public void setLocation(double x, double y){
        this.x = x;
        this.y = y;
    }

    public double findDistance(Point p){
        double tempX = p.getX() - x;
        double tempY = p.getY() - y;
        return Math.sqrt(tempX*tempX + tempY*tempY);
    }

    public boolean equals(Point p){
        return x == p.getX() && y == p.getY();
    }

    public void print(){
        System.out.print(x);
        System.out.print(',');
        System.out.println(y);
    }
}
