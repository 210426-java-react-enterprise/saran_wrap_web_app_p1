package com.revature.project1.screens;

import com.revature.project1.daos.UserDAO;
import com.revature.project1.models.AppUser;
import com.revature.project1.models.UserAccount;
import com.revature.project1.models.UserTransactionHistory;
import com.revature.project1.util.ScreenRouter;
import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.Instant;

public class WithdrawlScreen extends Screen {
    private UserDAO userDao = new UserDAO();
    private BufferedReader consoleReader;
    private ScreenRouter router;
    private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public WithdrawlScreen(BufferedReader consoleReader, ScreenRouter router){
        super("WithdrawlScreen","/withdrawl");
        this.consoleReader = consoleReader;
        this.router= router;
    }
    @Override
    public void render() {
    }
    @Override
    public void render(AppUser currentUser) {
        Double balance;
        Double withdrawlAmount;
        String accountName;
        Date date = new Date();
        Instant instant = date.toInstant();


        try{
            System.out.println("Name/Type of Account:");
            System.out.print(":::>");
            accountName = consoleReader.readLine();

            UserAccount currentAccount = userDao.getUserAccount(accountName,currentUser.getId());

            if(currentAccount != null){

                balance = currentAccount.getBalance();
                System.out.println(balance);
                System.out.println("Amount to Withdraw:");
                System.out.print(":::>$ ");
                withdrawlAmount = Double.parseDouble(consoleReader.readLine());

                //checks for overdraft
                if (balance - withdrawlAmount < 0){
                    System.out.println("This transaction will cause you to overdraft\n" +
                                        "Please enter an amount you can afford lol");
                    System.out.print(":::>$ ");
                    withdrawlAmount = Double.parseDouble(consoleReader.readLine());
                };

                balance -= withdrawlAmount;

                System.out.printf("Withdrawing %.2f into account named: %s \n",withdrawlAmount,accountName);
                userDao.updateUserBalance(currentUser,accountName,balance);
                UserTransactionHistory newTransaction = new UserTransactionHistory(currentUser.getId(),"WITHDRAW",withdrawlAmount,String.valueOf(instant),currentAccount.getAccountType());

                userDao.updateTransactionsTable(newTransaction);

                System.out.printf("Your current balance is now: $%.2f \t",balance);
            }else{
                System.out.printf("You have no accounts called: %s",accountName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
