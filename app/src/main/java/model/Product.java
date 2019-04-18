package model;

import java.io.Serializable;

public class Product implements Serializable {
    private String pictureName;
    private String productName;
    private int cost;
    private Float fees;


    public String getPictureName() {
        return pictureName;
    }

    public void setPictureName(String pictureName) {
        this.pictureName = pictureName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Float getFees() {
        return fees;
    }

    public void setFees(Float fees) {
        this.fees = fees;
    }


    public Product(String pictureName, String productName, int cost, Float fees) {
        this.pictureName = pictureName;
        this.productName = productName;
        this.cost = cost;
        this.fees = fees;
    }

    @Override
    public String toString() {
        return "Product{" +
                "pictureName='" + pictureName + '\'' +
                ", productName='" + productName + '\'' +
                ", cost=" + cost +
                ", fees=" + fees +
                '}';
    }
}
