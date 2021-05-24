package com.revature.project0.models;
//@Entity(name = "app_users")
public class AppUser {
    //@Column(name= "username")
    private String username;

    //@Column(name= "password")
    private String password;

    //@Column(name = "email")
    private String email;

    //@Column(name = "first_name")
    private String firstName;

    //@Column(name "last_name")
    private String lastName;

    //@Column(name = "age")
    private int age;

    //@Id
    //@Column(name= "user_id")
    private int id;

    public  AppUser(){
        super();
    }
    public AppUser(String username,String password,String email,String firstName,String lastName,int age){
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

    public String toFileString() {
        return String.format("%s;%s;%s;%s;%s;%d", username, password, email, firstName, lastName, age);
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AppUser{");
        sb.append("username='").append(username).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", age=").append(age);
        sb.append('}');
        return sb.toString();
    }
}
