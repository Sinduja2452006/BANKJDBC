package com.wipro.bank.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.wipro.bank.bean.TransferBean;
import com.wipro.bank.util.DBUtil;

public class BankDAO {

    public int generateSequenceNumber() throws SQLException {
        Connection connection = DBUtil.getDBConnection();
        String query = "SELECT bank_trans_seq.NEXTVAL FROM dual";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean validateAccount(String accountNumber) {
        String query = "SELECT 1 FROM BANK_ACCOUNT_TBL WHERE Account_ID=?";
        try (Connection connection = DBUtil.getDBConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, accountNumber);
            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

 
    public float findBalance(String accountNumber) {
        String query = "SELECT Balance FROM BANK_ACCOUNT_TBL WHERE Account_ID=?";
        try (Connection connection = DBUtil.getDBConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, accountNumber);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getFloat(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public boolean transferMoney(TransferBean transferBean) throws SQLException {
        Connection connection = DBUtil.getDBConnection();
        transferBean.setTransactionID(generateSequenceNumber());

        String query = "INSERT INTO BANK_TRANSFER_TBL VALUES (?,?,?,?,?)";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, transferBean.getTransactionID());
            ps.setString(2, transferBean.getFromAccountNumber());
            ps.setString(3, transferBean.getToAccountNumber());
            ps.setDate(4, new Date(transferBean.getDateOFTransaction().getTime()));
            ps.setFloat(5, transferBean.getAmount());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateBalance(String accountNumber, float newBalance) {
        String query = "UPDATE BANK_ACCOUNT_TBL SET Balance=? WHERE Account_ID=?";
        try (Connection connection = DBUtil.getDBConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setFloat(1, newBalance);
            ps.setString(2, accountNumber);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
