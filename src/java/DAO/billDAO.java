/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Context.DBContext;
import Entity.Bill;
import Entity.BillDetail;
import Entity.Cart;
import Entity.Category;
import Entity.Product;
import Entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ngodi
 */
public class billDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public void addOrder(User u, float cartTotal, String payment, String address, String date, String phone) throws Exception {
        try {
            String sql = "INSERT INTO bill (user_id, total_money, payment, address, date, phone, delivery_status,bill_status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, u.getUser_id());
            ps.setFloat(2, cartTotal);
            ps.setString(3, payment);
            ps.setString(4, address);
            ps.setString(5, date);
            ps.setString(6, phone);
            ps.setBoolean(7, false);
            ps.setString(8, "xac nhan don");
            ps.executeUpdate();

            String sql1 = "SELECT TOP 1 bill_id FROM bill ORDER BY bill_id DESC";
            ps = conn.prepareStatement(sql1);
            rs = ps.executeQuery();

            int billId = 0;
            if (rs.next()) {
                billId = rs.getInt("bill_id");

                Cart cart = u.getCart();
                Map<Product, Integer> products = cart.getItems();
                System.out.println(products.entrySet().size());
                for (Map.Entry<Product, Integer> entry : products.entrySet()) {
                    Product product = entry.getKey();
                    int quantity = entry.getValue();
                    String sql2 = "INSERT INTO bill_detail (bill_id, product_id, quantity, product_total, delivery_status) VALUES (?, ?, ?, ?, ?)";
                    ps = conn.prepareStatement(sql2);
                    double productTotal = product.getProduct_price() * quantity;
                    ps.setInt(1, billId);
                    ps.setString(2, product.getProduct_id());
                    ps.setInt(3, quantity);
                    ps.setDouble(4, productTotal);
                    ps.setBoolean(5, false);
                    ps.executeUpdate();
                }

            }
            String sql3 = "UPDATE product SET quantity = quantity - ? WHERE product_id = ?";
            ps = conn.prepareStatement(sql3);
            Cart cart = u.getCart();
            Map<Product, Integer> products = cart.getItems();
            for (Map.Entry<Product, Integer> entry : products.entrySet()) {
                Product product = entry.getKey();
                int quantity = entry.getValue();
                ps.setInt(1, quantity);
                ps.setString(2, product.getProduct_id());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatebilldely(int id) {
        LocalDate curDate = java.time.LocalDate.now();
        String date = curDate.toString();
        try {
            String query = "insert into billstatus values(?,?,?)";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ps.setString(2, "xac nhan don");
            ps.setString(3, date);
            ps.executeUpdate();
        } catch (Exception ex) {

        };
    }

    public List<Bill> getBillInfo() {
        List<Bill> list = new ArrayList<>();
        String sql = "select b.bill_id, u.user_name,b.phone,b.address,b.date,b.total_money,b.payment from bill b inner join users u on b.user_id = u.user_id";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                User u = new User(rs.getString(2));
                list.add(new Bill(rs.getInt(1), u, rs.getFloat(6), rs.getString(7), rs.getString(4), rs.getDate(5), rs.getString(3)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<BillDetail> getDetail(int bill_id) {
        List<BillDetail> list = new ArrayList<>();
        String sql = "SELECT d.bill_detail_id, p.product_id, p.product_name, p.img, d.quantity, d.product_total,d.bill_id \n"
                + "FROM bill_detail d\n" + "INNER JOIN product p ON d.product_id = p.product_id\n" + "WHERE d.bill_id = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, bill_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new BillDetail(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getFloat(6), rs.getInt(7)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Bill> getBillByID(int user_id) {
        List<Bill> list = new ArrayList<>();
        String sql = "select b.bill_id, b.date,b.total,b.payment, b.address, b.phone from bill b where user_id = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, user_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Bill(rs.getInt(1), rs.getFloat(3), rs.getString(4), rs.getString(5), rs.getDate(2), rs.getString(6)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Bill> getBill() {
        List<Bill> list = new ArrayList<>();
        String sql = "select p.bill_id,c.user_name,p.total_money,p.payment,p.address,p.date,p.phone\n"
                + "from bill p inner join users c on p.user_id=c.user_id and p.delivery_status='False' order by date desc ";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Bill(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getString(4), rs.getString(5), rs.getDate(6), rs.getString(7)));
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Bill> getBill1() {
        List<Bill> list = new ArrayList<>();
        String sql = "select p.bill_id,c.user_name,p.total_money,p.payment,p.address,p.date,p.phone\n"
                + "from bill p inner join users c on p.user_id=c.user_id and p.delivery_status='True'";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Bill(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getString(4), rs.getString(5), rs.getDate(6), rs.getString(7)));
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Bill> getBillByDay() {
        List<Bill> list = new ArrayList<>();
        String sql = "select b.bill_id, u.user_name,b.total_money,b.payment,b.address,b.date,b.phone from bill b inner join users u on b.user_id = u.user_id where date = cast(getdate() as Date) and b.delivery_status='False'";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Bill(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getString(4), rs.getString(5), rs.getDate(6), rs.getString(7)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public void updatebill(int id, String status) {
        LocalDate curDate = java.time.LocalDate.now();
        String date = curDate.toString();
        try {
            String query = "insert into billstatus values(?,?,?)";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ps.setString(2, status);
            ps.setString(3, date);
            ps.executeUpdate();
            String deliveryStatusValue;
            switch (status) {
                case "xac nhan don":
                    deliveryStatusValue = "xac nhan don";
                    break;
                case "cho lay hang":
                    deliveryStatusValue = "cho lay hang";
                    break;
                case "dang giao":
                    deliveryStatusValue = "dang giao";
                    break;
                case "hoan thanh":
                    deliveryStatusValue = "hoan thanh";
                    break;
                default:
                    deliveryStatusValue = null;
            }
            if (deliveryStatusValue != null) {
                String updateQuery = "UPDATE bill SET bill_status = ? WHERE bill_id = ?";
                ps = conn.prepareStatement(updateQuery);
                ps.setString(1, deliveryStatusValue);
                ps.setInt(2, id);
                ps.executeUpdate();
            }
        } catch (Exception ex) {

        };
    }

    public void updatebillcancel(int id, String status) {
        LocalDate curDate = java.time.LocalDate.now();
        String date = curDate.toString();
        try {
            String query = "insert into cancelbill values(?,?,?)";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ps.setString(2, status);
            ps.setString(3, date);
            ps.executeUpdate();
        } catch (Exception ex) {

        };
    }

    public void cancelbill(int id, String status) {
        LocalDate curDate = java.time.LocalDate.now();
        String date = curDate.toString();
        try {
            String query = " update bill  set delivery_status='True' where bill_id=?";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception ex) {

        };
    }

    public List<Bill> getBillsByUserId(int user_id) throws Exception {
        List<Bill> bills = new ArrayList<>();
        try {
            String sql = "SELECT bill_id, date, payment, address, total_money, phone, bill_status FROM bill WHERE user_id = ? and delivery_status='False' ORDER BY date DESC";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, user_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                int bill_id = rs.getInt("bill_id");
                java.sql.Date date = rs.getDate("date");
                String payment = rs.getString("payment");
                String address = rs.getString("address");
                float totalMoney = rs.getFloat("total_money");
                String phone = rs.getString("phone");
                String bill_status = rs.getString("bill_status");
                Bill bill = new Bill(bill_id, totalMoney, payment, address, date, phone, bill_status);
                bills.add(bill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bills;

    }

    public int getuseridbybill(int billid) {
        try {
            String sql = "select b.user_id from bill b where b.bill_id=?";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, billid);
            rs = ps.executeQuery();
            while (rs.next()) {
                int a = rs.getInt(1);
                return a;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return 0;
    }
    
    public String getstatus(int billid) {
        try {
            String sql = "select b.bill_status from bill b where b.bill_id=?";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, billid);
            rs = ps.executeQuery();
            while (rs.next()) {
                String a = rs.getString(1);
                return a;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public int getbillid() {
        try {
            String sql = "SELECT TOP 1 bill_id FROM bill ORDER BY bill_id DESC";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int a = rs.getInt(1);
                return a;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return 0;
    }

    public int moneymonth(int month) {
        try {
            String sql = "select sum(total_money) from bill where month(date)=? and delivery_status='False'";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, month);
            rs = ps.executeQuery();
            while (rs.next()) {
                int a = rs.getInt(1);
                return a;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public int quality(int month) {
        try {
            String sql = "select sum(d.quantity)\n"
                    + "  from bill b inner join bill_detail d\n"
                    + "  on b.bill_id=d.bill_id\n"
                    + "  where MONTH(b.date)=? and b.delivery_status='False'";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, month);
            rs = ps.executeQuery();
            while (rs.next()) {
                int a = rs.getInt(1);
                return a;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public List<Bill> getBillsStatus1(int user_id) throws Exception {
        List<Bill> bills = new ArrayList<>();
        try {
            String sql = "SELECT bill_id, date, payment, address, total_money, bill_status\n"
                    + "FROM bill\n"
                    + "WHERE bill_status = 'xac nhan don'\n"
                    + "AND user_id = ?\n"
                    + "ORDER BY date DESC";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, user_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                bills.add(new Bill(rs.getInt(1), rs.getDate(2), rs.getString(3), rs.getString(4), rs.getFloat(5), rs.getString(6)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bills;
    }

    public List<Bill> getBillsStatus2(int user_id) throws Exception {
        List<Bill> bills = new ArrayList<>();
        try {
            String sql = "SELECT bill_id, date, payment, address, total_money, bill_status\n"
                    + "FROM bill\n"
                    + "WHERE bill_status = 'cho lay hang'\n"
                    + "AND user_id = ?\n"
                    + "ORDER BY date DESC";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, user_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                bills.add(new Bill(rs.getInt(1), rs.getDate(2), rs.getString(3), rs.getString(4), rs.getFloat(5), rs.getString(6)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bills;
    }

    public List<Bill> getBillsStatus3(int user_id) throws Exception {
        List<Bill> bills = new ArrayList<>();
        try {
            String sql = "SELECT bill_id, date, payment, address, total_money, bill_status\n"
                    + "FROM bill\n"
                    + "WHERE bill_status = 'dang giao'\n"
                    + "AND user_id = ?\n"
                    + "ORDER BY date DESC";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, user_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                bills.add(new Bill(rs.getInt(1), rs.getDate(2), rs.getString(3), rs.getString(4), rs.getFloat(5), rs.getString(6)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bills;
    }

    public List<Bill> getBillsStatus4(int user_id) throws Exception {
        List<Bill> bills = new ArrayList<>();
        try {
            String sql = "SELECT bill_id, date, payment, address, total_money, bill_status\n"
                    + "FROM bill\n"
                    + "WHERE bill_status = 'hoan thanh'\n"
                    + "AND user_id = ?\n"
                    + "ORDER BY date DESC";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, user_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                bills.add(new Bill(rs.getInt(1), rs.getDate(2), rs.getString(3), rs.getString(4), rs.getFloat(5), rs.getString(6)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bills;
    }

    public List<Bill> getBillsStatus5(int user_id) throws Exception {
        List<Bill> bills = new ArrayList<>();
        try {
            String sql = "SELECT bill_id, date, payment, address, total_money, bill_status\n"
                    + "FROM bill\n"
                    + "WHERE delivery_status = 'True'\n"
                    + "AND user_id = ?\n"
                    + "ORDER BY date DESC";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, user_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                bills.add(new Bill(rs.getInt(1), rs.getDate(2), rs.getString(3), rs.getString(4), rs.getFloat(5), rs.getString(6)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bills;
    }
    public String getLatestBillStatusDate(int billId) {
        try {
            String sql = "SELECT TOP 1 bs.Status FROM billstatus bs WHERE bs.bill_id = ? ORDER BY bs.date DESC ";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, billId);
            rs = ps.executeQuery();
            while (rs.next()) {
                String status = rs.getString("Status");
                return status;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public Date getDangGiao(int billId){
         try {
            String sql = "SELECT TOP 1 bs.date FROM billstatus bs WHERE bs.bill_id = ? AND bs.Status = 'dang giao' ORDER BY bs.date DESC";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, billId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Date date3 = rs.getDate("date");
                return date3;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public Date getXacNhanDon(int billId){
         try {
            String sql = "SELECT TOP 1 bs.date FROM billstatus bs WHERE bs.bill_id = ? AND bs.Status = 'xac nhan don' ORDER BY bs.date DESC";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, billId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Date date1 = rs.getDate("date");
                return date1;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public Date getChoLayHang(int billId){
         try {
            String sql = "SELECT TOP 1 bs.date FROM billstatus bs WHERE bs.bill_id = ? AND bs.Status = 'cho lay hang' ORDER BY bs.date DESC";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, billId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Date date2 = rs.getDate("date");
                return date2;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public Date getHoanThanh(int billId){
         try {
            String sql = "SELECT TOP 1 bs.date FROM billstatus bs WHERE bs.bill_id = ? AND bs.Status = 'hoan thanh' ORDER BY bs.date DESC";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, billId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Date date4 = rs.getDate("date");
                return date4;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
