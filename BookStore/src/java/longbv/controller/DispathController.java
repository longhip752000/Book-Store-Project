/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longbv.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author longh
 */
@WebServlet(name = "DispathController", urlPatterns = {"/DispathController"})
public class DispathController extends HttpServlet {

    private final String LOGIN_PAGE = "login.jsp";
    private final String LOGIN_CONTROLLER = "LoginServlet";
    private final String SEARCH_CONTROLLER = "SearchServlet";
    private final String UPDATE_CONTROLLER = "UpdateServlet";

    private final String EDIT_CONTROLLER = "EditServlet";
    private final String ADD_CONTROLLER = "StoreServlet";
    private final String DELETE_CONTROLLER = "DeleteServlet";
    private final String SHOPPING_CONTROLLER = "ShoppingServlet";

    private final String ADD_TO_CART_CONTROLLER = "AddToCartServlet";
    private final String VIEW_CART_CONTROLLER = "ViewCartServlet";
    private final String REMOVE_ITEMS_CONTROLLER = "RemoveItemsServlet";
    private final String CHECKOUT_CONTROLLER = "CheckOutServlet";
    
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

        String button = request.getParameter("btAction");
        String url = LOGIN_PAGE;

        try {
            if (button == null) {

            } else if (button.equals("Login")) {
                url = LOGIN_CONTROLLER;
            } else if (button.equals("Search")) {
                url = SEARCH_CONTROLLER;
            } else if (button.equals("Edit")) {
                url = EDIT_CONTROLLER;
            } else if (button.equals("Save")) {
                url = UPDATE_CONTROLLER;
            } else if (button.equals("Update")) {
                url = UPDATE_CONTROLLER;
            } else if (button.equals("Store")) {
                url = ADD_CONTROLLER;
            } else if (button.equals("Delete")) {
                url = DELETE_CONTROLLER;
            } else if (button.equals("Shopping")) {
                url = SHOPPING_CONTROLLER;
            } else if (button.equals("Add to cart")) {
                url = ADD_TO_CART_CONTROLLER;
            } else if (button.equals("View Cart")) {
                url = VIEW_CART_CONTROLLER;
            } else if (button.equals("Add Account")) {
                url = ADD_PAGE;
            } else if (button.equals("Remove")) {
                url = REMOVE_ITEMS_CONTROLLER;
            } else if (button.equals("Checkout")) {
                url = CHECKOUT_CONTROLLER;
            }
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
