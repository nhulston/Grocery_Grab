package com.smithbois.grocerygrab.util.pathfinding;

import android.graphics.Point;

import com.smithbois.grocerygrab.util.Cart;
import com.smithbois.grocerygrab.util.pathfinding.Product;

import java.util.*;
import java.util.List;

public class Driver {

    private static List<String> pointsVisited;

    private static int[][] DIRECTIONS
            = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

    public static void main(String[] args){
        int[][] mapArray = new int[][]{
                {1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 1, 0, 1, 0, 1, 0, 1},
                {1, 0, 1, 0, 1, 0, 1, 0, 1},
                {1, 0, 1, 0, 1, 0, 1, 0, 1},
                {1, 0, 1, 0, 1, 0, 1, 0, 1},
                {1, 0, 1, 0, 1, 0, 1, 0, 1},
                {1, 3, 1, 1, 1, 1, 1, 2, 1}
        };

        Product cheese = new Product(2, 1, "cheese",5);
        Product milk = new Product(2, 3, "milk",6);
        Product chips = new Product(5, 5, "chips",7);

        List<Product> productList = new ArrayList<>();
        productList.add(cheese);
        productList.add(milk);
        productList.add(chips);

        List<Point> optimumRoute = optimizeRoute(mapArray, productList);
        System.out.println("optimum route: " + optimumRoute);
    }


    public static List<Point> optimizeRoute(int[][] mapArray, List<Product> productList){



       for(Product product : productList){
           mapArray[product.getX()][product.getY()] = product.getCode();
       }

       Store store = new Store(mapArray);

        for(Product product : productList){
            store.addProduct(product);
        }

        //System.out.println("Entrance: " + store.getEntrance()[0] + ", " + store.getEntrance()[1]);
        //System.out.println("Exit: " + store.getExit()[0] + ", " + store.getExit()[1]);

       //System.out.println("Products: " + store.getProducts());

        int[] entryArray = store.getEntrance();
        Point entry = new Point(entryArray[0], entryArray[1]);
        //System.out.println("Entry: " + entry);

       HashMap<String, HashMap<Product, List<Point>>> solutionMatrix = new HashMap<String, HashMap<Product, List<Point>>>();
       HashMap<String, HashMap<Product, Integer>> distanceMatrix = new HashMap<String, HashMap<Product, Integer>>();


       HashMap<Product, List<Point>> entrySolutionSet = new HashMap<Product, List<Point>>();

       for (Product product: store.getProducts()){
           entrySolutionSet.put(product, solve(store, product.getCode(), entry));
           store.resetMap();
       }

       HashMap<Product, Integer> entryDistanceSet = new HashMap<Product, Integer>();

       for (Map.Entry<Product, List<Point>> e : entrySolutionSet.entrySet()){
           entryDistanceSet.put(e.getKey(), e.getValue().size());
           //System.out.println("product: " + e.getKey().getName() + ", distance: " + e.getValue().size());
        }

       distanceMatrix.put("Entry", entryDistanceSet);
       solutionMatrix.put("Entry", entrySolutionSet);

       for(int i = 0; i < store.getProducts().size(); i++){

           HashMap<Product, Integer> productDistanceSet = new HashMap<Product, Integer>();
           HashMap<Product, List<Point>> productSolutionSet = new HashMap<Product, List<Point>>();

           for(int j = 0; j < store.getProducts().size(); j++){
               if(i == j){
                   continue;
               } else {
                   List<Point> solution = solve(store, store.getProducts().get(j).getCode(), new Point(store.getProducts().get(i).getX(), store.getProducts().get(i).getY()));
                   store.resetMap();
                   productSolutionSet.put(store.getProducts().get(j), solution);
                   productDistanceSet.put(store.getProducts().get(j), solution.size());
                   //System.out.println("product: " + store.getProducts().get(j).getName() + ", distance: " + productDistanceSet.get(store.getProducts().get(j)) + ", from: " + store.getProducts().get(i).getName());
               }
               solutionMatrix.put(store.getProducts().get(i).getName(), productSolutionSet);
               distanceMatrix.put(store.getProducts().get(i).getName(), productDistanceSet);
           }
       }

       List<String> productOrder = new ArrayList<>();
       List<List<Point>> shortestPaths = new ArrayList<>();

       pointsVisited = new ArrayList<>();
       productOrder.add("Entry");
       String closest = findClosestPoint("Entry", distanceMatrix);
       productOrder.add(closest);
       shortestPaths.add(solutionMatrix.get("Entry").get(store.getProductByName(closest)));
       pointsVisited.add(closest);

       String nextClosest;

       for(int i = 0; i < store.getProducts().size()-1; i++){
           nextClosest = findClosestPoint(closest, distanceMatrix);
           productOrder.add(nextClosest);
           shortestPaths.add(solutionMatrix.get(closest).get(store.getProductByName(nextClosest)));
           pointsVisited.add(nextClosest);
           closest = nextClosest;
       }

       //System.out.println("Product order: " + productOrder);
       List<Point> linePoints = simplyLine(shortestPaths);
       //System.out.println("Line points: " + linePoints);
       return linePoints;
    }
    public static List<Point> solve(Store maze, int target, Point startPoint) {
        List<Point> path = new ArrayList<>();
        Point entry = startPoint;
        //System.out.println("Entry: " + entry);
        if (
                explore(
                        maze,
                        (int)entry.x,
                        (int)entry.y,
                        path,
                        target
                )
        ) {
            return path;
        }
        return Collections.emptyList();
    }
    private static boolean explore(Store maze, int row, int col, List<Point> path, int target) {
        if (
                !maze.isValid(row, col)
                        || maze.isObstructed(row, col)
                        || maze.isExplored(row, col)
        ) {
            return false;
        }

        path.add(new Point(row, col));

        if (maze.getMap()[row][col] == target) {
            return true;
        }
        maze.setVisited(row, col, true);

        for (int[] direction : DIRECTIONS) {
            Point coordinate = getNextCoordinate(
                    row, col, direction[0], direction[1]);
            //System.out.println("coordinate: " + coordinate + ", result: " + maze.getMap()[row][col]);
            //System.out.println("path: " + path);
            if (
                    explore(
                            maze,
                            (int)coordinate.x,
                            (int)coordinate.y,
                            path,
                            target
                    )
            ) {
                return true;
            }
        }

        path.remove(path.size() - 1);
        return false;
    }

    private static Point getNextCoordinate(
            int row, int col, int i, int j) {
        return new Point(row + i, col + j);
    }

    public static String findClosestPoint(String firstNode, HashMap<String, HashMap<Product, Integer>> distanceMatrix){
        HashMap<Product, Integer> productDistanceMatrix = distanceMatrix.get(firstNode);
        Integer lowestDistance = null;
        String closest = null;
        for (Map.Entry<Product, Integer> e : productDistanceMatrix.entrySet()){
            if(!pointsVisited.contains(e.getKey().getName())){
                if (lowestDistance == null || e.getValue() < lowestDistance){
                    closest = e.getKey().getName();
                }
            }
        }
        return closest;
    }

    public static List<Point> simplyLine(List<List<Point>> complexList){
        List<Point> simpleLine = new ArrayList<Point>();
        for (int i = 0; i < complexList.size(); i++){
            List<Point> subList = complexList.get(i);
            if (i == 0){
                for (int j = 0; j < subList.size(); j++){
                    simpleLine.add(subList.get(j));
                }
            } else {
                for (int j = 1; j < subList.size(); j++){
                    simpleLine.add(subList.get(j));
                }
            }
        }
        return simpleLine;
    }
}
