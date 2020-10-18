package com.smithbois.grocerygrab.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.smithbois.grocerygrab.R;
import com.smithbois.grocerygrab.util.Cart;
import com.smithbois.grocerygrab.util.api.NCRRequests;
import com.smithbois.grocerygrab.util.pathfinding.Product;

import org.json.JSONException;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class CheckoutDialog {

    private static Button checkoutButton;
    private static List<TextView> cartItemNames;
    private static List<TextView> cartItemPrices;

    private TextView cartItem1;
    private TextView cartItem2;
    private TextView cartItem3;
    private TextView cartItem4;
    private TextView cartItem5;
    private TextView cartPrice1;
    private TextView cartPrice2;
    private TextView cartPrice3;
    private TextView cartPrice4;
    private TextView cartPrice5;

    private static TextView subtotalAmountText;
    private static TextView taxesAmountText;
    private static TextView totalAmountText;

    public static Dialog onCreateDialog(Context context) throws JSONException {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
        final AlertDialog dialogs = builder.create();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.checkout_layout, null);
        dialogs.setView(dialogView);
        dialogs.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        cartItemNames = new ArrayList<>();
        cartItemNames.add(dialogView.findViewById(R.id.cartitem1));
        cartItemNames.add(dialogView.findViewById(R.id.cartitem2));
        cartItemNames.add(dialogView.findViewById(R.id.cartitem3));
        cartItemNames.add(dialogView.findViewById(R.id.cartitem4));
        cartItemNames.add(dialogView.findViewById(R.id.cartitem5));

        cartItemPrices = new ArrayList<>();
        cartItemPrices.add(dialogView.findViewById(R.id.cartprice1));
        cartItemPrices.add(dialogView.findViewById(R.id.cartprice2));
        cartItemPrices.add(dialogView.findViewById(R.id.cartprice3));
        cartItemPrices.add(dialogView.findViewById(R.id.cartprice4));
        cartItemPrices.add(dialogView.findViewById(R.id.cartprice5));


        NumberFormat formatter = NumberFormat.getCurrencyInstance();

        List<Product> cartProducts = Cart.getCart();
        for(int i = 0; i < cartProducts.size(); i++){
            if (cartProducts.get(i) != null){
                cartItemNames.get(i).setText(cartProducts.get(i).getName());
                cartItemNames.get(i).setVisibility(View.VISIBLE);
                cartItemPrices.get(i).setText(formatter.format(cartProducts.get(i).getPrice()));
                cartItemPrices.get(i).setVisibility(View.VISIBLE);
            }
        }

        NCRRequests.getCart(context);
        for(int i = 0; i < 50; i++){
            System.out.println("cart total: " + NCRRequests.getTotal());
        }

        List<Double> prices = new ArrayList<>();

        for (TextView t : cartItemPrices){
            if(t.getVisibility() != View.GONE){
                String str = (String) t.getText();
                String newStr = str.substring(1);
                prices.add(Double.parseDouble(newStr));
            }
        }
        double subtotal = 0;
        for(double price : prices){
            subtotal = subtotal + price;
        }
        double taxes = subtotal * .09;
        double total = subtotal + taxes;



        subtotalAmountText = dialogView.findViewById(R.id.subtotal_amount_text);
        subtotalAmountText.setText(formatter.format(subtotal));

        taxesAmountText = dialogView.findViewById(R.id.tax_amount_text);
        taxesAmountText.setText(formatter.format(taxes));

        totalAmountText = dialogView.findViewById(R.id.total_amount_text);
        totalAmountText.setText(formatter.format(total));

        System.out.println("text view visibility: " + cartItemNames.get(1).getVisibility());

        checkoutButton = dialogView.findViewById(R.id.checkout_button);
        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    NCRRequests.checkOut(context);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    NCRRequests.deleteCart(context);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Toast t = Toast.makeText(context, "User Checked Out!", Toast.LENGTH_LONG);
                t.setGravity(Gravity.TOP, 0, 0);
                t.show();
                dialogs.dismiss();
            }
        });

        return dialogs;
    }

}
