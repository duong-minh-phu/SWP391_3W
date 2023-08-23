/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Context.DBContext;
import Entity.Category;
import Entity.Product;
import Entity.MealPackage;
import dto.MealsByPackage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ROG STRIX
 */
public class PackageDao {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public Boolean insertPackage(MealPackage insertPackage, String[] productIds) throws SQLException {
        try {
            String sql = "INSERT INTO dbo.Package(package_id,description, name, price, quantity, img, status, delivery_date, size, promotion) values (?,?,?,?,?,?,?,?,?,?)";

            float price = 0;

            productDAO proDAO = new productDAO();
            for (String productId : productIds) {
                Product addProduct = proDAO.getProductByID(productId);
                price = price + addProduct.getProduct_price();
            }
            String packageId = UUID.randomUUID().toString();
            float pricePromotion = (price * (100 - insertPackage.getPromotion())) / 100;
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, packageId);
            ps.setString(2, insertPackage.getDescription());
            ps.setString(3, insertPackage.getName());
            ps.setFloat(4, pricePromotion);
            ps.setInt(5, insertPackage.getQuantity());
            ps.setString(6, insertPackage.getImg());
            ps.setInt(7, insertPackage.getStatus());
            ps.setInt(8, insertPackage.getDelivery_date());
            ps.setFloat(9, insertPackage.getSize());
            ps.setInt(10, insertPackage.getPromotion());

            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                for (String productId : productIds) {
                    String insertProductToPackageSql = "INSERT INTO dbo.ProductInPackage(product_id, package_id) values (?,?)";
                    ps = conn.prepareStatement(insertProductToPackageSql, Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, productId);
                    ps.setString(2, packageId);

                    int Result = ps.executeUpdate();

                    if (Result > 0) {

                        String updateQuantitySqlString = "UPDATE product SET quantity = quantity - ? WHERE product_id = ?";
                        ps = conn.prepareStatement(updateQuantitySqlString, Statement.RETURN_GENERATED_KEYS);
                        ps.setInt(1, insertPackage.getQuantity());
                        ps.setString(2, productId);

                        ps.executeUpdate();
                    }

                }
            } else {
                throw new SQLException("Creating package failed, no ID obtained.");
            }

        } catch (Exception ex) {
            conn.rollback();
            Logger.getLogger(PackageDao.class.getName()).log(Level.SEVERE, "InsertPackage sql Fail", ex);
            throw new SQLException("InsertPackage sql fail:" + ex.getMessage());
        }
        return true;
    }

    public List<MealPackage> getListByPage(List<MealPackage> list,
            int start, int end) {
        ArrayList<MealPackage> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(list.get(i));
        }
        return arr;
    }

    public List<MealPackage> getPackages() throws SQLException {
        List<MealPackage> listPackage = new ArrayList<MealPackage>();
        try {
            String sql = "SELECT * FROM dbo.Package WHERE Status = 1";

            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();

//            ArrayList<MealPackage> pkList = new ArrayList();
            while (rs.next()) {
                MealPackage pk = new MealPackage();

                pk.setId(rs.getString(1));
                pk.setDescription(rs.getString(2));
                pk.setName(rs.getString(3));
                pk.setPrice(rs.getInt(4));
                pk.setQuantity(rs.getInt(5));
                pk.setImg(rs.getString(6));
                pk.setDelivery_date(rs.getInt(7));
                pk.setStatus(rs.getInt(8));
                pk.setSize(rs.getFloat(9));
                pk.setPromotion(rs.getInt(10));
                listPackage.add(pk);
            }
            return listPackage;
        } catch (Exception ex) {
            Logger.getLogger(PackageDao.class.getName()).log(Level.SEVERE, "get package sql Fail", ex);
            throw new SQLException("get package sql Fail" + ex.getMessage());
        }
//        return null;
    }

    public MealPackage getMealPackageByID(String package_id) throws SQLException {
//        List<Product> list = new ArrayList<>();
        String sql = "select *from Package Where package_id=? ";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, package_id);
            rs = ps.executeQuery();
            MealPackage pk = new MealPackage();
            while (rs.next()) {
                pk.setId(rs.getString(1));
                pk.setDescription(rs.getString(2));
                pk.setName(rs.getString(3));
                pk.setPrice(rs.getInt(4));
                pk.setQuantity(rs.getInt(5));
                pk.setImg(rs.getString(6));
                pk.setDelivery_date(rs.getInt(7));
                pk.setStatus(rs.getInt(8));
                pk.setSize(rs.getFloat(9));
                pk.setPromotion(rs.getInt(10));
            }
            return pk;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public void deletePackage(String packageId) throws Exception {
        String sql = "UPDATE package SET status = 0 WHERE package_id = ?";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, packageId);
            ps.executeUpdate();
        } catch (Exception ex) {
            try {
                conn.rollback(); // Nếu có lỗi, rollback các thay đổi trước đó
            } catch (SQLException e) {
                System.out.println("Error rolling back changes: " + ex.getMessage());
            }
            Logger.getLogger(PackageDao.class.getName()).log(Level.SEVERE, "get package sql Fail", ex);
            throw new Exception("delete package Fail" + ex.getMessage());
        }

    }

    public void RecoveryPackage(int package_id) throws Exception {
        String sq = "update package set status=1 where product_id = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sq);
            ps.setInt(1, package_id);
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(PackageDao.class.getName()).log(Level.SEVERE, "get package sql Fail", ex);
            throw new Exception("delete package Fail" + ex.getMessage());
        }

    }

    public List<MealPackage> getPackageLow() {
        List<MealPackage> list = new ArrayList<>();
        String sql = "select *from Package Where status='True' ORDER BY price ASC";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                MealPackage pk = new MealPackage();

                pk.setId(rs.getString(1));
                pk.setDescription(rs.getString(2));
                pk.setName(rs.getString(3));
                pk.setPrice(rs.getInt(4));
                pk.setQuantity(rs.getInt(5));
                pk.setImg(rs.getString(6));
                pk.setDelivery_date(rs.getInt(7));
                pk.setStatus(rs.getInt(8));
                pk.setSize(rs.getFloat(9));
                pk.setPromotion(rs.getInt(10));
                list.add(pk);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public List<MealPackage> getPackageHigh() {
        List<MealPackage> list = new ArrayList<>();
        String sql = "select *from Package Where status='True' ORDER BY price DESC";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                MealPackage pk = new MealPackage();

                pk.setId(rs.getString(1));
                pk.setDescription(rs.getString(2));
                pk.setName(rs.getString(3));
                pk.setPrice(rs.getInt(4));
                pk.setQuantity(rs.getInt(5));
                pk.setImg(rs.getString(6));
                pk.setDelivery_date(rs.getInt(7));
                pk.setStatus(rs.getInt(8));
                pk.setSize(rs.getFloat(9));
                pk.setPromotion(rs.getInt(10));
                list.add(pk);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public List<MealPackage> getPackageAZ() {
        List<MealPackage> list = new ArrayList<>();
        String sql = "select *from Package Where status='True' ORDER BY name";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                MealPackage pk = new MealPackage();

                pk.setId(rs.getString(1));
                pk.setDescription(rs.getString(2));
                pk.setName(rs.getString(3));
                pk.setPrice(rs.getInt(4));
                pk.setQuantity(rs.getInt(5));
                pk.setImg(rs.getString(6));
                pk.setDelivery_date(rs.getInt(7));
                pk.setStatus(rs.getInt(8));
                pk.setSize(rs.getFloat(9));
                pk.setPromotion(rs.getInt(10));
                list.add(pk);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public List<MealPackage> getPackageByList(String package_id) {
        List<MealPackage> list = new ArrayList<>();
        String sql = "SELECT * FROM Package WHERE package_id NOT LIKE ? and status='True';";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, package_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                MealPackage pk = new MealPackage();
                pk.setId(rs.getString(1));
                pk.setDescription(rs.getString(2));
                pk.setName(rs.getString(3));
                pk.setPrice(rs.getInt(4));
                pk.setQuantity(rs.getInt(5));
                pk.setImg(rs.getString(6));
                pk.setDelivery_date(rs.getInt(7));
                pk.setStatus(rs.getInt(8));
                pk.setSize(rs.getFloat(9));
                pk.setPromotion(rs.getInt(10));
                list.add(pk);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Product> getProductInPackage(String package_id) {
        List<Product> listProduct = new ArrayList<>();
        String getProductSql = "SELECT  p.* from dbo.ProductInPackage pip inner join product p on pip.product_id = p.product_id where package_id = ?; ";
        productDAO prodDao = new productDAO();

        try {

            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(getProductSql);
            ps.setString(1, package_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setProduct_id(rs.getString(1));
                product.setCate(prodDao.getCategoryById(rs.getString(2)));
                product.setProduct_name(rs.getString(3));
                product.setProduct_price(rs.getFloat(4));
                product.setProduct_describe(rs.getString(5));
                product.setQuantity(rs.getInt(6));
                product.setImg(rs.getString(7));

                listProduct.add(product);
            }
        } catch (Exception e) {
        }
        return listProduct;
    }

    public List<MealsByPackage> getMealByPackage(String package_id) {
        List<MealsByPackage> list = new ArrayList<>();
        String sql = "select b.product_id,b.product_describe,b.product_price,b.price,b.img,c.category_name,b.product_name,b.promotion,b.size\n"
                + "from (select hp.product_id,t.product_describe,t.product_price,hp.price,t.img,t.category_id,t.product_name,hp.promotion,t.size\n"
                + "from (select product_id,p.price,p.promotion from Package p inner join ProductInPackage o on p.package_id = o.package_id \n"
                + "Where p.package_id = ?) as hp inner join Product t\n"
                + "on hp.product_id=t.product_id) as b inner join category c on b.category_id=c.category_id";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, package_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                MealsByPackage pk = new MealsByPackage();
                pk.setId(rs.getString(1));
                pk.setDescribe(rs.getString(2));
                pk.setProductPrice(rs.getFloat(3));
                pk.setPrice(rs.getFloat(4));
                pk.setImg(rs.getString(5));
                pk.setCategoryName(rs.getString(6));
                pk.setProductName(rs.getString(7));
                pk.setPromotion(rs.getInt(8));
                pk.setQuantity(rs.getString(9));
                list.add(pk);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

//    String package_name = request.getParameter("package_name");
//        int quantity = Integer.parseInt(request.getParameter("quantity"));
////        String describe = request.getParameter("describe");
//        String describe = new String(request.getParameter("describe").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
//        int delivery_date = Integer.parseInt(request.getParameter("delivery_date"));
//        String[] productIds = request.getParameterValues("product_id_list");
//        float size = Float.parseFloat(request.getParameter("weight"));
//        int promotion = Integer.parseInt(request.getParameter("promotion"));
//        String insertStatus = "";
    public void updatePackage(MealPackage updatePackage, String[] updateProductIds) throws SQLException {

        PackageDao packageDao = new PackageDao();
        productDAO proDAO = new productDAO();

        List<Product> oldProductsInPackage = getProductInPackage(updatePackage.getId());
        List<Product> currentProductsInPackage = new ArrayList<>();
        List<Product> deleteProducts = new ArrayList<>();

        try {
            conn = new DBContext().getConnection();
//            conn.setAutoCommit(false);
            for (String productId : updateProductIds) {
                currentProductsInPackage.add(proDAO.getProductByID(productId));
            }

            MealPackage oldPackage = packageDao.getMealPackageByID(updatePackage.getId());

            for (Product product : oldProductsInPackage) {
                if (!currentProductsInPackage.contains(product)) {
                    deleteProducts.add(product);
                }
            }
            int updateQuantity = updatePackage.getQuantity() - oldPackage.getQuantity();

            for (Product currentProduct : currentProductsInPackage) {
                if (oldProductsInPackage.contains(currentProduct)) {
                    if (updateQuantity < 0) {
                        String updateQuantitySqlString = "UPDATE product SET quantity = quantity + ? WHERE product_id = ?";
                        conn = new DBContext().getConnection();
                        ps = conn.prepareStatement(updateQuantitySqlString);
                        ps.setInt(1, updateQuantity);
                        ps.setString(2, currentProduct.getProduct_id());
                        ps.executeUpdate();
                    } else if (updateQuantity > 0) {
                        String updateQuantitySqlString = "UPDATE product SET quantity = quantity - ? WHERE product_id = ?";
                        conn = new DBContext().getConnection();
                        ps = conn.prepareStatement(updateQuantitySqlString);
                        ps.setInt(1, updateQuantity);
                        ps.setString(2, currentProduct.getProduct_id());
                        ps.executeUpdate();
                    }
                } else {
                    String insertProductToPackageSql = "INSERT INTO dbo.ProductInPackage(product_id, package_id) values (?,?)";
                    ps = conn.prepareStatement(insertProductToPackageSql);
                    ps.setString(1, currentProduct.getProduct_id());
                    ps.setString(2, updatePackage.getId());

                    int Result = ps.executeUpdate();

                    if (Result > 0) {
                        String updateQuantitySqlString = "UPDATE product SET quantity = quantity - ? WHERE product_id = ?";
                        conn = new DBContext().getConnection();
                        ps = conn.prepareStatement(updateQuantitySqlString);
                        ps.setInt(1, updatePackage.getQuantity());
                        ps.setString(2, currentProduct.getProduct_id());
                        ps.executeUpdate();
                    } else {
                        throw new SQLException("insert product to package sql Fail in update");
                    }
                }
            }

            if (!deleteProducts.isEmpty()) {
                for (Product product : deleteProducts) {
                    String insertProductToPackageSql = "DELETE FROM dbo.ProductInPackage where product_id = ?";
                    ps = conn.prepareStatement(insertProductToPackageSql);
                    ps.setString(1, product.getProduct_id());

                    int Result = ps.executeUpdate();

                    if (Result > 0) {
                        String updateQuantitySqlString = "UPDATE product SET quantity = quantity + ? WHERE product_id = ?";
                        conn = new DBContext().getConnection();
                        ps = conn.prepareStatement(updateQuantitySqlString);
                        ps.setInt(1, updatePackage.getQuantity());
                        ps.setString(2, product.getProduct_id());
                        ps.executeUpdate();
                    } else {
                        throw new SQLException("delete product to package sql Fail in update");
                    }
                }
            }

            float price = 0;
            for (String productId : updateProductIds) {
                Product addProduct = proDAO.getProductByID(productId);
                price = price + addProduct.getProduct_price();
            }
            float pricePromotion = (price * (100 - updatePackage.getPromotion())) / 100;
            String updateSqlString = "update package set name = ?, price = ?, quantity = ?, img = ?, size = ?, promotion = ?, description = ? where package_id=? and status=1";

            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(updateSqlString);
            ps.setString(1, updatePackage.getName());
            ps.setFloat(2, pricePromotion);
            ps.setInt(3, updatePackage.getQuantity());
            ps.setString(4, updatePackage.getImg());
            ps.setFloat(5, updatePackage.getSize());
            ps.setInt(6, updatePackage.getPromotion());
            ps.setString(7, updatePackage.getDescription());
            ps.setString(8, updatePackage.getId());
            ps.executeUpdate();

//            conn.commit();
        } catch (Exception e) {
//            conn.rollback();
            throw new SQLException("Update Package sql fail:" + e.getMessage());
        } finally {
//            conn.close();
        }
    }
}
