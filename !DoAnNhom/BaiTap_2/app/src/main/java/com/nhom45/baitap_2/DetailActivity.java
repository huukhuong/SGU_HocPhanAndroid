package com.nhom45.baitap_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.nhom45.baitap_2.Models.Country;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

public class DetailActivity extends AppCompatActivity {

    private TextView txtCountryName;
    private ImageView imgFlag;
    private TextView txtCapital;
    private TextView txtPopulation;
    private TextView txtArea;
    private ImageView imgMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Country country = (Country) getIntent().getSerializableExtra("country");

        addControls();
        loadData(country);
    }

    private void addControls() {
        txtCountryName = findViewById(R.id.txtCountryName);
        imgFlag = findViewById(R.id.imgFlag);
        txtCapital = findViewById(R.id.txtCapital);
        txtPopulation = findViewById(R.id.txtPopulation);
        txtArea = findViewById(R.id.txtArea);
        imgMap = findViewById(R.id.imgMap);
    }

    private void loadData(Country country) {
        DecimalFormat dcf = new DecimalFormat("###,###");
        txtCountryName.setText(country.getCountryName());
        txtCapital.setText(country.getCapital());
        txtPopulation.setText(dcf.format(country.getPopulation()));
        txtArea.setText(dcf.format(country.getAreaInSqKm()));
        Picasso.with(this)
                .load(country.getFlag())
                .into(imgFlag);
        Picasso.with(this)
                .load(country.getMap())
                .placeholder(R.drawable.progress_animation)
                .into(imgMap);

        Log.e("IMG", country.getMap());
    }
}