package com.revature.project1.services;

import com.revature.project1.daos.UserDAO;
//import com.revature.project1.exception.AuthenticationException;
import com.revature.project1.models.AppUser;
import com.revature.project1.exception.*;
import com.revature.project1.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class UserService {
    private UserDAO userDao;

    public UserService(UserDAO userDao){
        this.userDao = userDao;
    }
    public List<AppUser> getAllUsers() {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            return userDao.findAllUsers(conn);
        }  catch (SQLException | DataSourceException e) {
            throw new ResourceNotFoundException();
        }

    }

    public AppUser authenticate(String username,String password) throws AuthenticationException{
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            //add connection to loginValidation will break loginScreen
            //Figure out why orElseThrow isnt functioning
            AppUser defaultUser = new AppUser();
            if ((userDao.loginValidation(conn,username,password) != null)){
                return userDao.loginValidation(conn, username, password);
            }
            return defaultUser;

        }catch( SQLException | DataSourceException e){
            e.printStackTrace();
            throw new AuthenticationException("Unable to authenticate User with provided credentials");
        }
    }
    public void register(AppUser newUser) throws InvalidRequestException, ResourcePersistenceException{
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            if (!userDao.isUsernameAvailable(conn, newUser.getUsername())) {
                throw new UsernameUnavailableException();
            }

            if (!userDao.isEmailAvailable(conn, newUser.getEmail())) {
                throw new EmailUnavailableException();
            }

            userDao.save(conn, newUser);
            //conn.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }catch(UsernameUnavailableException | EmailUnavailableException e){
            throw new ResourcePersistenceException(e.getMessage());
        }
    }
    private boolean isUserValid(AppUser user) {
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
    public AppUser getUserById(int id) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            return userDao.findUserById(conn, id)
                    .orElseThrow(ResourceNotFoundException::new);

        }  catch (SQLException | DataSourceException e) {
            throw new ResourceNotFoundException();
        } catch (NumberFormatException e) {
            throw new InvalidRequestException("An illegal value was provided!");
        }
    }

}

