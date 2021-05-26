package com.revature.project1.webServices.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.project1.daos.UserDAO;
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
import java.util.List;

public class UserServlet extends HttpServlet {

    private UserService userService;

    public UserServlet(UserService userService){
        this.userService = userService;
    }
    //post CREATE - INSERT
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    //get READ - SELECT
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");

        HttpSession session = req.getSession(false);
        AppUser requestingUser=(session == null) ? null :(AppUser)  session.getAttribute("this-user");

        if(requestingUser == null){
            resp.setStatus((401));
            return;
        }else if(!requestingUser.getUsername().equals("username")) {
            resp.setStatus(403);
        }

        String userIdParam = req.getParameter("id");

        try{
            if(userIdParam == null){
                List<AppUser> users = userService.getAllUsers();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    //put UPDATE - UPDATE
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //dispatcher.dispatch(req, resp);
    }

    //delete DELETE - DELETE
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //dispatcher.dispatch(req, resp);
    }

}
