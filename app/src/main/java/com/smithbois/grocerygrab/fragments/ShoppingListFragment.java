package com.smithbois.grocerygrab.fragments;


import android.content.Context;
import android.os.Bundle;
import android.text.Layout;
import android.util.Base64;
import android.util.Log;
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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.smithbois.grocerygrab.R;
import com.smithbois.grocerygrab.util.Cart;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingListFragment extends Fragment {

    private static String itemCost;
    private static final String[] ITEMS = new String[] {
            "Asparagus", "Broccoli", "Carrots", "Cauliflower", "Celery", "Corn", "Cucumbers", "Lettuce", "Greens", "Mushrooms", "Onions", "Peppers", "Potatoes", "Spinach", "Squash", "Zucchini", "Tomatoes",
            "Apples", "Avocados", "Bananas", "Berries", "Cherries", "Grapefruit", "Grapes", "Kiwis", "Lemons", "Limes", "Melon", "Oranges", "Peaches", "Nectarines", "Pears", "Plums", "Bagels", "Chip dip",
            "English muffins", "Eggs", "Fruit juice", "Hummus", "Ready-bake breads", "Tofu", "Tortillas", "Breakfasts", "Burritos", "Fish sticks", "Ice cream", "Sorbet", "Juice concentrate", "Pizza", "Pizza Rolls",
            "Popsicles", "Fries", "Tater tots", "TV dinners", "Vegetables", "Veggie burgers", "BBQ sauce", "Gravy", "Honey", "Hot sauce", "Jam / Jelly", "Ketchup", "Mustard", "Mayonnaise", "Pasta sauce", "Relish",
            "Salad dressing", "Salsa", "Soy sauce", "Steak sauce", "Syrup", "Worcestershire sauce", "Bouillon cubes", "Cereal", "Coffee", "Coffe filters", "Instant potatoes", "Lemon juice", "Lime juice", "Mac and cheese",
            "Olive oil", "Pancake mix", "Waffle mix", "Pasta", "Peanut butter", "Pickles", "Rice", "Tea", "Vegetable oil", "Vinegar", "Applesauce", "Baked beans", "Chili", "Fruit", "Olives", "Tinned meats", "Tuna",
            "Chicken", "Soups", "Veggies", "Basil", "Black pepper", "Cilantro", "Cinnamon", "Garlic", "Ginger", "Mint", "Oregano", "Paprika", "Parsley", "Red pepper", "Salt", "Spice mix", "Vanilla extract",
            "Butter", "Margarine", "Cottage cheese", "Half & half", "Milk", "Sour cream", "Whipped cream", "Yogurt", "Bleu cheese", "Cheddar", "Cottage cheese", "Cream cheese", "Feta", "Goat cheese", "Mozzarella",
            "Provolone", "Parmesan", "Provolone", "Ricotta", "Sandwich slices", "Swiss", "Bacon", "Sausage", "Beef", "Chicken", "Ground beef", "Turkey", "Ham", "Pork", "Hot dogs", "Lunchmea", "Catfish", "Crab", "Lobster",
            "Mussels", "Oysters", "Salmon", "Shrimp", "Tilapia", "Tuna", "Beer", "Club soda", "Tonic", "Champagne", "Gin", "Juice", "Mixers", "Red wine", "White wine", "Rum", "Saké", "Soda pop", "Sports drink", "Whiskey",
            "Vodka", "Bagels", "Croissants", "Buns", "Rolls", "Cake", "Cookies", "Donuts", "Pastries", "Fresh bread", "Sliced bread", "Pie", "Pita bread", "Baking powder", "Baking soda", "Bread crumbs", "Cake",
            "Brownie mix", "Icing", "Cake decorations", "Chocolate chips", "Cocoa", "Flour", "Shortening", "Sugar", "Sugar substitute", "Yeast", "Candy", "Gum", "Cookies", "Crackers", "Dried fruit", "Granola bars",
            "Nuts", "Seeds", "Oatmeal", "Popcorn", "Potato chips", "Pretzels", "Burger night", "Chili night", "Pizza night", "Spaghetti night", "Taco night", "Take-out deli food", "Baby food", "Diapers", "Formula",
            "Lotion", "Baby wash", "Wipes", "Cat food", "Cat treats", "Cat litter", "Dog food", "Treats", "Flea treatment", "Pet shampoo", "Antiperspirant", "Deodorant", "Bath soap", "Hand soap", "Condoms", "Cosmetics",
            "Cotton balls", "Facial cleanser", "Facial tissue", "Feminine products", "Floss", "Hair gel", "Hair spray", "Lip balm", "Moisturizing lotion", "Mouthwash", "Razors", "Shaving cream", "Shampoo", "Conditioner",
            "Sunblock", "Toilet paper", "Toothpaste", "Vitamins", "Supplements", "Allergy", "Antibiotic", "Antidiarrheal", "Aspirin", "Antacid", "Band-aids", "Medical", "Cold medication", "Flu medicatoin",
            "Sinus medication", "Pain reliever", "Prescription pick-up", "Aluminum foil", "Napkins", "Non-stick spray", "Paper towels", "Plastic wrap", "Sandwich bags", "Freezer bags", "Wax paper", "Air freshener",
            "Bathroom cleaner", "Bleach", "Detergent", "Dish soap", "Garbage bags", "Glass cleaner", "Mop head", "Vacuum bags", "Sponges", "Notepad", "Envelopes", "Glue", "Tape", "Paper", "Pens", "Pencils",
            "Postage stamps", "Automotive", "Batteries", "Charcoal", "Propane", "Flowers", "Greeting card", "Insect repellent", "Light bulbs", "Newspaper", "Magazine", "Water"
    };
    private AutoCompleteTextView editText;
    private ImageView plusButton;
    private ArrayAdapter<String> adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_shopping_list, container, false);
        final Context context = getContext();

        editText = root.findViewById(R.id.chooseItemText);
        plusButton = root.findViewById(R.id.plus_icon);

        adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, ITEMS);
        editText.setAdapter(adapter);

        editText.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showAddItemLayout(position, context, root);
            }
        });

        root.findViewById(R.id.back_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                root.findViewById(R.id.invis_layout).setVisibility(View.GONE);
                root.findViewById(R.id.fade_rectangle).setVisibility(View.GONE);

                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(editText.getWindowToken(),
                        InputMethodManager.RESULT_UNCHANGED_SHOWN);
            }
        });

        Button addButton = root.findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                root.findViewById(R.id.invis_layout).setVisibility(View.VISIBLE);
                root.findViewById(R.id.fade_rectangle).setVisibility(View.VISIBLE);
            }
        });

        FloatingActionButton fButton = root.findViewById(R.id.floatingActionButton);
        fButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                root.findViewById(R.id.invis_layout).setVisibility(View.VISIBLE);
                root.findViewById(R.id.fade_rectangle).setVisibility(View.VISIBLE);
            }
        });

        root.findViewById(R.id.fade_rectangle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                root.findViewById(R.id.fade_rectangle).setVisibility(View.GONE);
                root.findViewById(R.id.invis_layout).setVisibility(View.GONE);

                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(editText.getWindowToken(),
                        InputMethodManager.RESULT_UNCHANGED_SHOWN);
            }
        });

//        root.findViewById(R.id.cart_button).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                root.findViewById(R.id.cart).setVisibility(View.VISIBLE);
//                requestWithSomeHttpHeaders(context);
//                System.out.println(itemCost);
//            }
//        });

        return root;
    }

    public void requestWithSomeHttpHeaders(Context context) {
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
                        itemCost = formatter.format(cost);
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

    public void showAddItemLayout(int position, Context context, final ViewGroup root){
        String item = adapter.getItem(position);
        editText.setText("");

        final LinearLayout linearLayout = root.findViewById(R.id.scroll);

        final RadioButton btn = new RadioButton(context);
        linearLayout.addView(btn);
        btn.setText(item);
        btn.setTextColor(getResources().getColor(R.color.primaryText));
        btn.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(15,30,0,30);
        btn.setLayoutParams(params);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = (String) btn.getText();
                Cart.getCart().add(s);
                linearLayout.removeView(btn);
                TextView tv = root.findViewById(R.id.cart_count);
                tv.setText(String.valueOf(Integer.parseInt(tv.getText().toString()) + 1));
            }
        });

        Toast t = Toast.makeText(context, "Item added to list", Toast.LENGTH_LONG);
        t.setGravity(Gravity.TOP, 0, 0);
        t.show();
    }

}