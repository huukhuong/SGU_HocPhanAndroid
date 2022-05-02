package com.nhom45.baitap_2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.nhom45.baitap_2.Adapters.AdapterCountry;
import com.nhom45.baitap_2.Models.Country;
import com.nhom45.baitap_2.Ultils.Constants;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private LinearLayout loading;
    private AdapterCountry adapterCountry;
    private ArrayList<Country> countryArrayList, countryArrayListLazy;
    private ListView lsvCountries;
    private int maxCount = 0;
    private ImageView loadingImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        addEvents();
        new GetCountryAPI().execute();
    }

    private void addControls() {
        countryArrayList = new ArrayList<>();
        countryArrayListLazy = new ArrayList<>();

        loading = findViewById(R.id.loading);
        loadingImg = findViewById(R.id.loadingImg);
        lsvCountries = findViewById(R.id.lsvCountries);

        adapterCountry = new AdapterCountry(this, R.layout.item_country, countryArrayListLazy);
        lsvCountries.setAdapter(adapterCountry);

        lsvCountries.setVisibility(View.GONE);
        loading.setVisibility(View.VISIBLE);
    }

    private void addEvents() {
        lsvCountries.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount != 0) {
                    lazyLoad();
                }
            }
        });

        lsvCountries.setOnItemClickListener((adapterView, view, i, l) -> {
            onItemClick(i);
        });
    }

    private void onItemClick(int i) {
        Country country = countryArrayListLazy.get(i);
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra("country", country);
        startActivity(intent);
    }

    private void lazyLoad() {
        if (countryArrayList.size() == 0) {
            return;
        }
        int temp = 0;
        for (int i = 0; i < countryArrayList.size(); i++) {
            if (temp == Constants.ITEM_COUNT_LAZY) {
                return;
            }
            Country countrySelected = countryArrayList.get(i);
            countryArrayListLazy.add(countrySelected);
            countryArrayList.remove(i);
            temp++;
        }
        adapterCountry.notifyDataSetChanged();
    }

    class GetCountryAPI extends AsyncTask<Void, Void, ArrayList<Country>> {

        private Country country;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            countryArrayList.clear();

            RotateAnimation rotate = new RotateAnimation(
                    0,
                    180, Animation.RELATIVE_TO_SELF,
                    0.5f, Animation.RELATIVE_TO_SELF,
                    0.5f);
            rotate.setDuration(3000);
            rotate.setInterpolator(new LinearInterpolator());

            loadingImg.startAnimation(rotate);
        }

        @Override
        protected void onPostExecute(ArrayList<Country> listCountries) {
            super.onPostExecute(listCountries);
            countryArrayList.addAll(listCountries);
            maxCount = countryArrayList.size();
            Log.e("Start", "Data get successfully");

            lazyLoad();

            lsvCountries.setVisibility(View.VISIBLE);
            loading.setVisibility(View.GONE);
        }

        @Override
        protected ArrayList doInBackground(Void... voids) {
            Log.e("Start", "Staring get API");

            ArrayList<Country> listCountries = new ArrayList<>();
            try {
                URL url = new URL(Constants.API_URL);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream(), "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder stringBuilder = new StringBuilder();
                String line = bufferedReader.readLine();
                while (line != null) {
                    stringBuilder.append(line);
                    line = bufferedReader.readLine();
                }

                JSONObject jsonObject = new JSONObject(stringBuilder.toString());
                JSONArray jsonArray = jsonObject.getJSONArray(Constants.API_ARRAY_NAME);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject countryObject = jsonArray.getJSONObject(i);

                    Country country = new Country();
                    final String flagUrl =
                            (Constants.API_FLAG_URL +
                                    countryObject.getString(Constants.FIELD_COUNTRY_FLAG) +
                                    Constants.FLAG_EXTENSION).toLowerCase();
                    final String mapUrl =
                            Constants.API_MAP_URL +
                                    countryObject.getString(Constants.FIELD_COUNTRY_MAP).toUpperCase() +
                                    Constants.MAP_EXTENSION;

                    country.setCountryCode(countryObject.getString(Constants.FIELD_COUNTRY_CODE));
                    country.setFlag(flagUrl);
                    country.setCapital(countryObject.getString(Constants.FIELD_COUNTRY_CAPITAL));
                    country.setCountryName(countryObject.getString(Constants.FIELD_COUNTRY_NAME));
                    country.setPopulation(Integer.parseInt(countryObject.getString(Constants.FIELD_COUNTRY_POPULATION)));
                    country.setAreaInSqKm(Float.parseFloat(countryObject.getString(Constants.FIELD_COUNTRY_AREAINSQKM)));
                    country.setMap(mapUrl);

                    Log.e("Loading", country.toString());

                    this.country = country;
                    listCountries.add(country);
                }
            } catch (Exception e) {
                Log.e("Error API", e.toString());
            }
            return listCountries;
        }
    }

}