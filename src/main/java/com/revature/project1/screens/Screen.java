package com.revature.project1.screens;

import com.revature.project1.models.AppUser;
//stencil for Screens
public abstract class Screen {
    private AppUser user;
    protected String name;
    protected String route;

    public Screen(String name, String route){
        this.name = name;
        this.route = route;
    }
    public Screen(String name, String route, AppUser user){
        this.name = name;
        this.route = route;
        this.user = user;
    }
    public String getName(){
        return name;
    }
    public String getRoute(){
        return route;
    }

    public abstract void render();
    //may not actually need this
    //TO-DO: FIGURE OUT WAY TO NAVIGATE TO THE HOMESCREEN WHILE PASSING IN USER
    public void render(AppUser user){

    };
}