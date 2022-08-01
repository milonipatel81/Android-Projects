package com.example.edithapp.rview;

public class Category {
    private int image;
    public Category(){}
    public Category(int image,String name){
        this.image = image;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "image=" + image +
                ", name='" + name + '\'' +
                '}';
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private  String name;

}
