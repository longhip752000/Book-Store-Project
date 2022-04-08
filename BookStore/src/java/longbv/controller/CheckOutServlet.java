/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longbv.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import longbv.book.BookDAO;
import longbv.book.BookDTO;
import longbv.cart.Cart;
import longbv.order_details.OrderDetailsDAO;

/**
 *
 * @author GF65
 */
@WebServlet(name = "CheckOutServlet", urlPatterns = {"/CheckOutServlet"})
public class CheckOutServlet extends HttpServlet {
    private final String SHOPPING_CONTROLLER = "ShoppingServlet";
    private final String VIEW_CART_CONTROLLER = "ViewCartServlet";
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
        
        String url = SHOPPING_CONTROLLER;

        HttpSession session = request.getSession();

        Cart cart = (Cart) session.getAttribute("CART");

        if (cart == null) { // chưa có giỏ
            cart = new Cart(); // giỏ chưa có hàng
        }

        HashMap<String, Integer> items = cart.getItems();

        if (items == null) {
            items = new HashMap<>();
        }

        ArrayList<BookDTO> books = new ArrayList<>();

        try {
            BookDAO dao = new BookDAO();
            ArrayList<String> errors = new ArrayList<>();
            for (String bookId : items.keySet()) {
                BookDTO book = dao.getBookById(bookId);

                if (book.getQuantity() < items.get(bookId)) {
                    errors.add(bookId + " - " + book.getBookName() + " - max available quantity: " + book.getQuantity());
                }

                book.setQuantity(items.get(bookId));

                books.add(book);
            }

            if (errors.isEmpty()) {
                // check out

                OrderDetailsDAO orderDetailsDao = new OrderDetailsDAO();
                orderDetailsDao.checkout(books);
                
                session.removeAttribute("CART");
                
                request.setAttribute("MESSAGE", "Checkout Successfully!");
            } else {
                url = VIEW_CART_CONTROLLER;
                
                String message = "Checkout failed! Some quantity error: ";
                
                for (String error : errors) {
                    message += "\\n -> " + error; 
                }
                
                request.setAttribute("MESSAGE", message);
            }
        } catch (SQLException | ClassNotFoundException | NamingException ex) {
            log("Error at " + this.getServletName() + ": " + ex.getMessage());
        }

        request.getRequestDispatcher(url).forward(request, response);
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
