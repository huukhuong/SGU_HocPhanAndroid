package com.example.baitap3_c2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Button btnSubmit;
    private CheckBox ckbColor, ckbBold;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        addEvents();
    }

    private void addControls() {
        editText = findViewById(R.id.editText);
        btnSubmit = findViewById(R.id.btnSubmit);
        ckbColor = findViewById(R.id.ckbColor);
        ckbBold = findViewById(R.id.ckbBold);
    }

    private void addEvents() {
        btnSubmit.setOnClickListener(v -> {
            count++;
            editText.setText("You've clicked " + count + " times");
        });

        ckbColor.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            if (isChecked) {
                editText.setTextColor(Color.BLUE);
            } else {
                editText.setTextColor(Color.BLACK);
            }
        });

        ckbBold.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            if (isChecked) {
                editText.setTypeface(null, Typeface.BOLD);
            } else {
                editText.setTypeface(null, Typeface.NORMAL);
            }
        });
    }
}