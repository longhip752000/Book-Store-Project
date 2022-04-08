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
@WebServlet(name = "UpdateServlet", urlPatterns = {"/UpdateServlet"})
public class UpdateServlet extends HttpServlet {

    private final String SEARCH_CONTROLLER = "SearchServlet"; //for going to search.jsp
    private final String EDIT_PAGE = "edit.jsp"; // for going to edit.jsp

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

        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String fullName = request.getParameter("fullName");
        String roleStr = request.getParameter("role");

        boolean role = false; // user

        if (roleStr != null) {
            role = true; // admin
        }

        boolean haveError = false; //default

        String url = SEARCH_CONTROLLER;

        try {
            // ko update username

            String errorPassword = AccountValidator.checkPassword(password);
            if (errorPassword != null) {
                request.setAttribute("ERROR_PASSWORD", errorPassword);
                haveError = true;
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
                result = dao.updateAccount(account);

                if (result) {
                    request.setAttribute("MESSAGE", "Update successfully!");
                } else {
                    request.setAttribute("MESSAGE", "Update failed - Error in DB!");
                }
            } else {

                String message = "Update failed - Error Fields!";

                if (request.getParameter("btAction").equals("Update")) {
                    if (errorPassword != null) {
                        message += "\\n + " + errorPassword;
                    }
                    if (errorFullName != null) {
                        message += "\\n + " + errorFullName;
                    }
                    // nếu nhấn Update thì đi đến SEARCH_CONTROLLER (search.jsp)
                } else if (request.getParameter("btAction").equals("Save")) {
                    // ngược lại nếu là nút save thì quay lại edit page (edit.jsp)
                    url = EDIT_PAGE;
                    // giữ lại giá trị trên các input cho người dùng xem
                    AccountDTO account = new AccountDTO(userName, password, fullName, role);

                    request.setAttribute("ACCOUNT", account);
                }

                request.setAttribute("MESSAGE", message);
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
