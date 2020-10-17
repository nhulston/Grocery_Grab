package com.smithbois.grocerygrab.util.pathfinding;

import android.graphics.Point;

public class Product {
    private int x;
    private int y;
    private String name;
    private int code;

    public Product(int x, int y, String name, int code){
        this.x = x;
        this.y = y;
        this.name = name;
        this.code = code;
    }
    public int getX(){
        return x;
    }

    public int getY() {
        return y;
    }

    public Point getLocation(){
        return new Point(x, y);
    }
    public String getName() {
        return name;
    }
    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "Product{" +
                "x=" + x +
                ", y=" + y +
                ", name='" + name + '\'' +
                ", code=" + code +
                '}';
    }
}
