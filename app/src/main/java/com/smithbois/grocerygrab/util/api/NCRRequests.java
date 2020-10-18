package com.smithbois.grocerygrab.util.api;

import android.content.Context;
import android.util.Base64;
import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.smithbois.grocerygrab.util.pathfinding.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

public class NCRRequests {
    private static String cartId;
    private static double total;
    private static Product[] products;

    public static double getTotal() {return total; }

    public static Product[] getProducts() {return products; }

    public static void getcatalog(Context context) throws JSONException {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "https://gateway-staging.ncrcloud.com/catalog/2/item-details/2";
        JSONObject jsonBody = new JSONObject();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, jsonBody, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println("Response: " + response);
                try {
                    JSONArray items = response.getJSONArray("pageContent");
                    products = new Product[items.length()];
                    for (int i=0; i < items.length(); i++) {
                        JSONObject item = items.getJSONObject(i);
                        System.out.println(item);

                        JSONObject longDescription = item.getJSONObject("longDescription");
                        JSONArray values = longDescription.getJSONArray("values");
                        JSONObject value = values.getJSONObject(0);
                        String name = value.getString("value");
                        System.out.println(name);

                        String loc = item.getString("manufacturerCode");
                        System.out.println(loc);

                        JSONObject itemId = item.getJSONObject("itemId");
                        String code = itemId.getString("itemCode");
                        System.out.println(code);

                        JSONArray itemPrices= item.getJSONArray("itemPrices");
                        JSONObject itemPrice = itemPrices.getJSONObject(0);
                        Double price = itemPrice.getDouble("price");
                        System.out.println(price);

                        products[i] = new Product(loc, name, code, price);
                        System.out.println(products[i].toString());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> params = new HashMap<String, String>();
                String creds = String.format("%s:%s","ea9fc3bc-e3c9-43cc-96bd-6a6fa3c36751","password123!");
                String auth = "Basic " + Base64.encodeToString(creds.getBytes(), Base64.DEFAULT);
                params.put("Authorization", auth);

                params.put("content-type", "application/json");
                params.put("nep-organization", "aa317d804243466bb23f1a9f236d166d");
                params.put("nep-enterprise-unit", "b6a4f865404d4b6ab2c70ab1bd9a5b71");

                return params;
            }
        };

        queue.add(jsonObjectRequest);
    }

    public static void createCart(Context context) throws JSONException {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "https://gateway-staging.ncrcloud.com/emerald/selling-service/v1/carts";
        JSONObject jsonBody = new JSONObject();

        MetaRequest jsonObjectRequest = new MetaRequest(Request.Method.POST, url, jsonBody, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println("Response: " + response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> params = new HashMap<String, String>();
                String creds = String.format("%s:%s","ea9fc3bc-e3c9-43cc-96bd-6a6fa3c36751","password123!");
                String auth = "Basic " + Base64.encodeToString(creds.getBytes(), Base64.DEFAULT);
                params.put("Authorization", auth);

                params.put("content-type", "application/json");
                params.put("nep-organization", "aa317d804243466bb23f1a9f236d166d");
                params.put("nep-enterprise-unit", "b6a4f865404d4b6ab2c70ab1bd9a5b71");

                return params;
            }
        };

        queue.add(jsonObjectRequest);
    }

    public static void addToCart(Context context, String itemCode) throws JSONException {
        cartId = MetaRequest.location;
        System.out.println("Cart ID: " + cartId);

        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "https://gateway-staging.ncrcloud.com/emerald/selling-service/v1/carts/" + cartId + "/items";
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("scanData", itemCode);
        JSONObject quantityJson = new JSONObject();
        quantityJson.put("unitOfMeasure", "EA");
        quantityJson.put("value", 1);
        jsonBody.put("quantity", quantityJson);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonBody, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println("Response: " + response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> params = new HashMap<String, String>();
                String creds = String.format("%s:%s","ea9fc3bc-e3c9-43cc-96bd-6a6fa3c36751","password123!");
                String auth = "Basic " + Base64.encodeToString(creds.getBytes(), Base64.DEFAULT);
                params.put("Authorization", auth);

                params.put("content-type", "application/json");
                params.put("nep-organization", "aa317d804243466bb23f1a9f236d166d");
                params.put("nep-enterprise-unit", "b6a4f865404d4b6ab2c70ab1bd9a5b71");

                return params;
            }
        };

        queue.add(jsonObjectRequest);
    }

    public static void getCart(Context context) throws JSONException {
        cartId = MetaRequest.location;
        System.out.println("Cart ID: " + cartId);

        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "https://gateway-staging.ncrcloud.com/emerald/selling-service/v1/carts/" + cartId;
        JSONObject jsonBody = new JSONObject();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, jsonBody, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println("Response: " + response);
                try {
                    JSONObject totals = response.getJSONObject("totals");
                    total = totals.getDouble("balanceDue");
                    System.out.println("Total: " + total);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> params = new HashMap<String, String>();
                String creds = String.format("%s:%s","ea9fc3bc-e3c9-43cc-96bd-6a6fa3c36751","password123!");
                String auth = "Basic " + Base64.encodeToString(creds.getBytes(), Base64.DEFAULT);
                params.put("Authorization", auth);

                params.put("content-type", "application/json");
                params.put("nep-organization", "aa317d804243466bb23f1a9f236d166d");
                params.put("nep-enterprise-unit", "b6a4f865404d4b6ab2c70ab1bd9a5b71");

                return params;
            }
        };

        queue.add(jsonObjectRequest);
    }

    public static void checkOut(Context context) throws JSONException {
        cartId = MetaRequest.location;
        System.out.println("Cart ID: " + cartId);

        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "https://gateway-staging.ncrcloud.com/emerald/selling-service/v1/carts/" + cartId + "/tenders";
        JSONObject jsonBody = new JSONObject();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonBody, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println("Response: " + response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> params = new HashMap<String, String>();
                String creds = String.format("%s:%s","ea9fc3bc-e3c9-43cc-96bd-6a6fa3c36751","password123!");
                String auth = "Basic " + Base64.encodeToString(creds.getBytes(), Base64.DEFAULT);
                params.put("Authorization", auth);

                params.put("content-type", "application/json");
                params.put("nep-organization", "aa317d804243466bb23f1a9f236d166d");
                params.put("nep-enterprise-unit", "b6a4f865404d4b6ab2c70ab1bd9a5b71");

                return params;
            }
        };

        queue.add(jsonObjectRequest);
    }

    public static void deleteCart(Context context) throws JSONException {
        cartId = MetaRequest.location;
        System.out.println("Cart ID: " + cartId);

        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "https://gateway-staging.ncrcloud.com/emerald/selling-service/v1/carts/" + cartId;
        JSONObject jsonBody = new JSONObject();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.DELETE, url, jsonBody, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println("Response: " + response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> params = new HashMap<String, String>();
                String creds = String.format("%s:%s","ea9fc3bc-e3c9-43cc-96bd-6a6fa3c36751","password123!");
                String auth = "Basic " + Base64.encodeToString(creds.getBytes(), Base64.DEFAULT);
                params.put("Authorization", auth);

                params.put("content-type", "application/json");
                params.put("nep-organization", "aa317d804243466bb23f1a9f236d166d");
                params.put("nep-enterprise-unit", "b6a4f865404d4b6ab2c70ab1bd9a5b71");

                return params;
            }
        };

        queue.add(jsonObjectRequest);
    }
    public static Product findProductByName(String name){
        for(int i = 0; i < getProducts().length; i++){
            if(getProducts()[i].getName().equals(name)){
                return getProducts()[i];
            }
        }
        return null;
    }
}
