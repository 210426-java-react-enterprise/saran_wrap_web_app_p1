package com.revature.project1.services;

import com.revature.project1.dbentry.SqlCreation;
import com.revature.project1.models.AppUser;

import java.util.ArrayList;

public class Service {
    SqlCreation sql;
    private SaranServices ss = new SaranServices(sql);

    public Service(SaranServices ss) {
        this.ss = ss;
    }

    public void register(AppUser newUser) {
            ss.insertObject(newUser);
    }

    public void getUser(String condition) {
        ss.selectObject(AppUser.class, condition);
    }

    public ArrayList<AppUser> getAllUsers() {
        return ss.selectAllObjects(AppUser.class);
    }

    public void update(AppUser newUser, String condition) {
        condition = "username = '"+newUser.getUsername()+"'";
        ss.updateObject(newUser, condition);
    }

    public void delete(String condition) {
        ss.deleteObject(AppUser.class, condition);
    }

    public AppUser authenticate(String username, String password) {
        String condition = "(username = '"+username+"' and password = '"+password+"')";
        ArrayList<AppUser> userList = ss.selectObject(AppUser.class, condition);
        AppUser user = userList.get(0);
        return user;
    }

    public void softDelete(AppUser user) {
        user.setUserStatus(false);
        String condition = "username = '"+user.getUsername()+"'";
        ss.updateObject(user, condition);
    }
}