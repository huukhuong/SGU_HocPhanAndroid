package com.nhom45.baitap_4.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.nhom45.baitap_4.Models.Image;
import com.nhom45.baitap_4.R;
import com.nhom45.baitap_4.Ultils.Helpers;

import java.io.File;
import java.util.List;

public class ImageAdapter extends ArrayAdapter<Image> {

    private int resourceLayout;
    private Context mContext;

    public ImageAdapter(@NonNull Context context, int resource, @NonNull List<Image> objects) {
        super(context, resource, objects);
        this.resourceLayout = resource;
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi = LayoutInflater.from(mContext);
            v = vi.inflate(resourceLayout, null);
        }

        Image item = getItem(position);

        if (item != null) {
            TextView txtName = v.findViewById(R.id.txtName);
            ImageView imageView = v.findViewById(R.id.imgView);

            txtName.setText(item.getName());
            imageView.setImageBitmap(Helpers.getImageFromPath(item.getPath()));
        }

        return v;
    }

}
