/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package MainController.PackageController;

import DAO.PackageDao;
import DAO.productDAO;
import Entity.Blog;
import Entity.Category;
import Entity.Product;
import Entity.MealPackage;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author huybao
 */
@WebServlet(name = "InsertPackage", urlPatterns = {"/InsertPackage"})
public class InsertPackage extends HttpServlet {

    String INSERT_PACKAGE = "admin/packageInsert.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        productDAO productDAO = new productDAO();
        
//    private String company;
//    private String size;
//    private  String expiry;

        String package_name = request.getParameter("package_name");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String describe = request.getParameter("describe");
        int delivery_date = Integer.parseInt(request.getParameter("delivery_date"));
        String[] productIds = request.getParameterValues("product_id_list");
        float size = Float.parseFloat(request.getParameter("weight"));
        int promotion = Integer.parseInt(request.getParameter("promotion"));
        
        try {
            for (String productId : productIds) {
                Product checkProduct = productDAO.getProductByID(productId);
                if (checkProduct == null) {
                    request.getSession().setAttribute("errorMessage", "Meal không hợp ");
                    request.getRequestDispatcher("MainController?action=packageManage").forward(request, response);
                    return;
                } else {
                    if (checkProduct.getQuantity() - quantity < 0) {
                        request.getSession().setAttribute("errorMessage", "Số lượng meal " + checkProduct.getProduct_name() + " không đủ để tạo package");
                        request.getRequestDispatcher("MainController?action=packageManage").forward(request, response);
                        return;
                    };
                }
            }

//            String product_name=new String(product_name1.getBytes(StandardCharsets.ISO_8859_1),StandardCharsets.UTF_8);
//            String product_describe=new String(product_describe1.getBytes(StandardCharsets.ISO_8859_1),StandardCharsets.UTF_8);
            // Chuyển đổi java.util.Date thành java.sql.Date
            Part filePart = request.getPart("package_img");
            System.out.println(filePart);
            String realPath = request.getServletContext().getRealPath("/images/");
            System.out.println(realPath);
            String fileName = filePart.getSubmittedFileName(); // Lấy tên tệp ảnh gốc
            System.out.println(fileName);

            if (fileName != null) {
                String destinationPath = realPath + fileName;
                System.out.println(destinationPath);

                filePart.write(destinationPath);

                String imagePath = "images/" + fileName;
                System.out.println(imagePath);

                PackageDao packageDao = new PackageDao();
                MealPackage newPackage = new MealPackage(describe, package_name, quantity, imagePath, 1, delivery_date,  size, promotion);

                packageDao.insertPackage(newPackage, productIds);

                request.getSession().setAttribute("successMessage", "Đã thêm package thành công");
                request.getRequestDispatcher("MainController?action=packageManage").forward(request, response);

            }
            if (fileName == null) {
                response.sendRedirect("404.jsp");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/404.jsp");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
