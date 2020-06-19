package com.example.dammobile;

import android.media.Image;

import java.io.Serializable;
import java.util.ArrayList;

public class Asset implements Serializable {
    private String name;
    private String type;
    private ArrayList <String> products;
    private String description;
    private Image img;
    private String imageURL;

    public Asset(Image img, String name){
        this.type = "";
        this.description = "";
        this.products = new ArrayList<>();
        this.img = img;
        this.name = name;
    }

    public Asset(String name, String imageURL){
        this.type = "";
        this.description = description;
        this.imageURL = imageURL;
        this.products = new ArrayList<>();
        this.name = name;
    }


    public String getType(){
        return type;
    }

    public void setType(String newType){
        this.type = newType;
    }

    public ArrayList<String> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<String> products) {

        this.products = products;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Image getImg(){
        return img;
    }

    public String getName(){
        return this.name;
    }

    public String getImageURL(){
        return this.imageURL;
    }
    public void setImageURL(String newImageURL){
        this.imageURL = newImageURL;
    }
}
