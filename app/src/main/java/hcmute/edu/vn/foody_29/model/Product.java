package hcmute.edu.vn.foody_29.model;

import hcmute.edu.vn.foody_29.constant.FoodyConstant;

public class Product {
    private int id;
    private String name;
    private int cost;
    private int imageSrc;

    public Product(int id, String name, int cost, int imageSrc) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.imageSrc = imageSrc;
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

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getImageSrc() {
        return FoodyConstant.getProductImage(id);
    }

    public void setImageSrc(int imageSrc) {
        this.imageSrc = imageSrc;
    }
}
