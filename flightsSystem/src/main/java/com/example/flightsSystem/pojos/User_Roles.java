package com.example.flightsSystem.pojos;

public class User_Roles {
  public   int id;
    public String roleName;

    public User_Roles(String roleName) {
        this.roleName = roleName;
    }

    public User_Roles(int id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "User_Roles{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
