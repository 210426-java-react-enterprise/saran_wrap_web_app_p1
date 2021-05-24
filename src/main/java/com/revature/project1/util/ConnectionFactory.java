package com.revature.project1.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

//singleton design pattern->will prevent more than one instances to be made
//factory design pattern-> abstracts the messy work of connecting
public class ConnectionFactory {
    //one instance of the factory to be made
    private static ConnectionFactory connectionFactory;//lazy singleton
    private Properties props = new Properties();

    //lazy because it will only create the instance only
    // when the getInstance method is called
    //creates and returns our ONE connection factory instance
    public static ConnectionFactory getInstance() {
        if (connectionFactory == null) {
            connectionFactory = new ConnectionFactory();
        }
        return connectionFactory;
    }

    //this static block grabs the proper Driverclass and loads it into memory
    //so it has the right driver file to connect our data base
    //loads driver class
    static {
        try{
            Class.forName("org.postgresql.Driver");
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    //this function loads our properties file (which holds our database credentials)
    private ConnectionFactory(){
        /*
        // commented out because we're using system variables
        try {
            props.load(new FileReader("src/main/resources/application.properties"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
         */
    }

    public Connection getConnection(){
        Connection conn = null;
        try{
            /*
            conn = DriverManager.getConnection(
                    System.getenv("host-url"),
                    System.getenv("username"),
                    System.getenv("password")
            );
            */

            conn = DriverManager.getConnection(
                    System.getenv("host-url"),
                    System.getenv("username"),
                    System.getenv("password")
            );


        }catch(SQLException sqle){
            sqle.printStackTrace();

        }
        return conn;
    }
}
