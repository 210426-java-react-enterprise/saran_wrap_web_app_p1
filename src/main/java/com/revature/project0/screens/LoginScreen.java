package com.revature.project0.screens;

import com.revature.project0.daos.UserDAO;
import com.revature.project0.models.AppUser;
import com.revature.project0.util.ScreenRouter;
import com.revature.project0.screens.UserHomeScreen;
import java.io.BufferedReader;


public class LoginScreen extends Screen {
    private UserDAO userDao = new UserDAO();
    private BufferedReader consoleReader;
    private ScreenRouter router;
    public LoginScreen(BufferedReader consoleReader, ScreenRouter router) {
        super("LoginScreen","/login");
        this.consoleReader = consoleReader;
        this.router = router;
    }

    public void render() {

        try {
            String username;
            String password;

            System.out.println("Welcome Back! Login Below:");
            System.out.println("+-------------------------+");

            System.out.print("Username: ");
            username = consoleReader.readLine();

            System.out.print("Password: ");
            password = consoleReader.readLine();
            //Checks for Valid User
            if (username != null && !username.isEmpty() && password != null && !password.isEmpty()){
                AppUser authenticatedUser = userDao.loginValidation(username,password);
                if (authenticatedUser != null){
                    System.out.println("Login Successful!");
                    System.out.println("Navigating to Home Screen...");
                    router.navigate("/homescreen",authenticatedUser);
                }else{
                    System.out.println(authenticatedUser);
                    System.out.println("Login failed.");
                }
            }else{
                System.out.println("It looks like you didnt provide valid credentials");
            }
        }


        catch (Exception e) {
            e.printStackTrace(); // include this line while developing/debugging the app!
            // should be logged to a file in a production environment
        }



    }
}

