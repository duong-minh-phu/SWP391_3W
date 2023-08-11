/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainController;

import DAO.billDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ROG STRIX
 */
@WebServlet(name = "Updatebill", urlPatterns = {"/Updatebill"})
public class Updatebill extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            String Bill_id = request.getParameter("bill_id");
            String status = request.getParameter("permission");
            int id = Integer.parseInt(Bill_id);
                   billDAO dao=new billDAO();
                   System.out.println(dao.getstatus(id));
                   String status1=dao.getstatus(id);
                   if(status1.equals("xac nhan don")&& status.equals("cho lay hang")){
                       dao.updatebill(id, status);
                       response.sendRedirect("MainController?action=billmana");
                   }else if(status1.equals("cho lay hang")&& status.equals("dang giao")){
                       dao.updatebill(id, status);
                       response.sendRedirect("MainController?action=billmana");
                   }else if(status1.equals("dang giao")&& status.equals("hoan thanh")){
                       dao.updatebill(id, status);
                       response.sendRedirect("MainController?action=billmana");
                   }else{
                       response.sendRedirect("404.jsp");
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
