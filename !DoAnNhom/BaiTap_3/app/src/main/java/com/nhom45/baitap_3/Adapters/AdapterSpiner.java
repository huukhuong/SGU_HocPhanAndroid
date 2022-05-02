package com.nhom45.baitap_3.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.nhom45.baitap_3.Models.Currency;
import com.nhom45.baitap_3.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterSpiner extends ArrayAdapter<Currency> {

    public AdapterSpiner(@NonNull Context context, int resource, @NonNull ArrayList<Currency> objects) {
        super(context, resource, objects);
    }

    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_currency_selected, parent, false);

        ImageView imgFlag = convertView.findViewById(R.id.imgFlag);
        TextView txtLabel = convertView.findViewById(R.id.txtLabel);

        Currency currency = this.getItem(position);
        if (currency != null) {
            Picasso.with(parent.getContext()).load(currency.getFlag()).into(imgFlag);
            txtLabel.setText(currency.getCurrencyCode());
        }

        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_currency, parent, false);

        ImageView imgFlag = convertView.findViewById(R.id.imgFlag);
        TextView txtLabel = convertView.findViewById(R.id.txtLabel);

        Currency currency = this.getItem(position);
        if (currency != null) {
            Picasso.with(parent.getContext()).load(currency.getFlag()).into(imgFlag);
            txtLabel.setText(currency.getCurrencyCode());
        }

        return convertView;
    }
}
