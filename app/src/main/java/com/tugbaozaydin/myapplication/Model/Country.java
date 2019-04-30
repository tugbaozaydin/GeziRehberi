package com.tugbaozaydin.myapplication.Model;

/**
 * Created by TugbaOzaydin on 3.03.2019.
 */

public class Country {
    private int id ;
    private String  name;
    private String image;
    private String text;

    public Country() {
    }

    public Country(int id, String name, String image, String text) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
