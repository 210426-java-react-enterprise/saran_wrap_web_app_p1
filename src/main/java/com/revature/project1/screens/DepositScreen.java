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

public class DepositScreen extends Screen{
    private UserDAO userDao = new UserDAO();
    private BufferedReader consoleReader;
    private ScreenRouter router;
    private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");


    public DepositScreen(BufferedReader consoleReader, ScreenRouter router){
        super("DepositScreen","/deposit");
        this.consoleReader = consoleReader;
        this.router= router;
    }

    @Override
    public void render() {

    }

    @Override
    public void render(AppUser currentUser) {
        Double balance;
        Double depositAmount;
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
                System.out.println("Amount to Deposit:");
                System.out.print(":::>$ ");
                depositAmount = Double.parseDouble(consoleReader.readLine());
                balance += depositAmount;
                System.out.printf("Depositing $%.2f into account named: %s",depositAmount,accountName);
                userDao.updateUserBalance(currentUser,accountName,balance);
                UserTransactionHistory newTransaction = new UserTransactionHistory(currentUser.getId(),"DEPOSIT",depositAmount,String.valueOf(instant),currentAccount.getAccountType());
                userDao.updateTransactionsTable(newTransaction);

                System.out.printf("Your current balance is now: $%f \n",balance);
            }else{
                System.out.printf("You have no accounts called %s",accountName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
