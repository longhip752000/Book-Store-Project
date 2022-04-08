/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longbv.account;

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
public class AccountDAO implements Serializable {

    private AccountDTO account;

    public AccountDTO getAccount() {
        return account;
    }

    public boolean checkLogin(String userName, String password) throws ClassNotFoundException, SQLException, NamingException {
        Connection con = null;
        PreparedStatement pm = null;
        ResultSet rs = null;
        try {
            //1.connect DB
            con = DBHelpers.makeConnection();
            //2. create sql statement
            String sql = "Select fullName, role "
                    + "From Accounts "
                    + "Where userName = ? and password = ?";
            //3. create state set sql
            pm = con.prepareStatement(sql);
            pm.setString(1, userName);
            pm.setString(2, password);
            //4. excute SQL query
            rs = pm.executeQuery();
            if (rs.next()) {
                String fullName = rs.getString("fullName");
                boolean role = rs.getBoolean("role");
                account = new AccountDTO(userName, password, fullName, role);
                return true;
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
        return false;
    }

    public ArrayList<AccountDTO> searchAccounts(String searchName) throws ClassNotFoundException, SQLException, NamingException {
        Connection con = null;
        PreparedStatement pm = null;
        ResultSet rs = null;

        ArrayList<AccountDTO> accounts = new ArrayList<>();

        try {
            //1.connect DB
            con = DBHelpers.makeConnection();
            //2. create sql statement
            String sql = "Select userName, password, fullName, role "
                    + "From Accounts "
                    + "Where fullName like ?";
            //3. create state set sql
            pm = con.prepareStatement(sql);

            pm.setString(1, "%" + searchName + "%");
            //4. excute SQL query
            rs = pm.executeQuery();
            while (rs.next()) {
                String userName = rs.getString("userName");
                String password = rs.getString("password");
                String fullName = rs.getString("fullName");
                boolean role = rs.getBoolean("role");
                AccountDTO account = new AccountDTO(userName, password, fullName, role);
                accounts.add(account);
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
        return accounts;
    }

    public AccountDTO getAccountByUserName(String userName) throws ClassNotFoundException, SQLException, NamingException {
        Connection con = null;
        PreparedStatement pm = null;
        ResultSet rs = null;

        try {
            //1.connect DB
            con = DBHelpers.makeConnection();
            //2. create sql statement
            String sql = "Select password, fullName, role "
                    + "From Accounts "
                    + "Where userName = ?";
            //3. create state set sql
            pm = con.prepareStatement(sql);

            pm.setString(1, userName);
            //4. excute SQL query
            rs = pm.executeQuery();
            if (rs.next()) {
                String password = rs.getString("password");
                String fullName = rs.getString("fullName");
                boolean role = rs.getBoolean("role");
                
                return new AccountDTO(userName, password, fullName, role);
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
    
    public boolean updateAccount(AccountDTO account) throws ClassNotFoundException, SQLException, NamingException {
        Connection con = null;
        PreparedStatement pm = null;
        int rs = 0;
        try {
            //1.connect DB
            con = DBHelpers.makeConnection();
            //2. create sql statement
            String sql = "Update Accounts "
                    + "Set password =?, fullName = ?, role = ? "
                    + "Where userName = ?";
            //3. create state set sql
            pm = con.prepareStatement(sql);

            pm.setString(1, account.getPassword());
            pm.setString(2, account.getFullName());
            pm.setBoolean(3, account.isRole());
            pm.setString(4, account.getUserName());
            //4. excute SQL query
            
            rs = pm.executeUpdate();
            if (rs != 0) {
                return true;
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
    
    public boolean addAccount(AccountDTO account) throws ClassNotFoundException, SQLException, NamingException {
        Connection con = null;
        PreparedStatement pm = null;
        int rs = 0;
        try {
            //1.connect DB
            con = DBHelpers.makeConnection();
            //2. create sql statement
            String sql = "Insert into Accounts(userName, password, fullName, role) "
                    + "Values(?, ?, ?, ?) ";
            //3. create state set sql
            pm = con.prepareStatement(sql);

            pm.setString(1, account.getUserName());
            pm.setString(2, account.getPassword());
            pm.setString(3, account.getFullName());
            pm.setBoolean(4, account.isRole());
            
            //4. excute SQL query
            
            rs = pm.executeUpdate();
            if (rs != 0) {
                return true;
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
    
    public boolean deleteUser(String userName) throws ClassNotFoundException, SQLException, NamingException{
        Connection con = null;
        PreparedStatement pm = null;
        int rs = 0;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Delete Accounts "
                        + "Where userName = ?";
                //create statement to DBI
                pm = con.prepareStatement(sql);

                pm.setString(1, userName);

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
}
