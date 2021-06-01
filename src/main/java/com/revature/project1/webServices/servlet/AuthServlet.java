package com.revature.project1.webServices.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.revature.project1.exception.AuthenticationException;
import com.revature.project1.models.AppUser;
import com.revature.project1.services.Service;
import com.revature.project1.services.UserService;
import com.revature.project1.dtos.Credentials;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class AuthServlet extends HttpServlet {

//    private final UserService userService;
    private final Service userService;
    private HttpSession session;

//    public AuthServlet(UserService userService) {
//        this.userService = userService;
//    }
    public AuthServlet(Service service) {
        this.userService = service;
    }

    //signs user out of their account
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);

        if (session != null) {
            this.session.invalidate();
        }
    }

    //securely logs in a valid user
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ObjectMapper mapper = new ObjectMapper();
        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");

        try {

            Credentials creds = mapper.readValue(req.getInputStream(), Credentials.class);
            writer.write("credentials obj: "+String.valueOf(creds)+
                    "\ncred uname: "+creds.getUsername()
                    +"\n cred pword: "+creds.getPassword()+"\n");
            AppUser authUser = userService.authenticate(creds.getUsername(), creds.getPassword());
            //throwing auth error here:issue is that userDao is not finding my user
            writer.write(mapper.writeValueAsString(authUser));

            session = req.getSession();
            session.setAttribute("this-user", authUser);
            resp.setStatus(200);


        } catch (MismatchedInputException e) {

            resp.setStatus(400);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            resp.setStatus(401);
        } catch (Exception e) {
            e.printStackTrace();

            resp.setStatus(500);
        }
    }

    //put UPDATE - UPDATE
    //deactivates a user account by setting user_status to inactive
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HttpSession session;
            session = req.getSession(false);
            if (session == null) session = this.session;
            AppUser userToBeDeactivated = (AppUser) session.getAttribute("this-user");
            userService.softDelete(userToBeDeactivated);
            session.invalidate();
            resp.setStatus(200);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
