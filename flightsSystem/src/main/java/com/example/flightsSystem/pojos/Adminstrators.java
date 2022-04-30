package com.example.flightsSystem.pojos;

import org.springframework.stereotype.Component;

@Component
public class Adminstrators {
   public int id,userId;
    public String firstName,lastName;

    public Adminstrators(int userId, String firstName, String lastName) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Adminstrators(int id, int userId, String firstName, String lastName) {
        this.id = id;
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
    }
public Adminstrators(){}

    @Override
    public String toString() {
        return "Adminstrators{" +
                "id=" + id +
                ", userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
