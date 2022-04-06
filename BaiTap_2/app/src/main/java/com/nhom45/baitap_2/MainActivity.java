package com.nhom45.baitap_2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.nhom45.baitap_2.Adapters.AdapterCountry;
import com.nhom45.baitap_2.Models.Country;
import com.nhom45.baitap_2.Ultils.Constants;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private AdapterCountry adapterCountry;
    private ArrayList<Country> countryArrayList;
    private ListView lsvCountries;

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
        lsvCountries = findViewById(R.id.lsvCountries);
        adapterCountry = new AdapterCountry(this, R.layout.item_country, countryArrayList);
        lsvCountries.setAdapter(adapterCountry);
    }

    private void addEvents() {

    }

    class GetCountryAPI extends AsyncTask<Void, Void, ArrayList<Country>> {

        private Country country;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            countryArrayList.clear();
        }

        @Override
        protected void onPostExecute(ArrayList<Country> listCountries) {
            super.onPostExecute(listCountries);
            countryArrayList.addAll(listCountries);
            adapterCountry.notifyDataSetChanged();
            Log.e("Start", "Data get successfully");
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            countryArrayList.add(country);
            adapterCountry.notifyDataSetChanged();
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
                                    Constants.IMAGE_EXTENSION).toLowerCase();

                    country.setCountryCode(countryObject.getString(Constants.FIELD_COUNTRY_CODE));
                    country.setFlag(flagUrl);
                    country.setCountryName(countryObject.getString(Constants.FIELD_COUNTRY_NAME));
                    country.setPopulation(Integer.parseInt(countryObject.getString(Constants.FIELD_COUNTRY_POPULATION)));
                    country.setAreaInSqKm(Float.parseFloat(countryObject.getString(Constants.FIELD_COUNTRY_AREAINSQKM)));

                    Log.e("Loading", country.toString());

                    url = new URL(flagUrl);
                    connection = (HttpURLConnection) url.openConnection();
                    Bitmap bitmap = BitmapFactory.decodeStream(connection.getInputStream());

                    country.setFlagBitmap(bitmap);

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