/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longbv.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import longbv.account.AccountDAO;
import longbv.account.AccountDTO;
import longbv.utils.AccountValidator;

/**
 *
 * @author longh
 */
@WebServlet(name = "StoreServlet", urlPatterns = {"/StoreServlet"})
public class StoreServlet extends HttpServlet {

    private final String SEARCH_CONTROLLER = "SearchServlet";
    private final String ADD_PAGE = "add.jsp";

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
        request.setCharacterEncoding("UTF-8");

        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String retypedPassword = request.getParameter("retypedPassword");
        String fullName = request.getParameter("fullName");
        String roleStr = request.getParameter("role");

        boolean role = false; // user

        if (roleStr != null) {
            role = true; // admin
        }

        boolean haveError = false; //default

        String url = SEARCH_CONTROLLER;

        try {
            String errorUserName = AccountValidator.checkUserName(userName);
            if (errorUserName != null) {
                request.setAttribute("ERROR_USERNAME", errorUserName);
                haveError = true;
            }

            String errorPassword = AccountValidator.checkPassword(password);
            if (errorPassword != null) {
                request.setAttribute("ERROR_PASSWORD", errorPassword);
                haveError = true;
            } else {
                String errorRetypedPass = null;
                if (retypedPassword.isEmpty()) {
                    errorRetypedPass = "Must retyped password!";
                } else if (!retypedPassword.equals(password)) {
                    errorRetypedPass = "Not match with the password!";
                }

                if (errorRetypedPass != null) {
                    request.setAttribute("ERROR_RETYPED_PASSWORD", errorRetypedPass);
                    haveError = true;
                }
            }

            String errorFullName = AccountValidator.checkFullName(fullName);
            if (errorFullName != null) {
                request.setAttribute("ERROR_FULLNAME", errorFullName);
                haveError = true;
            }

            boolean result = false; //default

            if (!haveError) {
                AccountDAO dao = new AccountDAO();
                AccountDTO account = new AccountDTO(userName, password, fullName, role);
                result = dao.addAccount(account);

                if (result) {
                    request.setAttribute("MESSAGE", "Add successfully!");
                } else {
                    request.setAttribute("MESSAGE", "Add failed - Error in DB!");
                    url = ADD_PAGE;
                }
            } else {
                request.setAttribute("MESSAGE", "Add failed - Error Fields!");
                url = ADD_PAGE;
            }

        } catch (SQLException | ClassNotFoundException | NamingException ex) {
            log("Error at " + this.getServletName() + ": " + ex.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
