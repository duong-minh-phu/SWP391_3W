/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainController.PackageController;

import DAO.PackageDao;
import DAO.productDAO;
import DAO.ratingDAO;
import Entity.Rating;
import java.io.IOException;
import java.io.PrintWriter;
import dto.MealsByPackage;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author HoangPhatNguyen
 */
public class Packagedetail extends HttpServlet {

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
        try {
            String package_id = request.getParameter("package_id");
            PackageDao dao = new PackageDao();
            ratingDAO rate = new ratingDAO();
            List<Rating> rating = rate.getRatingsByPackageID(package_id);
            double rating_ave = rate.calculateAverageRatingPackage(package_id);
            int rating_count = rate.countRatingsByPackageId(package_id);
//            Entity.Product product = c.getProductByID(package_id);
//            if(product==null){
//                response.sendRedirect("404.jsp");
//            }
//            int category_id = product.getCate().getCategory_id();
//            if(product.getQuantity()==0){
//                 request.setAttribute("detail", "Mặt hàng này đã hết xin chọn loại khác!!!");
//            }
//            List<Entity.Product> productByCategory = c.getProductByCategory(category_id);
            request.setAttribute("RatingAV", rating_ave);
            request.setAttribute("RatingCount", rating_count);
            request.setAttribute("ReviewData", rating);
//            request.setAttribute("ProductData", product);
//            request.setAttribute("ProductByCategory", productByCategory);
//            request.getRequestDispatcher("product-details.jsp").forward(request, response);
            List<Entity.MealPackage> packageByList = dao.getPackageByList(package_id);
            List<MealsByPackage> mealsByPackage = dao.getMealByPackage(package_id);
            Entity.MealPackage mealPackages = dao.getMealPackageByID(package_id);
            if (mealPackages.getQuantity() == 0) {
                request.setAttribute("detail", "Mặt hàng này đã hết xin chọn loại khác!!!");
            }
            if (mealPackages == null) {
                response.sendRedirect("404.jsp");
            }
            request.setAttribute("MealPackageData", mealPackages);

            request.setAttribute("PackageByList", packageByList);
            float price = 0;
            int promotion = 0;
            for (MealsByPackage el : mealsByPackage) {
                price += el.getProductPrice();
                promotion = el.getPromotion();
            }
            List<String> list= new ArrayList<>();
            for (MealsByPackage a : mealsByPackage) {                
                list.add(a.getQuantity());
            }
            request.setAttribute("quantity", list);
            request.setAttribute("promotion", promotion);
            request.setAttribute("PriceAllMeals", price);

            request.setAttribute("MealsByPackage", mealsByPackage);

            request.getRequestDispatcher("package_detail.jsp").forward(request, response);

        } catch (Exception ex) {

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
