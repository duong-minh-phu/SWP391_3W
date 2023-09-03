/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 *
 * @author HoangPhatNguyen
 */
public class MealsByPackage {
    private String id;
    private String describe;
    private float productPrice;
    private float price;
    private String img;
    private String categoryName;
    private String productName;
    private int promotion;
    private String quantity;

    public MealsByPackage() {
    }


    public MealsByPackage(String id, String describe, float productPrice, float price, String img, String categoryName, String productName, int promotion, String quantity) {
        this.id = id;
        this.describe = describe;
        this.productPrice = productPrice;
        this.price = price;
        this.img = img;
        this.categoryName = categoryName;
        this.productName = productName;
        this.promotion = promotion;
        this.quantity = quantity;
    }
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }

    public float getPrice() {
        return price;
    }
    
    public void setPrice(float price) {
        this.price = price;
    }
    
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getPromotion() {
        return promotion;
    }

    public void setPromotion(int promotion) {
        this.promotion = promotion;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
    
    
    
}
