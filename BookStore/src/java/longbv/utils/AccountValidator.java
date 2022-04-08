/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longbv.utils;

import java.sql.SQLException;
import javax.naming.NamingException;
import longbv.account.AccountDAO;
import longbv.book.BookDAO;

/**
 *
 * @author GF65
 */
public class AccountValidator {
    
    public static String checkUserName(String userName) throws SQLException, ClassNotFoundException, NamingException {
        if (userName == null || userName.isEmpty()) {
            return "Not empty!";
        }
        if (userName.length() > 10 ) {
            return "The length is less than 10 chacracters!";
        }
        String regexCode = "[a-zA-Z0-9]+";
        if (!userName.matches(regexCode)) {
            return "Not have special characters!";
        }
        if (new AccountDAO().getAccountByUserName(userName) != null) {
            return "Has existed!";
        }
        return null;
    }
    
    public static String checkFullName(String fullName) throws SQLException, ClassNotFoundException {
        if (fullName == null || fullName.isEmpty()) {
            return "Not empty!";
        }
        if (fullName.length() < 5 && fullName.length() > 50 ) {
            return "The length is between 5-50 chacracters!";
        }
        String regexCode = "[a-zA-Z0-9 ]+"; // cho phép full name chứa space characters
        if (!fullName.matches(regexCode)) {
            return "Not have special characters!";
        }

        return null;
    }

    public static String checkPassword(String password) {
        if (password == null || password.isEmpty()) {
            return "Not empty!";
        }

        if (password.length() < 3 && password.length() > 20 ) {
            return "The length is between 3-20 chacracters!";
        }
        
        return null;
    }
}
