package com.revature.project1.screens;
import com.revature.project1.daos.UserDAO;
import com.revature.project1.models.AppUser;
import java.io.BufferedReader;
import com.revature.project1.services.UserService;
import com.revature.project1.exception.*;

public class RegisterScreen extends Screen {

    private UserDAO userDao = new UserDAO();
    private UserService userService;
    private BufferedReader consoleReader;

    public RegisterScreen(BufferedReader consoleReader, UserService userService) {
        super("RegisterScreen","/register");
        this.consoleReader = consoleReader;
        this.userService = userService;
    }

    public void render() {

        String firstName;
        String lastName;
        String email;
        String username;
        String password;
        Boolean strongPassword;
        Boolean isEmailAvailible;
        Boolean isUsernameAvailible;
        int age;

        try {
            System.out.println("Sign up for B.B.B!");
            System.out.println("+-------------------------+");

            System.out.print("First name: ");
            firstName = consoleReader.readLine(); // throws Exception here

            System.out.print("Last name: ");
            lastName = consoleReader.readLine();

            System.out.print("Email: ");
            email = consoleReader.readLine();

            System.out.print("Username: ");
            username = consoleReader.readLine();

            System.out.print("Password: ");
            password = consoleReader.readLine();
            strongPassword = userDao.isPasswordSecure(password);
            while (!strongPassword) {
                System.out.print("Password(at least 8 characters and 1 number: ");
                password = consoleReader.readLine();
                strongPassword = userDao.isPasswordSecure(password);
            }
            System.out.print("Age: ");
            age = Integer.parseInt(consoleReader.readLine());


            AppUser newUser = new AppUser(username, password, email, firstName, lastName, age);
            //userDao.save(newUser);
            userService.register(newUser);
            System.out.println("Registration Succesful");
        } catch (NumberFormatException nfe) {
            // do something about these!
            System.err.println("You provided an incorrect value for your age! Please try again!");
            this.render(); // this breaks some stuff! we will need to fix this
        } catch (InvalidRequestException e){
            System.out.println("This is an invalid request");
            //e.printStackTrace();
        }catch (Exception e) {
            System.err.println("IOException in register screen");
            e.printStackTrace(); // include this line while developing/debugging the app!
            // should be logged to a file in a production environment
        }

    }

}
