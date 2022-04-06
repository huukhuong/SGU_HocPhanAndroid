package com.nhom45.baitap_2.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.nhom45.baitap_2.Models.Country;
import com.nhom45.baitap_2.R;

import java.io.InputStream;
import java.text.DecimalFormat;
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
            viewHolder.txtPopulation = v.findViewById(R.id.txtPopulation);
            viewHolder.txtAreaSquare = v.findViewById(R.id.txtAreaSquare);

            v.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) v.getTag();
        }

        DecimalFormat sdf = new DecimalFormat("###,###.###");
        Country item = getItem(position);
        if(item.getFlagBitmap() != null) {
            viewHolder.imgFlag.setImageBitmap(item.getFlagBitmap());
        }
        viewHolder.txtCountryName.setText(item.getCountryName());
        viewHolder.txtPopulation.setText(sdf.format(item.getPopulation()));
        viewHolder.txtAreaSquare.setText(sdf.format(item.getAreaInSqKm()));

        return v;
    }

    private static class ViewHolder {
        ImageView imgFlag;
        TextView txtCountryName;
        TextView txtPopulation;
        TextView txtAreaSquare;
    }
}
