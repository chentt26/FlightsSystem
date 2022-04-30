package com.example.flightsSystem.pojos;

import org.springframework.stereotype.Component;

@Component
public class Users {
  public   int id,userRole;
  public   String userName,password,email;

    public Users(int userRole, String userName, String password, String email) {
        this.userRole = userRole;
        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    public Users(int id, int userRole, String userName, String password, String email) {
        this.id = id;
        this.userRole = userRole;
        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", userRole=" + userRole +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
    public Users(){

    }
}
