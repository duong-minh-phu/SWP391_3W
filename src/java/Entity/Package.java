/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author huybao
 */
public class Package {
    private int Id;
    private String description;
    private String name;
    private int price;
    private int quantity;
    private String img;
    private int status;
    private int delivery_date;
    
    public Package() {   
    }

    public Package(String description, String name, int price, int quantity, String img, int status, int delivery_date) {
        this.description = description;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.img = img;
        this.status = status;
        this.delivery_date = delivery_date;
    }

    public int getId() {
        return Id;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
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
    
    
}