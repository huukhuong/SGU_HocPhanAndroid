package com.nhom45.baitap_2.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.nhom45.baitap_2.Models.Country;
import com.nhom45.baitap_2.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterCountry extends ArrayAdapter<Country> {

    private int resourceLayout;
    private Context mContext;

    public AdapterCountry(@NonNull Context context, int resource, @NonNull List<Country> objects) {
        super(context, resource, objects);
        this.resourceLayout = resource;
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ViewHolder viewHolder;

        if (v == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            v = inflater.inflate(resourceLayout, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.imgFlag = v.findViewById(R.id.imgFlag);
            viewHolder.txtCountryName = v.findViewById(R.id.txtCountryName);

            v.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) v.getTag();
        }

        Country item = getItem(position);
        viewHolder.txtCountryName.setText(item.getCountryName());
        Picasso.with(mContext).load(item.getFlag()).into(viewHolder.imgFlag);

        return v;
    }

    private static class ViewHolder {
        ImageView imgFlag;
        TextView txtCountryName;
    }
}
