package com.revature.project1.util;

import com.revature.project1.daos.UserDAO;
import com.revature.project1.screens.*;
import com.revature.project1.services.UserService;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AppState {
    private BufferedReader consoleReader;
    private ScreenRouter router;
    private boolean appRunning;

    public AppState(){
        System.out.println("Initializing applicaton...");

        appRunning = true;
        consoleReader = new BufferedReader(new InputStreamReader(System.in));

        final UserDAO userDAO = new UserDAO();
        final UserService userService = new UserService(userDAO);

        router = new ScreenRouter();
        router.addScreen(new WelcomeScreen(consoleReader,router))
                .addScreen(new LoginScreen(consoleReader,router))
                .addScreen(new RegisterScreen(consoleReader,userService))
                .addScreen(new UserHomeScreen(consoleReader,router))
                .addScreen(new CreateAccountScreen(consoleReader,router))
                .addScreen(new DepositScreen(consoleReader,router))
                .addScreen(new WithdrawlScreen(consoleReader,router))
                .addScreen(new TransactionScreen(consoleReader,router));

        System.out.println("Application Initialized");
    }
    public ScreenRouter getRouter(){ return router; }

    public boolean isAppRunning(){
        return appRunning;
    }
    public void setAppRunning(boolean appRunning) {
        this.appRunning = appRunning;
    }
}
