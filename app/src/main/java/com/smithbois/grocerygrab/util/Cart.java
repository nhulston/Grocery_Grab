package com.smithbois.grocerygrab.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Cart {
    private static List<String> cart = new ArrayList<>();
    private static HashMap<String, Double> prices = new HashMap<>();
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

    public static List<String> getCart() {
        return cart;
    }

    // Code to get and set prices
    public static void setupPricesMap() {
        // TODO temporary hardcode
        for (String s : ITEMS) {
            prices.put(s, 5.0);
        }
    }

    public static HashMap<String, Double> getPrices() {
        return prices;
    }
}
