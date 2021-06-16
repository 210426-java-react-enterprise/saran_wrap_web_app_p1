package com.revature.project1.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AppState {
    private BufferedReader consoleReader;
    private boolean appRunning;

    public AppState(){
        System.out.println("Initializing applicaton...");

        appRunning = true;
        consoleReader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Application Initialized");
    }

    public boolean isAppRunning(){
        return appRunning;
    }
    public void setAppRunning(boolean appRunning) {
        this.appRunning = appRunning;
    }
}
