package com.revature.project1.webServices;

import com.revature.project1.daos.UserDAO;
import com.revature.project1.dbentry.SqlCreation;
import com.revature.project1.services.SaranServices;
import com.revature.project1.services.Service;
import com.revature.project1.services.UserService;
import com.revature.project1.webServices.servlet.AuthServlet;
import com.revature.project1.webServices.servlet.UserServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

// Tied to startup and shutdown of Tomcat. Implement the SCL and put your logic in
// the overridden methods. Make sure you inform Tomcat of this class by including
// it in your deployment descriptor(web.xml) under the listener tag
public class DependencyLoaderListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        UserDAO userDAO = new UserDAO();
        SaranServices ss = new SaranServices(new SqlCreation());

//        UserService userService = new UserService(userDAO);
        Service userService = new Service(ss);

//        AuthServlet authServlet = new AuthServlet(userService);
        UserServlet userServlet = new UserServlet(userService);

        ServletContext context = servletContextEvent.getServletContext();
//        context.addServlet("AuthServlet",authServlet).addMapping("/auth");
        context.addServlet("UserServlet",userServlet).addMapping("/users/*");

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
