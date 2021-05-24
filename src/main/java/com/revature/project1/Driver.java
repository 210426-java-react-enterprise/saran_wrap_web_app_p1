package com.revature.project1;
import com.revature.project1.util.AppState;

public class Driver {
    private static AppState app = new AppState();

    public static void main(String[] args) {
        while(app.isAppRunning()){
            app.getRouter().navigate("/welcome");
        }

    }
    //getter for app
    public static AppState app(){
        return app;
    }

}
