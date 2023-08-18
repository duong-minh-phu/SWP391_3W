package MainController.PackageController;

import Entity.Category;
import Entity.Product;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.nio.charset.StandardCharsets;
import java.util.List;
import DAO.PackageDao;
import DAO.productDAO;
import javax.mail.Session;

@MultipartConfig()
@WebServlet(name = "GetDataForInsertPackage", urlPatterns = "/GetDataForInsertPackage")

public class GetDataForInsertPackage extends HttpServlet {

    String INSERT_PACKAGE = "admin/packageInsert.jsp";

    String MEAL_ID_ERROR_STATUS = "mealIdError";
    String SUCCESS_STATUS = "success";
    String QUANTITY_ERROR_STATUS = "quantityError";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            PackageDao packageao = new PackageDao();
            productDAO productDao = new productDAO();
            List<Product> product = productDao.getProduct();
            request.setAttribute("ProductData", product);

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
            request.getRequestDispatcher(INSERT_PACKAGE).forward(request, response);
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
