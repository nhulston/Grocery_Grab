package com.smithbois.grocerygrab.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.smithbois.grocerygrab.R;
import com.smithbois.grocerygrab.activities.ARActivity;
import com.smithbois.grocerygrab.activities.DashboardActivity;
import com.smithbois.grocerygrab.util.Cart;
import com.smithbois.grocerygrab.util.map.DrawView;

public class ARFragment extends Fragment {

    String[] arr;
    DrawView drawView;
    Button arButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_ar, container, false);
        final Context context = getContext();
        super.onCreate(savedInstanceState);

        drawView = root.findViewById(R.id.draw_view);
        drawView.setBackgroundColor(Color.WHITE);

        arButton = root.findViewById(R.id.ar_button);


        final AutoCompleteTextView editText = root.findViewById(R.id.chooseItemText2);
        arr = new String[] {"Smith Store (0.1 miles)", "Publix (0.7 miles)", "Trader Joes (1.8 miles)", "Whole Foods (1.8 miles)"};
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, arr);
        editText.setAdapter(adapter);

        editText.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(editText.getWindowToken(),
                        InputMethodManager.RESULT_UNCHANGED_SHOWN);
                root.findViewById(R.id.fade_rectangle2).setVisibility(View.GONE);
                root.findViewById(R.id.temp_image).setVisibility(View.VISIBLE);
            }
        });
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.showDropDown();
                root.findViewById(R.id.fade_rectangle2).setVisibility(View.VISIBLE);
            }
        });

        root.findViewById(R.id.fade_rectangle2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                root.findViewById(R.id.fade_rectangle2).setVisibility(View.GONE);

                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(editText.getWindowToken(),
                        InputMethodManager.RESULT_UNCHANGED_SHOWN);
            }
        });

        arButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ARActivity.class);
                startActivity(intent);
            }
        });

        return root;
    }
}