package com.revature.project1.services;
import com.revature.project1.daos.UserDAO;
import com.revature.project1.models.AppUser;
import com.revature.project1.exception.ResourcePersistenceException;
import com.revature.project1.exception.InvalidRequestException;

public class UserService {
    private UserDAO userDao;

    public UserService(UserDAO userDao){
        this.userDao = userDao;
    }
    public void register(AppUser newUser){
        //validates entries upon registration using our own custom exceptions
        if (!isUserValid(newUser)){
            throw new InvalidRequestException("Invalid new user data provided! ");
        }
        if (!userDao.isUsernameAvailible(newUser.getUsername())){
            throw new ResourcePersistenceException("Username taken.");
        }
        if (!userDao.isEmailAvailible(newUser.getEmail())){
            throw new ResourcePersistenceException("Email has already been used. ");
        }
        if (!userDao.isPasswordSecure(newUser.getPassword())){
            throw new ResourcePersistenceException("Password is not secure enough. ");
        }
        userDao.save(newUser);
    }
    //Makes sure user info input is valid
    public boolean isUserValid(AppUser user){
        if (user == null) return false;
        if (user.getUsername()==null|| user.getUsername().trim().isEmpty() || user.getUsername().length() > 20 )  return false;
        if (user.getPassword()==null|| user.getPassword().trim().isEmpty() || user.getPassword().length() > 255 ) return false;
        if (user.getEmail()==null|| user.getEmail().trim().isEmpty() || user.getEmail().length() > 255 ) return false;
        if (user.getFirstName()==null|| user.getFirstName().trim().isEmpty() || user.getFirstName().length() > 25 ) return false;
        if (user.getLastName()==null|| user.getLastName().trim().isEmpty() || user.getLastName().length() > 25 ) return false;
        if (user.getAge()<0 )  return false;
        return true;
    }

}

