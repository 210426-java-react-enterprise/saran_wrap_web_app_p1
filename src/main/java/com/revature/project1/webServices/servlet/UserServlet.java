package com.revature.project1.webServices.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.project1.models.AppUser;
import com.revature.project1.services.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class UserServlet extends HttpServlet {

//    private UserService userService;
    private Service userService;

//    public UserServlet(UserService userService){
    public UserServlet(Service service){
//        this.userService = userService;
        this.userService = service;
    }

    //post CREATE - INSERT
    //registers a new customer
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        PrintWriter writer = resp.getWriter();

        try {

            AppUser newUser = mapper.readValue(req.getInputStream(),AppUser.class);
            writer.write("credentials obj: " + String.valueOf(newUser ) +
                    "\ncred uname: " + newUser .getUsername()
                    + "\n cred pword: " + newUser .getPassword() + "\n");
            userService.register(newUser);
            resp.setContentType("application/json");
            writer.write(mapper.writeValueAsString(newUser));
            resp.setStatus(200);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    //get READ - SELECT
    //Grabs all active users and displays them in json format
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");
        List<AppUser> allUsers = userService.getAllUsers();
        writer.write(mapper.writeValueAsString(allUsers));
        resp.setStatus(200);

    }


    //put UPDATE - UPDATE
    //deactivates a user account by setting user_status to inactive
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        PrintWriter writer = resp.getWriter();
//        AppUser userToBeDeactivated = (AppUser) req.getSession().getAttribute("this-user");
        AppUser userToBeDeactivated = mapper.readValue(req.getInputStream(),AppUser.class);
        userService.softDelete(userToBeDeactivated, "inactive");
        //writer.write(mapper.writeValueAsString(allUsers));
        resp.setStatus(200);
    }

}
