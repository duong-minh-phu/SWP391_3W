/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.ArrayList;

/**
 *
 * @author ROG STRIX
 */
public class Category {
    int category_id;
    String category_name;
    int product_count;
    private ArrayList<Product> product = new ArrayList<>();

    public Category() {
    }

    public Category(int category_id, String category_name) {
        this.category_id = category_id;
        this.category_name = category_name;
    }

    public Category(int category_id, String category_name, int product_count) {
        this.category_id = category_id;
        this.category_name = category_name;
        this.product_count = product_count;
    }

    public int getProduct_count() {
        return product_count;
    }

    public void setProduct_count(int product_count) {
        this.product_count = product_count;
    }
    public Category(String category_name) {
        this.category_name = category_name;
    }
    
    public Category(int category_id) {
       this.category_id = category_id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public ArrayList<Product> getProduct() {
        return product;
    }

    public void setProduct(ArrayList<Product> product) {
        this.product = product;
    }

    
}
