package com.example.myproyect_2022;

import java.util.ArrayList;

public class Comic {
    String img;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Comic(String img) {
        this.img = img;
    }

    public static ArrayList<Comic> listComics = new ArrayList<Comic>();

}
