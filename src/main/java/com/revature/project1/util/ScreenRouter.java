package com.revature.project1.util;

import com.revature.project1.models.AppUser;
import com.revature.project1.screens.Screen;
import com.revature.project1.util.structures.LinkedList;

//Aggregation of multiple screen routers
public class ScreenRouter {
    private LinkedList<Screen> screens = new LinkedList<Screen>();
    //builder patern, allows us to chain build

    //add indvidual screens to our list
    public ScreenRouter addScreen(Screen screen){
        screens.add(screen);
        return this;
    }

    public void navigate(String route){
        for (int i = 0; i < screens.size(); i++) {

            Screen screen = screens.get(i);

            if( screen.getRoute().equals(route) ){
                screen.render();
            }

        }
    }
    //Overloading navigateMethod
    public void navigate(String route, AppUser user){
        for (int i = 0; i < screens.size(); i++) {

            Screen screen = screens.get(i);

            if( screen.getRoute().equals(route) ){
                screen.render(user);
            }

        }
    }



}
