package com.nhom45.baitap_1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.slider.Slider;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends AppCompatActivity {

    private Slider slider;
    private View viewArray[];
    private int colorArray[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        addEvents();
    }

    private void addControls() {
        slider = findViewById(R.id.slider);
        viewArray = new View[]{
                findViewById(R.id.thumb_1),
                findViewById(R.id.thumb_2),
                findViewById(R.id.thumb_3),
                findViewById(R.id.thumb_4),
                findViewById(R.id.thumb_5)
        };
        colorArray = new int[]{
                -12264309,
                -103895213,
                -1020018,
                -4625080,
                -213686123,
                -19256,
                -436278
        };
        Random rnd = new Random();
        for (int i = colorArray.length - 1; i > 0; i--) {
            int index = rnd.nextInt(colorArray.length - 1);
            int a = colorArray[index];
            colorArray[index] = colorArray[i];
            colorArray[i] = a;
        }
        for (int i = 0; i < viewArray.length; i++) {
            viewArray[i].setBackgroundColor(colorArray[i]);
            Log.e("COLOR", colorArray[i] + "");
        }
    }

    private void addEvents() {
        slider.addOnChangeListener((slider, value, fromUser) -> {
            int increment = (int) value;
            for (int i = 0; i < viewArray.length; i++) {
                viewArray[i].setBackgroundColor(colorArray[i] + increment);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_more_infomation:
                showAlertDialog();
                break;
        }
        return true;
    }

    private void showAlertDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();

        @SuppressLint("ResourceType")
        View dialogView = inflater.inflate(
                R.layout.dialog,
                findViewById(R.layout.activity_main));

        Button btnVisit, btnNotNow;

        btnVisit = dialogView.findViewById(R.id.btn_visit);
        btnNotNow = dialogView.findViewById(R.id.btn_notNow);

        dialogBuilder.setView(dialogView);
        AlertDialog b = dialogBuilder.create();
        b.show();

        btnVisit.setOnClickListener(e -> {
            Uri uri = Uri.parse("http://www.moma.org");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
            b.dismiss();
        });

        btnNotNow.setOnClickListener(e -> {
            b.dismiss();
        });
    }
}