package com.nhom45.baitap_3.Ultils;

import android.annotation.SuppressLint;

public class Helpers {

    @SuppressLint("DefaultLocale")
    public static String formatDecimal(float number) {
        float epsilon = 0.004f;
        if (Math.abs(Math.round(number) - number) < epsilon) {
            return String.format("%10.0f", number);
        } else {
            return String.format("%10.2f", number);
        }
    }

}
