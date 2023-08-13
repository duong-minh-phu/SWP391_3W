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
@MultipartConfig()
@WebServlet(name = "PackageManagement", urlPatterns = "/PackageManagement")

public class PackageManagement extends HttpServlet {
    String INSERT_PACKAGE = "admin/packageInsert.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        try {
            PackageDao packageao = new PackageDao();
            productDAO productDao = new productDAO();
                List<Product> product = productDao.getProduct();
                request.setAttribute("ProductData", product);
                
                request.getRequestDispatcher(INSERT_PACKAGE).forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/404.jsp");
        }
        
        
                
        
//        try {
//            String category_id = request.getParameter("category_id");
//            String productId = request.getParameter("product_id");
//            String product_name1 = request.getParameter("product_name");
//            String product_price = request.getParameter("price");
//            String product_quantity = request.getParameter("quantity");
//            String product_describe1 = request.getParameter("describe");                        
//            String product_name=new String(product_name1.getBytes(StandardCharsets.ISO_8859_1),StandardCharsets.UTF_8);
//            String product_describe=new String(product_describe1.getBytes(StandardCharsets.ISO_8859_1),StandardCharsets.UTF_8);
//            // Chuyển đổi java.util.Date thành java.sql.Date
//            
//            int quantity = Integer.parseInt(product_quantity);
//            Float price = Float.parseFloat(product_price);
//            int cid = Integer.parseInt(category_id);
//            productDAO dao = new productDAO();
//            Category cate = new Category(cid);
//            
//            Part filePart = request.getPart("product_img");
//            System.out.println(filePart);
//            String realPath = request.getServletContext().getRealPath("/images/");
//            System.out.println(realPath);
//            String fileName = filePart.getSubmittedFileName(); // Lấy tên tệp ảnh gốc
//            System.out.println(fileName);
//
//            if (fileName != null) {
//                String destinationPath = realPath + fileName;
//                System.out.println(destinationPath);
//
//                filePart.write(destinationPath);
//
//                String imagePath = "images/" + fileName;
//                System.out.println(imagePath);
//
//               Product product = new Product(cate, productId, product_name, price, product_describe, quantity, imagePath);
//
//                dao.insertProduct(product);
//
//                // Chuyển hướng người dùng đến trang danh sách sản phẩm
//                request.getSession().setAttribute("successMessage", "Đã thêm sản phẩm thành công");
//                request.getRequestDispatcher("MainController?action=insert").forward(request, response);
//            }
//            if (fileName == null) {
//                response.sendRedirect("404.jsp");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            response.sendRedirect(request.getContextPath() + "/404.jsp");
//        }
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
