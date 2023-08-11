/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainController;

import DAO.BlogCommentDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
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
@WebServlet(name = "Blogcomment", urlPatterns = {"/Blogcomment"})
public class Blogcomment extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String comment1=request.getParameter("blograting");
            String comment=new String(comment1.getBytes(StandardCharsets.ISO_8859_1),StandardCharsets.UTF_8);
            HttpSession session = request.getSession();
            Entity.User user = (Entity.User) session.getAttribute("user");
            
            if(user==null){
                request.setAttribute("error", "Bạn cần phải đăng nhập trước khi comment");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
            if(user.getUser_name().isEmpty()){
                request.setAttribute("infor", "bạn cần cập nhật thông tin trước khi comment");
                request.getRequestDispatcher("my-account.jsp").forward(request, response);
            }
            System.out.println(user.getUser_name());
            if(user!=null && !user.getUser_name().isEmpty()){
            int user_id = user.getUser_id();
            String blog_id=request.getParameter("blog_id");
            int id_blog=Integer.parseInt(blog_id);
            BlogCommentDAO dao=new BlogCommentDAO();
            dao.BlogComment(user_id, id_blog, comment);
            request.getRequestDispatcher("MainController?action=blogdetails").forward(request, response);
            }
        }catch(Exception ex){
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
