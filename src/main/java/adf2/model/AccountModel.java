package adf2.model;

import adf2.entity.Account;
import adf2.helper.ConnectionHelper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class AccountModel {



    public boolean save(Account account){
        try {
            String cmd = "insert into accounts (username,password,fullName,createdDate) values (?,?,?,?)";
            PreparedStatement preparedStatement = ConnectionHelper.getConnection().prepareStatement(cmd);
            preparedStatement.setString(1,account.getUsername());
            preparedStatement.setString(2,account.getPassword());
            preparedStatement.setString(3,account.getFullName());
            preparedStatement.setString(4,account.getCreatedDate());
            preparedStatement.execute();
            return true;
        } catch (Exception ex) {
            System.err.println("Error: " + ex.getMessage());
            return false;
        }
    }
    public ArrayList<Account> getList(){
        ArrayList<Account> list = new ArrayList<Account>();
        try {
            String cmd = "select * from accounts";
            PreparedStatement preparedStatement = ConnectionHelper.getConnection().prepareStatement(cmd);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String fullName = resultSet.getString("fullName");
                String createdDate = resultSet.getString("createdDate");
                Account obj = new Account(username,password,fullName,createdDate);
                list.add(obj);
            }
        } catch (Exception ex){
            System.err.println("Không thể lấy dữ liệu từ database. Message: " + ex.getMessage());
            ex.printStackTrace();
        }
        return list;
    }
    public static Account getAccout(String userName, String password) {
        try {
            String cmd = "select * from accounts where username = ? and password = ?";
            PreparedStatement preparedStatement = ConnectionHelper.getConnection().prepareStatement(cmd);
            preparedStatement.setString(1,userName);
            preparedStatement.setString(2,password);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String username = resultSet.getString("username");
                String password1 = resultSet.getString("password");
                String fullName = resultSet.getString("fullName");
                String createdDate = resultSet.getString("createdDate");
                Account account = new Account(username,password1,fullName,createdDate);
                return account;
            }

        } catch (SQLException e) {
           e.printStackTrace();
        }
        return null;
    }
    public void test() throws SQLException {
        Statement statement = ConnectionHelper.getConnection().createStatement();
        statement.execute("truncate table accounts");
    }

}
