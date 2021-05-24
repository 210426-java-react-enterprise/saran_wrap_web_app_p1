package com.revature.project1.screens;

import com.revature.project1.util.ScreenRouter;
import static com.revature.project1.Driver.app;
import java.io.BufferedReader;
import java.io.IOException;

public class WelcomeScreen extends Screen{
    private final BufferedReader consoleReader;
    private final ScreenRouter router;
    public WelcomeScreen(BufferedReader consoleReader, ScreenRouter router){
        super("WelcomeScreen","/welcome");
        this.consoleReader = consoleReader;
        this.router = router;

    }
    //First Screen. Allows user to sign up, register or exit
    @Override
    public void render() {
        System.out.print("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
                "\tWelcome to Big Baller Bank\n" +
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
                "Please Choose an option from the list below:\n" +
                "\t(1) Sign Up\n" +
                "\t(2) Log In\n" +
                "\t(3) Exit" +
                "\n:::");


        try{
            System.out.print(">");
            String userSelection = consoleReader.readLine();
            switch(userSelection){
                case "1":
                    //makes a new
                    System.out.println("Navigating to Register Screen...");
                    router.navigate("/register");
                    break;
                case "2":
                    System.out.println("Navigating to Login Screen...");
                    router.navigate("/login");
                    break;
                case "3":
                    System.out.println("Exiting Banking App.");
                    app().setAppRunning(false);
                    break;
                default:
                    System.out.println("Invalid input selected.");

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
