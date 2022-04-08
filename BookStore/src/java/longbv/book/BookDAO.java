/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longbv.book;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import longbv.utils.DBHelpers;

/**
 *
 * @author longh
 */
public class BookDAO implements Serializable {

    private BookDTO bDTO;
    private BookDTO searchBDTO;

    public BookDTO getSearchBDTO() {
        return searchBDTO;
    }

    public void setSearchBDTO(BookDTO searchBDTO) {
        this.searchBDTO = searchBDTO;
    }

    public BookDTO getbDTO() {
        return bDTO;
    }

    public void setbDTO(BookDTO bDTO) {
        this.bDTO = bDTO;
    }

    private List<BookDTO> searchBook;

    public List<BookDTO> getSearchBook() {
        return searchBook;
    }

    public ArrayList<BookDTO> getAllBooks() throws ClassNotFoundException, SQLException, NamingException {
        Connection con = null;
        PreparedStatement pm = null;
        ResultSet rs = null;
        
        ArrayList<BookDTO> books = new ArrayList<>();
        
        try {
            //1. connect DB
            con = DBHelpers.makeConnection();
            //2. create sql statement
            String sql = "Select bookId, bookName, price, quanity, cateID "
                    + "From Books ";
            //3. create statement set sql
            pm = con.prepareStatement(sql);
            //4. excute SQL query
            rs = pm.executeQuery();
            while (rs.next()) {
                //get field/ column
                String bookId = rs.getString("bookId");
                String bookName = rs.getNString("bookName");
                int price = rs.getInt("price");
                int quantity = rs.getInt("quanity");
                //create DTo instance
                BookDTO bDto = new BookDTO(bookId, bookName, price, quantity);
                books.add(bDto);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pm != null) {
                pm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return books;
    }

    public boolean updateBook(BookDTO book) throws
            SQLException, ClassNotFoundException, NamingException {
        Connection con = null;
        PreparedStatement pm = null;
        int rs = 0;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Update Books "
                        + "Set price = ?, quanity = ?, cateId = ? "
                        + "Where bookId = ? and bookName = ?";
                //create statement to DBI
                pm = con.prepareStatement(sql);
                pm.setInt(1, book.getPrice());
                pm.setInt(2, book.getQuantity());
                pm.setString(5, book.getBookId());
                pm.setString(4, book.getBookName());
                rs = pm.executeUpdate();
                if (rs != 0) {
                    return true;
                }
            }
        } finally {
            if (pm != null) {
                pm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public BookDTO getBookById(String bookId) throws SQLException, ClassNotFoundException, NamingException {
        Connection con = null;
        PreparedStatement pm = null;
        ResultSet rs = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Select bookId, bookName, price, quanity "
                        + "From Books "
                        + "where bookId = ?";
                pm = con.prepareStatement(sql);
                pm.setString(1, bookId);
                rs = pm.executeQuery();
                if (rs.next()) {
                    return new BookDTO(bookId, rs.getString("bookName"), rs.getInt("price"), rs.getInt("quanity"));
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pm != null) {
                pm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

    public boolean storeBook(BookDTO book) throws SQLException, ClassNotFoundException, NamingException {
        Connection con = null;
        PreparedStatement pm = null;
        int rs = 0;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Insert into Books(bookId, bookName, price, quanity) "
                        + "values (?, ?, ?, ?)";
                //create statement to DBI
                pm = con.prepareStatement(sql);

                pm.setString(1, book.getBookId());
                pm.setString(2, book.getBookName());
                pm.setInt(3, book.getPrice());
                pm.setInt(4, book.getQuantity());

                rs = pm.executeUpdate();
                if (rs != 0) {
                    return true;
                }
            }
        } finally {
            if (pm != null) {
                pm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean deleteBook(String bookName)
            throws SQLException, ClassNotFoundException, NamingException {
        Connection con = null;
        PreparedStatement pm = null;
        int rs = 0;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Delete Books "
                        + "Where bookName = ?";
                //create statement to DBI
                pm = con.prepareStatement(sql);

                pm.setString(1, bookName);

                rs = pm.executeUpdate();
                if (rs != 0) {
                    return true;
                }
            }
        } finally {
            if (pm != null) {
                pm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public BookDTO checkExistBookId(String bookId) throws ClassNotFoundException, SQLException, NamingException {
        Connection con = null;
        PreparedStatement pm = null;
        ResultSet rs = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Select bookId, bookName, price, quanity "
                        + "From Books "
                        + "Where bookId = ?";
                pm = con.prepareStatement(sql);
                pm.setString(1, bookId);
                rs = pm.executeQuery();
                if (rs.next()) {
                    return new BookDTO(bookId, rs.getString("bookName"), rs.getInt("price"), rs.getInt("quanity"));
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pm != null) {
                pm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

}
