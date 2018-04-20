package com.ArkavQuarium;

public class Item extends Point{
    private double speed;

    public Item(){
        super(-1,-1);
        speed = 0.6;
    }

    public Item(double x, double y) {
        super(x,y);
        speed = 0.6;
    }

    void move(double maks){
        double gerakSejauh = Math.min(super.getY()+speed,maks);
        super.setLocation(getX(),gerakSejauh);
    }

    boolean isAtBottom(double bottom){
        return (super.getY() == bottom);
    }
}

//class Item: public Point {
//public:
//        /*** ctor, cctor, dtor ***/
//        Item():Point(-1,-1),speed(0.6){
//        }
//
//        Item(int x, int y):Point(x,y),speed(0.6){
//        }
//        // konstruktor kelas Barang
//        // parameter 1 menyatakan absis barang, parameter 2 menyatakan ordinat barang
//
//        /*** fungsi lain ***/
//        // menggerakkan benda ke arah bawah
//        void move(double maks){
//          double gerakSejauh = min(getY()+speed,maks);
//          setLocation(getX(),gerakSejauh);
//        }
//
//        bool isAtBottom(double bottom){
//          return (getY() == bottom);
//        }
//// mengecek apakah benda ada di dasar akuarium.
//// parameter 1 menyatakan max x dan y
//
//protected:
//        double speed;     // kecepatan turun barang
//};