package com.revature.project1.screens;

import com.revature.project1.daos.UserDAO;
import com.revature.project1.models.AppUser;
import com.revature.project1.models.UserTransactionHistory;
import com.revature.project1.util.ScreenRouter;
import com.revature.project1.models.UserAccount;
import java.io.BufferedReader;
import java.io.IOException;
import java.time.Instant;
import java.util.Date;


public class CreateAccountScreen extends Screen {

    private UserDAO userDao = new UserDAO();
    private BufferedReader consoleReader;
    private ScreenRouter router;
    Date date = new Date();
    Instant instant = date.toInstant();
    public CreateAccountScreen(BufferedReader consoleReader, ScreenRouter router) {
        super("CreateAccountScreen","/newAccount");
        this.consoleReader = consoleReader;
        this.router = router;
    }

    @Override
    public void render() {

    }
    @Override
    public void render(AppUser currentUser) {
        //Grab current user's id and send it to userDao
        int userId = currentUser.getId();
        double balance=0.00;//
        String accountType;//
        boolean makeFirstDeposit = false;
        String choice;

        try {
            System.out.print("Name/Type of Account: ");
            accountType = consoleReader.readLine(); // throws Exception here

            System.out.print("Would you like to make your first deposit today?(y/n): ");
            choice = consoleReader.readLine();
            if (choice.equalsIgnoreCase("y")) {
                System.out.print("Amount to be deposited: ");
                balance = Double.parseDouble(consoleReader.readLine());


                System.out.printf("$%.2f was deposited into your account.\n" +
                        "Please log back in to access your funds",balance);

            }else if (choice.equalsIgnoreCase("n")){
                balance = 0.00;

            }else{
                System.out.println("Entry invalid. Please log back in and try again !");
            }
            UserAccount newAccount = new UserAccount(userId,balance,accountType);
            userDao.saveAccount(newAccount);
            UserTransactionHistory newTransaction = new UserTransactionHistory(currentUser.getId(),"DEPOSIT",balance,String.valueOf(instant),accountType);
            userDao.updateTransactionsTable(newTransaction);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
