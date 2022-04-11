package com.nhom45.baitap_4.Ultils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.nhom45.baitap_4.R;

import java.io.File;

public class Helpers {

    public static Bitmap getImageFromPath(String path) {
        File imgFile = new File(path);
        if (imgFile.exists()) {
            return BitmapFactory.decodeFile(imgFile.getAbsolutePath());
        }
        return BitmapFactory.decodeFile(String.valueOf(R.drawable.img));
    }

}
