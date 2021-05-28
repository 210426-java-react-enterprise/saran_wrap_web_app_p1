package com.revature.project1.dtos;

public class AppUserRegisterDTO {

    private int id;

    //@Column(name = "username", nullable = false)
    private String username;

    //@Column(name = "password", nullable = false)
    private String password;

    //@Column(name = "email", nullable = false)
    private String email;

    //@Column(name = "first_name", nullable = false)
    private String firstName;

    //@Column(name = "last_name", nullable = false)
    private String lastName;

    //@Column(name = "user_age", nullable = false)
    private int age;

    public  AppUserRegisterDTO(){
        super();
    }

    public AppUserRegisterDTO(String username,String password,String email,String firstName,String lastName,int age){
        System.out.println("AppUser invoked!");
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.id = id;
    }
    public String getUsername(){
        return username;
    }
    public void setUsername(String username){ this.username = username; }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public void setId(int id) { this.id = id; }
    public int getId() { return id; }

}
