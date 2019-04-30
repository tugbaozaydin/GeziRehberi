package com.tugbaozaydin.myapplication.Model;

import java.io.Serializable;

/**
 * Created by TugbaOzaydin on 11.03.2019.
 */

public class CardViewObject implements Serializable {
    private  int id;
    private  String name;
    private  String image;
    private String location;
    private int countryId;

    public CardViewObject() {
    }

    public CardViewObject(int id, String name, String image, String location, int countryId) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.location = location;
        this.countryId = countryId;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }
}
