/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package MainController.PackageController;

import DAO.PackageDao;
import DAO.productDAO;
import Entity.MealPackage;
import Entity.Product;
import dto.MealsByPackage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
@WebServlet(name = "GetInfoForUpdatePackage", urlPatterns = {"/GetInfoForUpdatePackage"})
public class GetInfoForUpdatePackage extends HttpServlet {

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
        try {
            PackageDao packageDao = new PackageDao();
            MealPackage packages = packageDao.getMealPackageByID(request.getParameter("package_id"));
            request.setAttribute("PackageData", packages);
            List<MealsByPackage> productInPackageList = packageDao.getMealByPackage(request.getParameter("package_id"));
            request.setAttribute("MealsByPackageData", productInPackageList);

            List<String> selectedProducts = new ArrayList<>();
            for (MealsByPackage a : productInPackageList) {
                selectedProducts.add(a.getId());
            }
// ...Thêm các product_id khác vào danh sách

            request.setAttribute("selectedProducts", selectedProducts);
            productDAO productDao = new productDAO();
            List<Product> product = productDao.getProduct();
            request.setAttribute("ProductData", product);

            String updateStatus = request.getParameter("updateStatus");

            if (updateStatus != null) {
                if (updateStatus.equals(MEAL_ID_ERROR_STATUS)) {
                    String message = "Meal không hợp lệ";
                    request.setAttribute("failMessage", message);
                } else if (updateStatus.equals(QUANTITY_ERROR_STATUS)) {
                    String message = "Số lượng meal không đủ để tạo package";
                    request.setAttribute("failMessage", message);
                } else if (updateStatus.equals(SUCCESS_STATUS)) {
                    String message = "Đã cập nhật package thành công";
                    request.setAttribute("successMessage", message);
                }
            }
            request.getRequestDispatcher(UPDATE_PACKAGE).forward(request, response);
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
