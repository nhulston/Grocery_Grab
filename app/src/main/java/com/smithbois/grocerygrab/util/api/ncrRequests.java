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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

public class ncrRequests {
    public static void postRequest(Context context) throws JSONException {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "https://gateway-staging.ncrcloud.com/emerald/selling-service/v1/carts/1VlfRrThrkiXWHOaPTKAmQ/items";
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("scanData", "101");
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

    public static void createCart(Context context){
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "https://gateway-staging.ncrcloud.com/emerald/selling-service/v1/carts";
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

            @Override
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                System.out.println("response data: " + response.data);
                try {
                    String jsonString = new String(response.data,
                            HttpHeaderParser.parseCharset(response.headers, PROTOCOL_CHARSET));
                    JSONObject jsonResponse = new JSONObject(jsonString);
                    jsonResponse.put("headers", new JSONObject(response.headers));
                    return Response.success(jsonResponse,
                            HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError(e));
                } catch (JSONException je) {
                    return Response.error(new ParseError(je));
                }
            }



        };


        queue.add(jsonObjectRequest);
    }

    public static void getRequest(Context context) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "https://gateway-staging.ncrcloud.com/catalog/2/item-details/2/102";
        StringRequest getRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>()
                {
                    public void onResponse(String response) {
                        System.out.println(response);
                        int a = response.indexOf("\"price\":");
                        int b = response.indexOf(",\"currency\"");
                        double cost = Double.parseDouble(response.substring(a + 8, b));
                        NumberFormat formatter = NumberFormat.getCurrencyInstance();
                        String itemCost = formatter.format(cost);
                        System.out.println("item cost: " + itemCost);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        Log.d("ERROR","error => "+error.toString());
                    }
                }
        ) {
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
        queue.add(getRequest);
    }
}
