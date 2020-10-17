package com.smithbois.grocerygrab.util.pathfinding;

import java.util.ArrayList;

public class Store {
    private int[][] map; // 0 is open, 1 is obstructed, 2 is entrance, 3 is exit, 4 is path
    private int[][] defaultMap;
    private ArrayList<Product> products;

    public Store(int[][] map){
        this.map = map;
        products = new ArrayList<Product>();
        int[][] defaultMap = new int[map.length][];
        for(int i = 0; i < map.length; i++){
            defaultMap[i] = map[i].clone();
        }
        this.defaultMap = defaultMap;
    }
    public Store(int[][] map, ArrayList<Product> products){
        this.map = map;
        this.products = products;
    }

    public int[][] getMap() {
        return map;
    }

    public Product productAt(int x, int y){
        for (Product product : products){
            if (product.getX() == x && product.getY() == y){
                return product;
            }
        }
        return null;
    }
    public void addProduct(Product product){
        products.add(product);
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public Product getProductByName(String oName){
        for (Product product: products){
            if(product.getName().equals(oName)){
                return product;
            }
        }
        return null;
    }

    public int[] getEntrance(){
        //System.out.println("map 7,7: " + map[7][7]);
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[i].length; j++){
                if(map[i][j] == 2){
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }
    public int[] getExit(){
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[i].length; j++){
                if(map[i][j] == 3){
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }
    public boolean isObstructed(int x, int y){
        return map[x][y] == 1;
    }

    public boolean isValid(int row, int col) {
        return (row >= 0) && (row < map.length) &&
                (col >= 0) && (col < map[1].length);
    }
    public boolean isExplored(int x, int y){
        return map[x][y] == 4;
    }
    public void setVisited(int x, int y, boolean bool) {
        if(bool){
            map[x][y] = 4;
        } else {
            map[x][y] = 0;
        }
    }

    public void resetMap(){
        int[][] temp = new int[defaultMap.length][];
        for(int i = 0; i < defaultMap.length; i++){
            temp[i] = defaultMap[i].clone();
        }
        this.map = temp;
    }



}
