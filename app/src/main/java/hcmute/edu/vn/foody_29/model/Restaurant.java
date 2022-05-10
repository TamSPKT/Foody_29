package hcmute.edu.vn.foody_29.model;

import hcmute.edu.vn.foody_29.constant.FoodyConstant;

public class Restaurant {
    private int id;
    private String name;
    private int imageSrc;

    public Restaurant(int id, String name, int imageSrc) {
        this.id = id;
        this.name = name;
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

    public int getImageSrc() {
        return FoodyConstant.getRestaurantImage(id);
    }

    public void setImageSrc(int imageSrc) {
        this.imageSrc = imageSrc;
    }
}
