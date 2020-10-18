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
import com.smithbois.grocerygrab.util.api.ncrRequests;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

public class PostTestFragment extends Fragment {

    private Button postTestButton;
    private Button getTestButton;
    private Button createCartButton;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final ViewGroup root = (ViewGroup) inflater.inflate(R.layout.post_test_layout, container, false);
        final Context context = getContext();

        postTestButton = root.findViewById(R.id.post_test_button);
        postTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    ncrRequests.postRequest(context);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
        getTestButton = root.findViewById(R.id.get_test_button);
        getTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ncrRequests.getRequest(context);
            }
        });

        createCartButton = root.findViewById(R.id.create_cart_button);
        createCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ncrRequests.createCart(context);
            }
        });

        return root;
    }


}
