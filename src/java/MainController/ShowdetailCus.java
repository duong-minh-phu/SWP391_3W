/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainController;

import DAO.billDAO;
import Entity.BillDetail;
import Entity.Bill;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ngodi
 */
@WebServlet(name = "ShowdetailCus", urlPatterns = {"/ShowdetailCus"})
public class ShowdetailCus extends HttpServlet {   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
           String billid=request.getParameter("bill_id");
            System.out.println(billid);
           int id=Integer.parseInt(billid);
           billDAO dao=new billDAO();
           String billnew = dao.getLatestBillStatusDate(id);
           Date date3 = (Date) dao.getDangGiao(id);
           Date date1 = (Date) dao.getXacNhanDon(id);
           Date date2 = (Date) dao.getChoLayHang(id);
           Date date4 = (Date) dao.getHoanThanh(id);
           List<BillDetail> detail = dao.getDetail(id);
           List<BillDetail> detail_package = dao.getDetailpackage(id);
           request.setAttribute("Date3", date3);
           request.setAttribute("Date4", date4);
           request.setAttribute("Date2", date2);
           request.setAttribute("Date1", date1);
           request.setAttribute("Detail", detail);
           request.setAttribute("Detail_package", detail_package);
           request.setAttribute("BillNew", billnew);
           System.out.println(detail);
           request.getRequestDispatcher("billdetail.jsp").forward(request, response);
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
