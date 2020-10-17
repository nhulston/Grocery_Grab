package com.smithbois.grocerygrab.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.smithbois.grocerygrab.R;

public class DashboardActivity extends AppCompatActivity {

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
            "Postage stamps", "Automotive", "Batteries", "Charcoal", "Propane", "Flowers", "Greeting card", "Insect repellent", "Light bulbs", "Newspaper", "Magazine"
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_shopping_list);

        final AutoCompleteTextView editText = findViewById(R.id.chooseItemText);
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ITEMS);
        editText.setAdapter(adapter);

        editText.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                String item = adapter.getItem(position);
                System.out.println(item);
                editText.setText("");

                Toast t = Toast.makeText(getBaseContext(), "Item added to list", Toast.LENGTH_LONG);
                t.setGravity(Gravity.TOP, 0, 0);
                t.show();
            }
        });


        Button addButton = findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.invis_layout).setVisibility(View.VISIBLE);
                findViewById(R.id.fade_rectangle).setVisibility(View.VISIBLE);
            }
        });
    }
}
