package com.smithbois.grocerygrab.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.smithbois.grocerygrab.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

public class PostTestFragment extends Fragment {

    private Button postTestButton;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final ViewGroup root = (ViewGroup) inflater.inflate(R.layout.post_test_layout, container, false);
        final Context context = getContext();

        postTestButton = root.findViewById(R.id.post_test_button);
        postTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    requestWithSomeHttpHeaders(context);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

        return root;
    }

    public void requestWithSomeHttpHeaders(Context context) throws JSONException {
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

}
