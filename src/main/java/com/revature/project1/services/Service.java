package com.revature.project1.services;

import com.revature.project1.dbentry.SqlCreation;
import com.revature.project1.models.AppUser;

import java.util.ArrayList;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class Service {
    SqlCreation sql;
    private SaranServices ss = new SaranServices(sql);

    public Service(SaranServices ss) {
        this.ss = ss;
    }

    public boolean register(AppUser newUser) {
        boolean userValid = isUserValid(newUser);
        boolean passwordSecure = isPasswordSecure(newUser);
//        boolean usernameAvailable = isUsernameAvailable(newUser);
//        boolean emailAvailable = isEmailAvailable(newUser);

        if (userValid && passwordSecure) {
            ss.insertObject(newUser);
            return true;
        } else return false;
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
        if (!userList.isEmpty()){
            AppUser user = userList.get(0);
            return user;
        }
        else return new AppUser();
    }

    public void softDelete(AppUser user) {
        user.setUserStatus(false);
        String condition = "username = '"+user.getUsername()+"'";
        ss.updateObject(user, condition);
    }

    public boolean isUserValid(AppUser user) {
        Predicate<String> isNullOrEmpty = str -> str == null || str.trim().isEmpty();
        BiPredicate<String, Integer> lengthIsInvalid = (str, length) -> str.length() > length;

        if (user == null) return false;
        if (isNullOrEmpty.test(user.getUsername()) || lengthIsInvalid.test(user.getUsername(), 20)) return false;
        if (isNullOrEmpty.test(user.getPassword()) || lengthIsInvalid.test(user.getPassword(), 255)) return false;
        if (isNullOrEmpty.test(user.getEmail()) || lengthIsInvalid.test(user.getEmail(), 255)) return false;
        if (isNullOrEmpty.test(user.getFirstName()) || lengthIsInvalid.test(user.getFirstName(), 25)) return false;
        if (isNullOrEmpty.test(user.getLastName()) || lengthIsInvalid.test(user.getLastName(), 25)) return false;
        return user.getAge() >= 0;
    }

    public Boolean isPasswordSecure(AppUser verifyUser){
        String password = verifyUser.getPassword();
        boolean hasLetter = false;
        boolean hasDigit = false;

        if (password.length() < 8) return false;

        for (char character:password.toCharArray()) {
            if (Character.isLetter(character)) hasLetter = true;
            if (Character.isDigit(character)) hasDigit = true;
            if (hasLetter && hasDigit) return true;
        }

        return false;
    }

    public Boolean isUsernameAvailable(AppUser verifyUser) {
        String username = verifyUser.getUsername();
        if (username == null) return false;
        return ss.selectObject(AppUser.class, "username = '"+username+"'").isEmpty();
    }

    public Boolean isEmailAvailable(AppUser verifyUser) {
        String email = verifyUser.getEmail();

        if (email.length() < 5) return false;
        if (!email.contains("@")) return false;
        return ss.selectObject(AppUser.class, "email = '"+email+"'").isEmpty();
    }
}