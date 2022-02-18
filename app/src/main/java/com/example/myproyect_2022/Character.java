package com.example.myproyect_2022;

import java.util.ArrayList;
import java.util.List;

public class Character {
    private int id;
    private String name;
    private String description;
    private String modified;
    private String imgURL;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getModified() {
        return modified;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public Character(int id, String name, String description, String modified, String imgURL) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.modified = modified;
        this.imgURL = imgURL;
    }

    @Override
    public String toString() {
        return "Character{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", modified='" + modified + '\'' +
                ", imgURL='" + imgURL + '\'' +
                '}';
    }

    public static ArrayList<Character> listCharacter = new ArrayList<Character>();
}
