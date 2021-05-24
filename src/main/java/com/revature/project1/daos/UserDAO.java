package com.revature.project1.daos;
import com.revature.project1.models.UserTransactionHistory;
import com.revature.project1.util.structures.LinkedList;
import com.revature.project1.models.AppUser;
import com.revature.project1.models.UserAccount;
import com.revature.project1.util.ConnectionFactory;

import java.sql.*;
/*TODO make sure to organize userDAO group functionalities
*  -ADD BUSINESS LOGIC TO PASSWORD
*  -FIGURE OUT HOW TO STORE PASSWORD SECURELY*/
public class UserDAO {
    public void save(AppUser newUser){
        try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sqlInsertUser = "insert into bigballerbank.customer (username , password , email , first_name , last_name , user_age ) values (?,?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sqlInsertUser,new String[]{"user_id"});
            pstmt.setString(1,newUser.getUsername());
            pstmt.setString(2,newUser.getPassword());
            pstmt.setString(3,newUser.getEmail());
            pstmt.setString(4,newUser.getFirstName());
            pstmt.setString(5,newUser.getLastName());
            pstmt.setInt(6,newUser.getAge());
            int rowsInserted = pstmt.executeUpdate();

            if (rowsInserted != 0){
                ResultSet rs = pstmt.getGeneratedKeys();
                while(rs.next()){
                    newUser.setId(rs.getInt("user_id"));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    /**MARK::-----USER VALIDATION METHODS-----**/
    //Checks secure password, not standard but works for now
    public Boolean isPasswordSecure(String password){
        boolean hasLetter = false;
        boolean hasDigit = false;
        if (password.length() >= 8){
            for (int i = 0; i < password.length() ; i++) {
                char x = password.charAt(i);
                if (Character.isLetter(x)){
                    hasLetter = true;
                }
                if (Character.isDigit(x)) {
                    hasDigit = true;
                }
                if (hasLetter && hasDigit){
                    return true;
                }

            }
        }
        return false;
    }
    //Checks whether or not an email has been used already @EJ: You spelled 'available' wrong lol
    public Boolean isEmailAvailible(String email){
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "select * from bigballerbank.customer where email = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,email);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()){
                return false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }
    //Checks if username has been taken @EJ: Do you know how to spell?
    public Boolean isUsernameAvailible(String username){
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "select * from bigballerbank.customer where username = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,username);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()){
                return false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }
    //Checks to see if user account exists in DB
    public AppUser loginValidation(String username,String password){
        AppUser user = null;
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){

            String sql = "select * from bigballerbank.customer where username = ? and password = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,username);
            pstmt.setString(2,password);

            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                user = new AppUser();
                user.setId(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setAge(rs.getInt("user_age"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

//    // TODO implement me: You can only delete an account when signed in
//    public void deleteAccount(AppUser user){
//        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
//            String sql = "delete * from bigballerbank.users where username = ?";
//            PreparedStatement pstmt = conn.prepareStatement(sql);
//            pstmt.setInt(1,user.getId());
//
//
//            ResultSet rs = pstmt.executeQuery();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }


    /**Mark:: ----ALL USER ACCOUNT METHODS----**/
    //Saves a creation of new account
    public void saveAccount(UserAccount newAccount){
        try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sqlInsertAccount = "insert into bigballerbank.account (user_id, balance, account_type) values (?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sqlInsertAccount,new String[]{"account_id"});
            pstmt.setInt(1,newAccount.getUserId());
            pstmt.setDouble(2,newAccount.getBalance());
            pstmt.setString(3,newAccount.getAccountType());
            int rowsInserted = pstmt.executeUpdate();

            if (rowsInserted != 0){
                ResultSet rs = pstmt.getGeneratedKeys();
                while(rs.next()){
                    newAccount.setAccountId(rs.getInt("account_id"));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    //Grabs Information for specified userAccount
    public UserAccount getUserAccount(String accountName,int userId){
        UserAccount userAccount = null;
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){

            String sql = "select * from bigballerbank.account where account_type = ? and user_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,accountName);
            pstmt.setInt(2,userId);

                ResultSet rs = pstmt.executeQuery();
                while(rs.next()){
                    userAccount = new UserAccount();
                    userAccount.setAccountId(rs.getInt("account_id"));
                    userAccount.setUserId(rs.getInt("user_id"));
                    userAccount.setBalance(rs.getDouble("balance"));
                    userAccount.setAccountType(rs.getString("account_type"));

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return userAccount;
        }
    //Grabs all user accounts to be displayed when they log in succesfully
    public LinkedList getAllCurrentUserAccounts(AppUser currentUser){
        //this method will grab all user accounts,store it into UserAccount
        //object and add it to a linkedlist called userAccountLinkedList
        LinkedList<UserAccount> currentUserAccounts = new LinkedList<>();
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            UserAccount userAccount = null;
            String sql = "select * from bigballerbank.account where user_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,currentUser.getId());
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                userAccount = new UserAccount();
                userAccount.setAccountId(rs.getInt("account_id"));
                userAccount.setUserId(rs.getInt("user_id"));
                userAccount.setBalance(rs.getDouble("balance"));
                userAccount.setAccountType(rs.getString("account_type"));
                currentUserAccounts.add(userAccount);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return currentUserAccounts;
    }
    //Updates user's balance after a transaction has been made
    public void updateUserBalance(AppUser currentUser, String accountName, Double balance) {
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){

            String sql = "update bigballerbank.account set balance = ? where user_id = ? and account_type = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setDouble(1,balance);
            pstmt.setInt(2,currentUser.getId());
            pstmt.setString(3,accountName);
            pstmt.execute();
            System.out.println("New balance: " + balance);
    } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    //Updates transaction table after each transaction
    public void updateTransactionsTable(UserTransactionHistory newTransaction){
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){

            String sql = "insert into bigballerbank.transactions (user_id , transaction_type , amount, transaction_date , account_type , account_id ) values (?,?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,newTransaction.getUserId());
            pstmt.setString(2,newTransaction.getTransactionType());
            pstmt.setDouble(3,newTransaction.getTransactionAmount());
            pstmt.setString(4,newTransaction.getTransactionDate());
            pstmt.setString(5,newTransaction.getAccountType());
            pstmt.setInt(6, newTransaction.getAccountId());
            int rowsInserted = pstmt.executeUpdate();

            if (rowsInserted != 0){
                ResultSet rs = pstmt.getGeneratedKeys();
                while(rs.next()){
                    newTransaction.setTransactionId(rs.getInt("transaction_id"));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    //Grabs all transactions upon request
    public LinkedList getAllTransactions(int currentUserId){
        LinkedList<UserTransactionHistory> currentUserTransactions = new LinkedList<>();
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            UserTransactionHistory userTransaction = null;
            String sql = "select * from bigballerbank.transactions where user_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,currentUserId);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                userTransaction = new UserTransactionHistory();
                userTransaction.setAccountId(rs.getInt("account_id"));
                userTransaction.setUserId(rs.getInt("user_id"));
                userTransaction.setTransactionId(rs.getInt("transaction_id"));
                userTransaction.setAccountType(rs.getString("account_type"));
                userTransaction.setTransactionType(rs.getString("transaction_type"));
                userTransaction.setTransactionAmount(rs.getDouble("amount"));
                userTransaction.setTransactionDate(rs.getString("transaction_date"));
                currentUserTransactions.add(userTransaction);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return currentUserTransactions;
    }
    //grabs transaction history on specified accounts upon request
    public LinkedList getTransactions(int currentUserId,String accountName) {
        LinkedList<UserTransactionHistory> currentUserTransactions = new LinkedList<>();
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            UserTransactionHistory userTransaction = null;
            String sql = "select * from bigballerbank.transactions where user_id = ? and account_Type = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, currentUserId);
            pstmt.setString(2,accountName);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                userTransaction = new UserTransactionHistory();
                userTransaction.setAccountId(rs.getInt("account_id"));
                userTransaction.setUserId(rs.getInt("user_id"));
                userTransaction.setTransactionId(rs.getInt("transaction_id"));
                userTransaction.setAccountType(rs.getString("account_type"));
                userTransaction.setTransactionType(rs.getString("transaction_type"));
                userTransaction.setTransactionAmount(rs.getDouble("amount"));
                userTransaction.setTransactionDate(rs.getString("transaction_date"));
                currentUserTransactions.add(userTransaction);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return currentUserTransactions;
    }


}
