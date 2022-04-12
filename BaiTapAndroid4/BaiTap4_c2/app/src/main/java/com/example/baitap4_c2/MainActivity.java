package com.example.baitap4_c2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnGoogle, btnCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        addEvents();
    }

    private void addControls() {
        btnGoogle = findViewById(R.id.btnGoogle);
        btnCall = findViewById(R.id.btnCall);
    }

    private void addEvents() {
        btnGoogle.setOnClickListener(v -> {
            Uri uri = Uri.parse("https://www.google.com");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        });

        btnCall.setOnClickListener(v -> {
            if (checkPermission()) {
                Uri uri = Uri.parse("tel:(+84)786506577");
                Intent intent = new Intent(Intent.ACTION_CALL, uri);
                startActivity(intent);
            }
        });
    }

    public boolean checkPermission() {
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    006);
            return false;
        } else {
            return true;
        }
    }
}