package com.revature.project1.services;

import com.revature.project1.daos.UserDAO;
import com.revature.project1.exception.*;
import com.revature.project1.models.AppUser;
import com.revature.project1.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import com.revature.project1.services.SaranServices;

public class Service {
    private UserDAO userDao;
    private SaranServices ss = new SaranServices();

    public Service(UserDAO userDao) {
        this.userDao = userDao;
    }

    public void register(AppUser newUser) throws InvalidRequestException, ResourcePersistenceException {
        try (Connection conn = ConnectionFactory.getInstance()
                                                .getConnection()) {
            if (!userDao.isUsernameAvailable(conn, newUser.getUsername())) {
                throw new UsernameUnavailableException();
            }

            if (!userDao.isEmailAvailable(conn, newUser.getEmail())) {
                throw new EmailUnavailableException();
            }

            ss.insertInDB(newUser);
            //conn.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (UsernameUnavailableException | EmailUnavailableException e) {
            throw new ResourcePersistenceException(e.getMessage());
        }
    }
}