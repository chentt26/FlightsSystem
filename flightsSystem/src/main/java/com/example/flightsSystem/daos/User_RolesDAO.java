package com.example.flightsSystem.daos;

import com.example.flightsSystem.pojos.User_Roles;
import com.example.flightsSystem.pojos.User_Roles;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class User_RolesDAO implements Dao<User_Roles> {
    Connection connection=Connections.getConnection();
    Statement statement=Connections.getStatement();

    @Override
    public User_Roles get(int id) {
User_Roles ur=null;
    try {
            var result = statement.executeQuery
                    ("SELECT * FROM User_Roles WHERE id=" + id);
            result.next();
        ur=new User_Roles(result.getInt("id"),
                result.getString("Role_Name")
        );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ur;
    }


    @Override
    public List<User_Roles> getAll() {
        User_Roles ur=null;
        List<User_Roles>urs=new ArrayList<>();
        try {
            var result = statement.executeQuery
                    ("SELECT * FROM User_Roles" );
            while (result.next()) {
                ur=new User_Roles(result.getInt("id"),
                        result.getString("Role_Name")
                );
                urs.add(ur);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return urs;    }


    @Override
    public void add(User_Roles user_roles) {
        try {
            statement.executeUpdate("INSERT INTO User_Roles (Role_name) " +
                    "VALUES " +
                    "(\'" + user_roles.roleName + "\')");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void remove(User_Roles user_roles) {
int id=user_roles.id;
        try {
            statement.executeUpdate("DELETE from User_Roles WHERE id = " + id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    @Override
    public void update(User_Roles user_roles) {
        try {
            statement.executeUpdate("UPDATE User_Roles SET Role_Name=\'" + user_roles.roleName + "\'"+"where id=" + user_roles.id + "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
