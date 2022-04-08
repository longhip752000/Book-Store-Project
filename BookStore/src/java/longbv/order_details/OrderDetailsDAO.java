/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longbv.order_details;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.naming.NamingException;
import longbv.account.AccountDTO;
import longbv.book.BookDTO;
import longbv.cart.Cart;
import longbv.utils.DBHelpers;

/**
 *
 * @author GF65
 */
public class OrderDetailsDAO {
     public void checkout(ArrayList<BookDTO> books) throws ClassNotFoundException, SQLException, NamingException {
        Connection con = null;
        PreparedStatement pm = null;
        int rs = 0;
        try {
            //1.connect DB
            con = DBHelpers.makeConnection();
            //2. create sql statement
            String sql = "Insert into OrderDetails(bookId, quantity, price, total) "
                    + "Values(?, ?, ?, ?) ";
            //3. create state set sql
            pm = con.prepareStatement(sql);

            for (BookDTO book : books) {
                pm.setString(1, book.getBookId());
                pm.setInt(2, book.getQuantity());
                pm.setDouble(3, book.getPrice());
                pm.setDouble(4, book.getPrice() * book.getQuantity());
                
                rs = pm.executeUpdate();
            }
            
        } finally {
            if (pm != null) {
                pm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
}
