
package Entity;

import java.sql.Date;

public class Bill {
    int bill_id;
    User user;
    String user1;
    Float total;
    String payment;
    String address;
    Date date;
    String phone;
    String status;
    String bill_status;

    public Bill() {
    }

    public Bill(int bill_id, User user, Float total, String payment, String address, Date date, String phone) {
        this.bill_id = bill_id;
        this.user = user;
        this.total = total;
        this.payment = payment;
        this.address = address;
        this.date = date;
        this.phone = phone;
    }

    public Bill(int bill_id, String user1, Float total, String payment, String address, Date date, String phone) {
        this.bill_id = bill_id;
        this.user1 = user1;
        this.total = total;
        this.payment = payment;
        this.address = address;
        this.date = date;
        this.phone = phone;
    }

    public String getUser1() {
        return user1;
    }

    public void setUser1(String user1) {
        this.user1 = user1;
    }
    
    
    public Bill(int bill_id,Float total,Date date) {
        this.bill_id = bill_id;
        this.total = total;
        this.date = date;
    }
    
    public Bill(int bill_id, Float total, String payment, String address, Date date, String phone) {
        this.bill_id = bill_id;
        this.total = total;
        this.payment = payment;
        this.address = address;
        this.date = date;
        this.phone = phone;
    }
    
    
        public Bill(int bill_id, Date date, String payment, String address, float totalMoney) {
        this.total = totalMoney;
        this.payment = payment;
        this.address = address;
        this.date = date;
        this.bill_id = bill_id;
    }

    public Bill(int bill_id, Float total, String payment, String address, Date date, String phone, String bill_status) {
        this.bill_id = bill_id;
        this.total = total;
        this.payment = payment;
        this.address = address;
        this.date = date;
        this.phone = phone;
        this.bill_status = bill_status;
    }
        
        
        public Bill(int bill_id, Date date, String payment, String address, float totalMoney, String bill_status){
       this.bill_id = bill_id;
        this.date = date;
        this.payment = payment;
        this.address = address;
        this.total = totalMoney;
        this.bill_status = bill_status;

    }

    public int getBill_id() {
        return bill_id;
    }

    public void setBill_id(int bill_id) {
        this.bill_id = bill_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBill_status() {
        return bill_status;
    }

    public void setBill_status(String bill_status) {
        this.bill_status = bill_status;
    }

    

    

    
    
    
    
}
