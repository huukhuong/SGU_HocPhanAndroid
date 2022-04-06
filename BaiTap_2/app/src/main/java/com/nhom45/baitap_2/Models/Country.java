package com.nhom45.baitap_2.Models;

import android.graphics.Bitmap;

public class Country {

    private String countryCode;
    private String flag;
    private String countryName;
    private int population;
    private float areaInSqKm;
    private Bitmap flagBitmap;

    public Country() {
    }

    public Country(String countryCode, String flag, String countryName, int population, float areaInSqKm, Bitmap flagBitmap) {
        this.countryCode = countryCode;
        this.flag = flag;
        this.countryName = countryName;
        this.population = population;
        this.areaInSqKm = areaInSqKm;
        this.flagBitmap = flagBitmap;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public float getAreaInSqKm() {
        return areaInSqKm;
    }

    public void setAreaInSqKm(float areaInSqKm) {
        this.areaInSqKm = areaInSqKm;
    }

    public Bitmap getFlagBitmap() {
        return flagBitmap;
    }

    public void setFlagBitmap(Bitmap flagBitmap) {
        this.flagBitmap = flagBitmap;
    }

    @Override
    public String toString() {
        return countryCode + "||" + countryName + "||" + population + "||" + areaInSqKm + "||" + flag;
    }
}
