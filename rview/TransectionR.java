package com.example.edithapp.rview;

public class TransectionR {
    private int image;
    private String category;
    private float amount;
    private String date;
    public TransectionR(){}

    public TransectionR(int image, String category, float amount, String date) {
        this.image = image;
        this.category = category;
        this.amount = amount;
        this.date = date;
    }


    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "TransectionR{" +
                "image=" + image +
                ", category='" + category + '\'' +
                ", amount='" + amount + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
