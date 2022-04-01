package com.nhom45.baitap_4.Models;

import java.io.Serializable;

public class Image implements Serializable {

    private String name;
    private String path;

    public Image() {
    }

    public Image(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
