package com.nhom45.baitap_3.Models;

public class Currency {

    private String countryCode;
    private String currencyCode;
    private String flag;

    public Currency() {
    }

    public Currency(String countryCode, String currencyCode, String flag) {
        this.countryCode = countryCode;
        this.currencyCode = currencyCode;
        this.flag = flag;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "countryCode='" + countryCode + '\'' +
                ", currencyCode='" + currencyCode + '\'' +
                '}';
    }
}
