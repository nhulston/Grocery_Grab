package com.smithbois.grocerygrab.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smithbois.grocerygrab.R;

public class CheckoutDialog {

    public static Dialog onCreateDialog(Context context) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
        final AlertDialog dialogs = builder.create();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.checkout_layout, null);
        dialogs.setView(dialogView);
        dialogs.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        return dialogs;
    }

}
