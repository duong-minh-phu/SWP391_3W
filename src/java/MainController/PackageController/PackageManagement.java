/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package MainController.PackageController;

import DAO.PackageDao;
import DAO.productDAO;
import Entity.Category;
import Entity.MealPackage;
import Entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author huybao
 */
@WebServlet(name = "PackageManagement", urlPatterns = {"/PackageManagement"})
public class PackageManagement extends HttpServlet {
    String PACKAGE_MANAGEMENT = "admin/PackageManagement.jsp";

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
        try {
            PackageDao p = new PackageDao();
            List<MealPackage> packageList = p.getPackages();            
            request.setAttribute("PackageData", packageList);           
            
            
            String insertStatus = request.getParameter("insertStatus");

            if (insertStatus != null) {
                if (insertStatus.equals(MEAL_ID_ERROR_STATUS)) {
                    String message = "Meal không hợp lệ";
                    request.setAttribute("failMessage", message);
                } else if (insertStatus.equals(QUANTITY_ERROR_STATUS)) {
                    String message = "Số lượng meal không đủ để tạo package";
                    request.setAttribute("failMessage", message);
                } else if (insertStatus.equals(SUCCESS_STATUS)) {
                    String message = "Đã thêm package thành công";
                    request.setAttribute("successMessage", message);
                }
            }
            request.getRequestDispatcher(PACKAGE_MANAGEMENT).forward(request, response);
        } catch (Exception e) {
            response.sendRedirect("404.jsp");
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
