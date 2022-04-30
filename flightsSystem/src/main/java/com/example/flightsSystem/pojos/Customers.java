package com.example.flightsSystem.pojos;

import org.springframework.stereotype.Component;

import java.util.Objects;
@Component
public class Customers {
  public   int id,userId;
  public   String firstName,lastName,address,phoneNo,creditCardNo;

    public Customers(int userId, String firstName, String lastName, String address, String phoneNo, String creditCardNo) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNo = phoneNo;
        this.creditCardNo = creditCardNo;
    }

    public Customers(int id, int userId, String firstName, String lastName, String address, String phoneNo, String creditCardNo) {
        this.id = id;
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNo = phoneNo;
        this.creditCardNo = creditCardNo;
    }
public Customers(){

}
    @Override
    public String toString() {
        return "Customers{" +
                "id=" + id +
                ", userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", creditCardNo='" + creditCardNo + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customers customers = (Customers) o;
        return id == customers.id && userId == customers.userId && Objects.equals(firstName, customers.firstName) && Objects.equals(lastName, customers.lastName) && Objects.equals(address, customers.address) && Objects.equals(phoneNo, customers.phoneNo) && Objects.equals(creditCardNo, customers.creditCardNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, firstName, lastName, address, phoneNo, creditCardNo);
    }
}
