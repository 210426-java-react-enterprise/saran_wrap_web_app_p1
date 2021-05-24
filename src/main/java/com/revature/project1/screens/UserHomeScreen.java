package com.revature.project1.screens;

import com.revature.project1.util.structures.LinkedList;
import com.revature.project1.daos.UserDAO;
import com.revature.project1.models.AppUser;
import com.revature.project1.util.ScreenRouter;
import java.io.BufferedReader;
import java.io.IOException;

import static com.revature.project1.Driver.app;

public class UserHomeScreen extends Screen {
    private UserDAO userDAO = new UserDAO();
    private BufferedReader consoleReader;
    private ScreenRouter router;

    public UserHomeScreen(BufferedReader consoleReader, ScreenRouter router) {
        super("LoginScreen", "/homescreen");
        this.consoleReader = consoleReader;
        this.router = router;
    }

    @Override
    public void render() {
    }

    @Override
    public void render(AppUser currentUser) {

        String userSelection = null;
        System.out.print(
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
                        + "\t+Welcome back, " + currentUser.getFirstName() + "!\n"
                        + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        //This block of code displays a (L)List of all of their available accounts
        LinkedList currentUserAccounts;
        currentUserAccounts = userDAO.getAllCurrentUserAccounts(currentUser);
        if (!currentUserAccounts.isEmpty()){
            currentUserAccounts.printLinkedList();
        }

        //Displays Home Screen Menu
        System.out.print(
                "Please Choose an option from the list below:\n" +
                        "\t(5) Create Account\n" +
                        "\t(6) Make a Deposit\n" +
                        "\t(7) Make a Withdrawl\n" +
                        "\t(8) View Your Transactions\n" +
                        "\t(9) Exit Banking App" +
                        "\n:::");
        //Attempts to route to user's chosen screen
        try {
            System.out.print(">");
             userSelection = consoleReader.readLine();
            switch (userSelection) {
                case "5":
                    router.navigate("/newAccount",currentUser);
                    break;
                case "6":
                    System.out.println("Navigating to Deposit Screen...");
                    router.navigate("/deposit",currentUser);
                    break;
                case "7":
                    System.out.println("Navigating to Withdrawl Screen...");
                    router.navigate("/withdrawl",currentUser);
                    break;
                case "8":
                    System.out.println("Navigating to Transaction Screen...");
                    router.navigate("/transaction",currentUser);
                    break;
                case"9":
                    System.out.println("Exiting Banking App.");
                    app().setAppRunning(false);
                    break;
                default:
                    System.out.println("Invalid input selected.");

            }

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Input Output Exception Raised @UserHomeScreen");
        }
    }
}