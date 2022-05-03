package com.nhom45.baitap_3.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.nhom45.baitap_3.Models.History;
import com.nhom45.baitap_3.R;

import java.util.List;


public class AdapterHistory extends ArrayAdapter<History> {

    private int resourceLayout;
    private Context mContext;

    public AdapterHistory(@NonNull Context context, int resource, @NonNull List<History> objects) {
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
            viewHolder.txtDate = v.findViewById(R.id.txtDate);
            viewHolder.txtValueFrom = v.findViewById(R.id.txtValueFrom);
            viewHolder.txtCodeFrom = v.findViewById(R.id.txtCodeFrom);
            viewHolder.txtValueTo = v.findViewById(R.id.txtValueTo);
            viewHolder.txtCodeTo = v.findViewById(R.id.txtCodeTo);

            v.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) v.getTag();
        }

        History item = getItem(position);
        viewHolder.txtDate.setText(item.getCreatedAt());
        viewHolder.txtValueFrom.setText(item.getValueInput());
        viewHolder.txtCodeFrom.setText(item.getCodeFrom());
        viewHolder.txtValueTo.setText(item.getValueOutput());
        viewHolder.txtCodeTo.setText(item.getCodeTo());

        return v;
    }

    private static class ViewHolder {
        TextView txtDate;
        TextView txtValueFrom;
        TextView txtCodeFrom;
        TextView txtValueTo;
        TextView txtCodeTo;
    }

}
