package com.revature.project1.webServices.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.project1.models.AppUser;
import com.revature.project1.services.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class UserServlet extends HttpServlet {

    private Service userService;

    public UserServlet(Service service){
        this.userService = service;
    }

    /**
     * Registers a new customer using json
     * @param req a HttpServletRequest
     * @param resp a HttpServletResponse
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        PrintWriter writer = resp.getWriter();
        boolean success = true;

        try {

            AppUser newUser = mapper.readValue(req.getInputStream(),AppUser.class);
            writer.write("credentials obj: " + String.valueOf(newUser ) +
                    "\ncred uname: " + newUser .getUsername()
                    + "\n cred pword: " + newUser .getPassword() + "\n");
            success = userService.register(newUser);
            if (success) {
                resp.setContentType("application/json");
                writer.write(mapper.writeValueAsString(newUser));
                resp.setStatus(200);
            } else resp.setStatus(401);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * Grabs all active users and displays them in json format
     * @param req a HttpServletRequest
     * @param resp a HttpServletResponse
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");
        List<AppUser> allUsers = userService.getAllUsers();
        writer.write(mapper.writeValueAsString(allUsers));
        resp.setStatus(200);

    }
}
