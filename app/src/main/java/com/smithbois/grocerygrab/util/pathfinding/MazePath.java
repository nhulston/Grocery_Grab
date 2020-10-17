package com.smithbois.grocerygrab.util.pathfinding;


import android.graphics.Point;

import java.util.ArrayList;

public class MazePath {
    private Point startPoint;
    private Point endPoint;
    ArrayList<Point> pathPoints;

    MazePath(Point startPoint, Point endPoint){
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.pathPoints = new ArrayList<Point>();
    }

}
