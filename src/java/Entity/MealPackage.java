/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author huybao
 */
public class MealPackage {
    private int id;
    private String description;
    private String name;
    private Float price;
    private int quantity;
    private String img;
    private int status;
    private int delivery_date;
    private String company;
    private String size;
    private  String expiry;
    
    public MealPackage() {   
    }

    public MealPackage(String description, String name, Float price, int quantity, String img, int status, int delivery_date) {
        this.description = description;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.img = img;
        this.status = status;
        this.delivery_date = delivery_date;
    }

    public MealPackage(int Id, String description, String name, Float price, int quantity, String img, int status, int delivery_date) {
        this.id = Id;
        this.description = description;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.img = img;
        this.status = status;
        this.delivery_date = delivery_date;
    }

    public MealPackage(int id, String description, String name, Float price, int quantity, String img, int status, int delivery_date, String company, String size, String expiry) {
        this.id = id;
        this.description = description;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.img = img;
        this.status = status;
        this.delivery_date = delivery_date;
        this.company = company;
        this.size = size;
        this.expiry = expiry;
    }

    
    

    

    public int getId() {
        return id;
    }
    
    public void setId(int Id) {
        this.id = Id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getDelivery_date() {
        return delivery_date;
    }

    public void setDelivery_date(int delivery_date) {
        this.delivery_date = delivery_date;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getExpiry() {
        return expiry;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }
    
    
    
}