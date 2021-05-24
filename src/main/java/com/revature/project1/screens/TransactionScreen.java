package com.revature.project1.screens;
import com.revature.project1.daos.UserDAO;
import com.revature.project1.models.AppUser;
import com.revature.project1.util.structures.LinkedList;
import com.revature.project1.util.ScreenRouter;
import java.io.BufferedReader;
import java.io.IOException;


public class TransactionScreen extends Screen{

    private UserDAO userDao = new UserDAO();
    private BufferedReader consoleReader;
    private ScreenRouter router;
    //Better name: TransactionHistoryScreen
    public TransactionScreen(BufferedReader consoleReader, ScreenRouter router) {
        super("Transaction Screen","/transaction");
        this.consoleReader = consoleReader;
        this.router = router;
    }

    @Override
    public void render() {

    }

    @Override
    public void render(AppUser currentUser){
        //User can choose to view all transactions or transactions for specified account name
        String userChoice;
        LinkedList accountHistory;
        try{
            System.out.println("Would you like to view your entire transaction history?(y/n)");
            userChoice = consoleReader.readLine();
            if (userChoice.equalsIgnoreCase("y")){
                accountHistory = userDao.getAllTransactions(currentUser.getId());
                accountHistory.printLinkedList();
            }
            //Needs to handle case in which Account Name does not exist
            else if (userChoice.equalsIgnoreCase("n")){
                String accountName;
                System.out.println("Name/Type of Account:");
                accountName = consoleReader.readLine();
                accountHistory = userDao.getTransactions(currentUser.getId(), accountName);
                accountHistory.printLinkedList();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
