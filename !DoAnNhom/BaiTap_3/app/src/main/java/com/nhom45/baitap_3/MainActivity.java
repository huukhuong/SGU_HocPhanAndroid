package com.nhom45.baitap_3;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button btnNum1, btnNum2, btnNum3, btnNum4, btnNum5, btnNum6, btnNum7, btnNum8, btnNum9, btnNum0, btnNumClear;
    private ImageButton btnNumBackspace;
    private ArrayList<Button> arrayButtonNumPad;
    private TextView txtInput, txtOutput;
    private String inputValue = "0";

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
        arrayButtonNumPad.add(btnNumClear);

        txtInput.setText(inputValue);
        txtOutput.setText(inputValue);
    }

    private void addEvents() {
        for (Button btn : arrayButtonNumPad) {
            btn.setOnClickListener(e -> {
                setNumpadClick(btn);
            });
        }
        btnNumBackspace.setOnClickListener(e -> {
            if (inputValue.length() > 0) {
                inputValue = inputValue.substring(0, inputValue.length() - 1);
            } else {
                inputValue = "0";
            }
            txtInput.setText(inputValue);
        });
    }

    @SuppressLint("NonConstantResourceId")
    private void setNumpadClick(Button btn) {
        if (inputValue.equals("0")) {
            inputValue = "";
        }
        if (inputValue.length() > 10) {
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
            case R.id.btnNumClear:
                inputValue = "";
        }
        txtInput.setText(inputValue);
    }
}