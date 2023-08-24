/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package MainController.PackageController;

import DAO.PackageDao;
import DAO.productDAO;
import Entity.MealPackage;
import Entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
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
@WebServlet(name = "UpdatePackage", urlPatterns = {"/UpdatePackage"})
public class UpdatePackage extends HttpServlet {

    String UPDATE_PACKAGE = "admin/updatePackage.jsp";

    String MEAL_ID_ERROR_STATUS = "mealIdError";
    String SUCCESS_STATUS = "success";
    String QUANTITY_ERROR_STATUS = "quantityError";

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
        PackageDao packageDao = new PackageDao();

        String package_name = new String(request.getParameter("package_name").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String describe = new String(request.getParameter("describe").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        String[] productIds = request.getParameterValues("product_id_list");
        int promotion = Integer.parseInt(request.getParameter("promotion"));
        String id = request.getParameter("package_id");

        

        String updateStatus = "";
        try {
            MealPackage oldPackage = packageDao.getMealPackageByID(id);
            
            for (String productId : productIds) {
                Product checkProduct = productDAO.getProductByID(productId);
                if (checkProduct == null) {
                    updateStatus = MEAL_ID_ERROR_STATUS;
                    response.sendRedirect("MainController?action=getPackageForUpdate&package_id=" + id + "&updateStatus=" + updateStatus);
                }
//                } else if (oldPackage.getQuantity() < quantity) {
//                    if (checkProduct.getQuantity() - quantity < 0) {
//                        updateStatus = QUANTITY_ERROR_STATUS;
//                        response.sendRedirect("MainController?action=getPackageForUpdate&package_id=" + id + "&updateStatus=" + updateStatus);
//                    }
//                }
            }

            Part filePart = request.getPart("package_img");
            System.out.println(filePart);
            String realPath = request.getServletContext().getRealPath("/images/");
            System.out.println(realPath);
            String fileName = filePart.getSubmittedFileName(); // Lấy tên tệp ảnh gốc
            System.out.println(fileName);

            if (fileName.equals("")) {
                MealPackage newPackage = new MealPackage();

                newPackage.setId(id);
                newPackage.setName(package_name);
                newPackage.setPromotion(promotion);
                newPackage.setQuantity(quantity);
                newPackage.setStatus(1);
                newPackage.setDescription(describe);
                newPackage.setImg(realPath);

                packageDao.updatePackage(newPackage, productIds);

                updateStatus = SUCCESS_STATUS;
                response.sendRedirect("MainController?action=getPackageForUpdate&package_id=" + id + "&updateStatus=" + updateStatus);
                return;
            } else {
                String destinationPath = realPath + fileName;
                System.out.println(destinationPath);

                filePart.write(destinationPath);

                String imagePath = "images/" + fileName;
                System.out.println(imagePath);

                MealPackage newPackage = new MealPackage();

                newPackage.setId(id);
                newPackage.setId(id);
                newPackage.setName(package_name);
                newPackage.setPromotion(promotion);
                newPackage.setQuantity(quantity);
                newPackage.setStatus(1);
                newPackage.setDescription(describe);
                newPackage.setImg(imagePath);

                packageDao.updatePackage(newPackage, productIds);

                updateStatus = SUCCESS_STATUS;
                response.sendRedirect("MainController?action=getPackageForUpdate&package_id=" + id + "&updateStatus=" + updateStatus);

            }

//            if (fileName == null) {
//                response.sendRedirect("404.jsp");
//            }

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
