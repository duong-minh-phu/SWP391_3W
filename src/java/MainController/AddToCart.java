/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainController;

import DAO.productDAO;
import Entity.Cart;
import Entity.Product;
import Entity.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author luong
 */
public class AddToCart extends HttpServlet {

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
        String URL = "MainController?action=productdetail&product_id=";
        try{
            String id = request.getParameter("product_id");
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            URL += id;
            
            Product product = new productDAO().getProductByID(id);
            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("cart");
            User user = (User) session.getAttribute("user");            
            String a=(String) session.getAttribute("deletecart");
            if(a!=null){
                cart = new Cart();
                user.setCart(cart);
                session.removeAttribute("deletecart");
            }
            if (user != null) {                
                cart = user.getCart();
                if (cart == null) {
                    cart = new Cart();
                }
                cart.addItem(product, quantity);
                
                user.setCart(cart);
                session.setAttribute("user", user);
            }else{
                request.setAttribute("error", "Vui lòng Login trước khi mua hàng!!!!");
                request.getRequestDispatcher("login.jsp").forward(request, response);
                 
            }
                      
//            if (cart == null){
//                
//                cart = new Cart();
//            }
//            cart.addItem(product, quantity);
            System.out.println(a);
            session.setAttribute("size", cart.size());
            session.setAttribute("cart", cart);
            
        }finally{
            RequestDispatcher rd = request.getRequestDispatcher(URL);
            rd.forward(request, response);
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
