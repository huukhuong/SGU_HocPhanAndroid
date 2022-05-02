package com.nhom45.baitap_3;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.nhom45.baitap_3.Adapters.AdapterSpiner;
import com.nhom45.baitap_3.Models.Currency;
import com.nhom45.baitap_3.Ultils.Constants;
import com.nhom45.baitap_3.Ultils.XMLDOMParser;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button btnNum1, btnNum2, btnNum3, btnNum4, btnNum5, btnNum6, btnNum7, btnNum8, btnNum9, btnNum0, btnNumClear;
    private ImageButton btnReverse;
    private ImageButton btnNumBackspace;
    private ArrayList<Button> arrayButtonNumPad;
    private TextView txtInput, txtOutput;
    private String inputValue = "0";
    private Spinner spnFrom, spnTo;
    private ArrayList<Currency> currenciesCodes;
    private AdapterSpiner adapterSpiner;
    private ProgressDialog loadingDialog;
    private String codeTo, codeFrom;
    private double valueFrom = 1, valueTo = 1;
    private final int maxLength = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        addEvents();
    }

    private void addControls() {
        txtInput = findViewById(R.id.txtInput);
        txtOutput = findViewById(R.id.txtOutput);

        btnNum1 = findViewById(R.id.btnNum1);
        btnNum2 = findViewById(R.id.btnNum2);
        btnNum3 = findViewById(R.id.btnNum3);
        btnNum4 = findViewById(R.id.btnNum4);
        btnNum5 = findViewById(R.id.btnNum5);
        btnNum6 = findViewById(R.id.btnNum6);
        btnNum7 = findViewById(R.id.btnNum7);
        btnNum8 = findViewById(R.id.btnNum8);
        btnNum9 = findViewById(R.id.btnNum9);
        btnNum0 = findViewById(R.id.btnNum0);
        btnNumClear = findViewById(R.id.btnNumClear);
        btnNumBackspace = findViewById(R.id.btnNumBackspace);

        btnReverse = findViewById(R.id.btnReverse);

        arrayButtonNumPad = new ArrayList<>();
        arrayButtonNumPad.add(btnNum1);
        arrayButtonNumPad.add(btnNum2);
        arrayButtonNumPad.add(btnNum3);
        arrayButtonNumPad.add(btnNum4);
        arrayButtonNumPad.add(btnNum5);
        arrayButtonNumPad.add(btnNum6);
        arrayButtonNumPad.add(btnNum7);
        arrayButtonNumPad.add(btnNum8);
        arrayButtonNumPad.add(btnNum9);
        arrayButtonNumPad.add(btnNum0);

        txtInput.setText(inputValue);
        txtOutput.setText(inputValue);

        spnFrom = findViewById(R.id.spnFrom);
        spnTo = findViewById(R.id.spnTo);

        currenciesCodes = new ArrayList<>();
        adapterSpiner = new AdapterSpiner(this, R.layout.item_currency_selected, currenciesCodes);

        spnFrom.setAdapter(adapterSpiner);
        spnTo.setAdapter(adapterSpiner);

        GetAllCurrencyTask getAllCurrencyTask = new GetAllCurrencyTask();
        getAllCurrencyTask.execute();
    }

    private void addEvents() {
        for (Button btn : arrayButtonNumPad) {
            btn.setOnClickListener(e -> {
                setNumpadClick(btn);
            });
        }

        btnNumBackspace.setOnClickListener(e -> {
            if (inputValue.equals("0")) {
                return;
            } else if (inputValue.length() > 1) {
                inputValue = inputValue.substring(0, inputValue.length() - 1);
            } else {
                inputValue = "0";
            }
            DecimalFormat dcf = new DecimalFormat("###,###,###.##");
            txtInput.setText(dcf.format(Double.parseDouble(inputValue)));
            calculating();
        });

        btnNumClear.setOnClickListener(e -> {
            resetAll();
            calculating();
        });

        btnReverse.setOnClickListener(e -> {
            reverseButton();
        });

        spnTo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                changeCurrencySpinner();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        spnFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                changeCurrencySpinner();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    private void reverseButton() {
        resetAll();

        String tmp = codeTo;
        codeTo = codeFrom;
        codeFrom = tmp;

        int positionFrom = spnFrom.getSelectedItemPosition();
        int positionTo = spnTo.getSelectedItemPosition();

        spnFrom.setSelection(positionTo);
        spnTo.setSelection(positionFrom);

        if (!codeFrom.equals(codeTo)) {
            GetCurrencyExchangeTask task = new GetCurrencyExchangeTask();
            task.execute();
        }

    }

    private void resetAll() {
        txtOutput.setText("0");
        txtInput.setText("0");
        inputValue = "0";
    }

    @SuppressLint("NonConstantResourceId")
    private void setNumpadClick(Button btn) {
        if (inputValue.equals("0")) {
            inputValue = "";
        }
        if (inputValue.length() > maxLength) {
            return;
        }
        switch (btn.getId()) {
            case R.id.btnNum0:
                inputValue += "0";
                break;
            case R.id.btnNum1:
                inputValue += "1";
                break;
            case R.id.btnNum2:
                inputValue += "2";
                break;
            case R.id.btnNum3:
                inputValue += "3";
                break;
            case R.id.btnNum4:
                inputValue += "4";
                break;
            case R.id.btnNum5:
                inputValue += "5";
                break;
            case R.id.btnNum6:
                inputValue += "6";
                break;
            case R.id.btnNum7:
                inputValue += "7";
                break;
            case R.id.btnNum8:
                inputValue += "8";
                break;
            case R.id.btnNum9:
                inputValue += "9";
                break;
        }
        DecimalFormat dcf = new DecimalFormat("###,###,###.##");
        txtInput.setText(dcf.format(Double.parseDouble(inputValue)));
        calculating();
    }

    private void changeCurrencySpinner() {
        resetAll();

        Currency currencyFrom = (Currency) spnFrom.getSelectedItem();
        Currency currencyTo = (Currency) spnTo.getSelectedItem();

        if (!currencyFrom.getCurrencyCode().equals(codeFrom) ||
                !currencyTo.getCurrencyCode().equals(codeTo)) {
            codeFrom = currencyFrom.getCurrencyCode();
            codeTo = currencyTo.getCurrencyCode();
            Log.e("CODE", codeFrom + " || " + codeTo);
            if (!codeFrom.equals(codeTo)) {
                GetCurrencyExchangeTask task = new GetCurrencyExchangeTask();
                task.execute();
            } else {
                valueFrom = valueTo = 1;
            }
        }
    }

    private void calculating() {
        double result = valueTo * Double.parseDouble(inputValue);
        DecimalFormat dcf = new DecimalFormat("###,###,###.###");
        txtOutput.setText(dcf.format(result));
        Log.e("CALCULATING", valueFrom + " || " + valueTo + " || " + inputValue + " || " + result);
    }

    class GetAllCurrencyTask extends AsyncTask<Void, Void, ArrayList<Currency>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loadingDialog = new ProgressDialog(MainActivity.this);
            loadingDialog.setTitle("Please wait");
            loadingDialog.setMessage("Loading data, please wait");
            loadingDialog.setCanceledOnTouchOutside(false);
            loadingDialog.show();
        }

        @Override
        protected void onPostExecute(ArrayList<Currency> currencies) {
            super.onPostExecute(currencies);

            currenciesCodes.clear();
            for (Currency currency : currencies) {
                if (!currency.getCurrencyCode().isEmpty()) {
                    currenciesCodes.add(currency);
                }
            }
            adapterSpiner.notifyDataSetChanged();

            loadingDialog.dismiss();
        }

        @Override
        protected ArrayList doInBackground(Void... voids) {
            ArrayList<Currency> currencies = new ArrayList<>();
            try {
                URL url = new URL(Constants.API_URL);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream(), "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                StringBuilder builder = new StringBuilder();
                String line = bufferedReader.readLine();
                while (line != null) {
                    builder.append(line);
                    line = bufferedReader.readLine();
                }
                JSONObject result = new JSONObject(builder.toString());
                JSONArray jsonArray = result.getJSONArray("geonames");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Currency currency = new Currency();
                    currency.setCurrencyCode(jsonObject.getString(Constants.FIELD_CURRENCY_CODE));
                    currency.setCountryCode(jsonObject.getString(Constants.FIELD_COUNTRY_CODE));
                    final String flagUrl = (Constants.API_FLAG_URL +
                            currency.getCountryCode() +
                            Constants.FLAG_EXTENSION).toLowerCase();
                    currency.setFlag(flagUrl);
                    currencies.add(currency);
                }
            } catch (Exception ex) {
                Log.e("ERR_API", ex.toString());
            }
            return currencies;
        }
    }

    class GetCurrencyExchangeTask extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loadingDialog = new ProgressDialog(MainActivity.this);
            loadingDialog.setTitle("Please wait");
            loadingDialog.setMessage("Loading data, please wait");
            loadingDialog.setCanceledOnTouchOutside(false);
            loadingDialog.show();
        }

        @Override
        protected String doInBackground(Void... voids) {
            StringBuilder content = new StringBuilder();
            try {
                String URL = "https://" + codeFrom + ".fxexchangerate.com/" + codeTo + ".xml";
                URL url = new URL(URL.toLowerCase());
                InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    content.append(line);
                }
                bufferedReader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return content.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            loadingDialog.dismiss();
            Log.e("VALUE_XML", s);

            XMLDOMParser parser = new XMLDOMParser();
            Document document = parser.getDocument(s);
            NodeList nodeList = document.getElementsByTagName("item");
            String tygia = "";
            for (int i = 0; i < 1; i++) {
                Element element = (Element) nodeList.item(i);
                NodeList DescriptionNode = element.getElementsByTagName("description");
                Element DescriptionEle = (Element) DescriptionNode.item(i);
                tygia = Html.fromHtml(DescriptionEle.getFirstChild().getNodeValue().trim()).toString();
                Log.e("tygia", tygia);
            }
            String[] arr = tygia.split("\n");
            String currency = arr[0];
            currency = currency.replace(codeFrom, "");
            currency = currency.replace(codeTo, "");
            // tách ra 2 giá trị
            String[] arrcurency = currency.split("=");
            valueFrom = Double.parseDouble(arrcurency[0].trim());
            valueTo = Double.parseDouble(arrcurency[1].trim());

            if (loadingDialog.isShowing()) {
                loadingDialog.dismiss();
            }
        }
    }
}