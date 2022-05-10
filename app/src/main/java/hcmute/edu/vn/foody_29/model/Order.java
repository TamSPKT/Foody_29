package hcmute.edu.vn.foody_29.model;

import hcmute.edu.vn.foody_29.constant.FoodyConstant;

public class Order {
    private int id;
    private int userId;
    private int productId;
    private int quantity;
    private String address;
    private String phoneNumber;
    private String productName;
    private int imageSrc;
    private int totalCost;
    private String date;

    public Order(int id, int userId, int productId, int quantity, String address, String phoneNumber, String productName, int imageSrc, int totalCost, String date) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.productName = productName;
        this.imageSrc = imageSrc;
        this.totalCost = totalCost;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getImageSrc() {
        return FoodyConstant.getProductImage(productId);
    }

    public void setImageSrc(int imageSrc) {
        this.imageSrc = imageSrc;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
