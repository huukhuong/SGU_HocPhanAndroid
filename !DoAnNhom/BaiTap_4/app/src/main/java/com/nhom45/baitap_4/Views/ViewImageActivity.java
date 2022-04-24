package com.nhom45.baitap_4.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.github.chrisbanes.photoview.PhotoView;
import com.nhom45.baitap_4.Models.Image;
import com.nhom45.baitap_4.R;
import com.nhom45.baitap_4.Ultils.Helpers;

public class ViewImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_image);

        Intent intent = getIntent();
        Image imageSelected = (Image) intent.getSerializableExtra("image");
        PhotoView imgView = findViewById(R.id.imgView);
        imgView.setImageBitmap(Helpers.getImageFromPath(imageSelected.getPath()));
    }
}