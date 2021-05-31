package com.revature.project1.webServices.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.project1.daos.UserDAO;
import com.revature.project1.dtos.AppUserRegisterDTO;
import com.revature.project1.dtos.Credentials;
import com.revature.project1.exception.InvalidRequestException;
import com.revature.project1.exception.ResourceNotFoundException;
import com.revature.project1.models.AppUser;
import com.revature.project1.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

public class UserServlet extends HttpServlet {

    private UserService userService;

    public UserServlet(UserService userService){
        this.userService = userService;
    }
    //post CREATE - INSERT
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        PrintWriter writer = resp.getWriter();
        resp.setStatus(200);
        resp.setContentType("application/json");

        try {

            AppUser newUser = mapper.readValue(req.getInputStream(),AppUser.class);
            writer.write("credentials obj: " + String.valueOf(newUser ) +
                    "\ncred uname: " + newUser .getUsername()
                    + "\n cred pword: " + newUser .getPassword() + "\n");
            resp.setStatus(200);
            userService.register(newUser);
            //throwing auth error here:issue is that userDao is not finding my user
            writer.write(mapper.writeValueAsString(newUser));

            //req.getSession().setAttribute("this-user", authUser);
            resp.setStatus(200);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    //get READ - SELECT
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");
        List<AppUser> allUsers = userService.getAllUsers();
        writer.write(mapper.writeValueAsString(allUsers));
        resp.setStatus(200);

//        HttpSession session = req.getSession(false);
////        //AppUser requestingUser = (session == null) ? null : (AppUser) session.getAttribute("this-user");
////        AppUser c
////        if (requestingUser == null) {
////            //System.out.println(userService.getAllUsers());
////            resp.setStatus(401);
//////            return;
////        } else if (!requestingUser.getUsername().equals("wsingleton")) {
////            resp.setStatus(403);
//////            return;
////        }
//
//        String userIdParam = req.getParameter("id");
//
//        try {
//            if (userIdParam == null) {
//                List<AppUser> users = userService.getAllUsers();
//                writer.write(mapper.writeValueAsString(users));
//            } else {
//                AppUser user = userService.getUserById(Integer.parseInt(userIdParam));
//                writer.write(mapper.writeValueAsString(user));
//            }
//        } catch (ResourceNotFoundException e) {
//            resp.setStatus(404);
//        } catch (InvalidRequestException e) {
//            resp.setStatus(400);
//        }

    }


    //put UPDATE - UPDATE
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        AppUser userToBeDeactivated = (AppUser) req.getSession().getAttribute("this-user");
        try {
            userService.updateUserStatus(userToBeDeactivated);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //writer.write(mapper.writeValueAsString(allUsers));
        resp.setStatus(200);
    }

    //delete DELETE - DELETE
//    @Override
//    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        //dispatcher.dispatch(req, resp);
//    }

}
