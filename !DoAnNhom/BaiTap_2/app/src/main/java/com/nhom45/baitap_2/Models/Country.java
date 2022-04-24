package com.nhom45.baitap_2.Models;

import java.io.Serializable;

public class Country  implements Serializable {

    private String countryCode;
    private String capital;
    private String flag;
    private String countryName;
    private int population;
    private float areaInSqKm;
    private String map;

    public Country() {
    }

    public Country(String countryCode, String capital, String flag, String countryName, int population, float areaInSqKm, String map) {
        this.countryCode = countryCode;
        this.capital = capital;
        this.flag = flag;
        this.countryName = countryName;
        this.population = population;
        this.areaInSqKm = areaInSqKm;
        this.map = map;
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

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return countryCode + "||" + countryName + "||" + population + "||" + areaInSqKm + "||" + flag;
    }
}
