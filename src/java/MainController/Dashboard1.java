/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainController;

import DAO.billDAO;
import DAO.productDAO;
import DAO.userDAO;
import Entity.Product;
import Entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ROG STRIX
 */
@WebServlet(name = "Dashboard1", urlPatterns = {"/Dashboard1"})
public class Dashboard1 extends HttpServlet {

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
        try  {
            HttpSession session = request.getSession();
            Entity.User user = (User) session.getAttribute("user");
            if (user.getIsAdmin().equalsIgnoreCase("ADMIN")||user.getIsAdmin().equalsIgnoreCase("STAFF")) {
                 userDAO dao = new userDAO();
            List<User> user1 = dao.getUser();
            productDAO p = new productDAO();
            List<Product> products = p.getProduct();
            billDAO dao1=new billDAO();           
            List<Entity.Bill> bill=dao1.getBill();
            List<Entity.Bill> billbyday=dao1.getBillByDay();
            List<Product> pro50=p.getProduct50();
            System.out.println(bill.size());
                for (int i = 1; i < 12; i++) {
                    System.out.println(dao1.moneymonth(i));
                }
            Calendar calendar = Calendar.getInstance();
            int month = calendar.get(Calendar.MONTH) + 1; 
            List<Entity.Product> product = p.getTop5Product();
         List<Entity.Product> product1 = p.getTrendProduct();
         List<Entity.Bill> bill1=dao1.getBill1();
         request.setAttribute("bill11", bill1.size());
         request.setAttribute("top5", product);
         request.setAttribute("topTrend1", product1);
         request.setAttribute("qua1", dao1.quality(1));
            request.setAttribute("qua2", dao1.quality(2));
            request.setAttribute("qua3", dao1.quality(3));
            request.setAttribute("qua4", dao1.quality(4));
            request.setAttribute("qua5", dao1.quality(5));
            request.setAttribute("qua6", dao1.quality(6));
            request.setAttribute("moneymonth", dao1.moneymonth(month));
            request.setAttribute("size50", pro50.size());    
            request.setAttribute("sizebill", bill.size());
            request.setAttribute("sizepro", products.size());
            request.setAttribute("sizeuser", user1.size());
            request.setAttribute("billbyday", billbyday);
                request.getRequestDispatcher("/admin/index_1.jsp").forward(request, response);
            } else {
                response.sendRedirect("login");
            }
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
