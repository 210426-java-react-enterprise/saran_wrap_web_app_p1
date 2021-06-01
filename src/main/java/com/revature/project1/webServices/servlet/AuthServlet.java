package com.revature.project1.webServices.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.revature.project1.exception.AuthenticationException;
import com.revature.project1.models.AppUser;
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

    private final UserService userService;

    public AuthServlet(UserService userService) {
        this.userService = userService;
    }
    //signs user out of their account
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);

        if (session != null) {
            session.invalidate();
        }
    }

    //securely logs in a valid user
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ObjectMapper mapper = new ObjectMapper();
        PrintWriter writer = resp.getWriter();
        resp.setStatus(200);
        resp.setContentType("application/json");

        try {

            Credentials creds = mapper.readValue(req.getInputStream(), Credentials.class);
            writer.write("credentials obj: "+String.valueOf(creds)+
                    "\ncred uname: "+creds.getUsername()
                    +"\n cred pword: "+creds.getPassword()+"\n");
            resp.setStatus(200);
            AppUser authUser = userService.authenticate(creds.getUsername(), creds.getPassword());

            writer.write(mapper.writeValueAsString(authUser));

            req.getSession().setAttribute("this-user", authUser);
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
}
