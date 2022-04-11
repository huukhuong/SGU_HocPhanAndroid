package com.example.baitap3_c3;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private LinearLayout lnlRoot;
    private RadioButton rdbRed, rdbGreen, rdbBlue, rdbGray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        addEvents();
    }

    private void addControls() {
        lnlRoot = findViewById(R.id.lnlRoot);
        rdbRed = findViewById(R.id.rdbRed);
        rdbGreen = findViewById(R.id.rdbGreen);
        rdbBlue = findViewById(R.id.rdbBlue);
        rdbGray = findViewById(R.id.rdbGray);
    }

    private void addEvents() {
        rdbRed.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            if (isChecked) {
                lnlRoot.setBackgroundColor(Color.RED);
            }
        });
        rdbGreen.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            if (isChecked) {
                lnlRoot.setBackgroundColor(Color.GREEN);
            }
        });
        rdbBlue.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            if (isChecked) {
                lnlRoot.setBackgroundColor(Color.BLUE);
            }
        });
        rdbGray.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            if (isChecked) {
                lnlRoot.setBackgroundColor(Color.GRAY);
            }
        });
    }

}