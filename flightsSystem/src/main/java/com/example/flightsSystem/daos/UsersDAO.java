package com.example.flightsSystem.daos;

import com.example.flightsSystem.pojos.Users;
import com.example.flightsSystem.pojos.Users;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
@Component
public class UsersDAO implements Dao<Users> {
    Connection connection = Connections.getConnection();
    Statement statement = Connections.getStatement();

    @Override
    public Users get(int id) {
        Users user = null;
        try {
            var result = statement.executeQuery
                    ("SELECT * FROM Users WHERE id=" + id);
            result.next();
            user = new Users(result.getInt("id")
                    , result.getInt("user_role")
                    , result.getString("userName")
                    , result.getString("password")
                    , result.getString("email")
            );


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<Users> getAll() {
        Users user = null;
        List<Users> users = new ArrayList<>();
        try {
            var result = statement.executeQuery
                    ("SELECT * FROM Users");
            while (result.next()) {
                user = new Users(result.getInt("id")
                        , result.getInt("user_role")
                        , result.getString("userName")
                        , result.getString("password")
                        , result.getString("email")
                );
                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }


    @Override
    public void remove(Users users) {

        int id = users.id;
        try {
            statement.executeUpdate("DELETE from Users WHERE id = " + id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void add(Users users) {

        try {
            statement.executeUpdate("INSERT INTO Users (username,password,email,user_role) " +
                    "VALUES " +
                    "(\'" + users.userName + "\','" + users.password + "','" + users.email + "','" + users.userRole + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(Users users) {
        try {
            statement.executeUpdate("UPDATE Users SET userName= \'" + users.userName + "\', password='" + users.password + "'" +
                    ",email='" + users.email + "', user_role =" + users.userRole +
                    "where id=" + users.id + "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Users getUserByUsername(String username) {
        Users user = null;
        try {
            var result = statement.executeQuery
                    ("SELECT * FROM get_user_by_username(\'" + username+"')");
            result.next();
            user = new Users(result.getInt("id")
                    , result.getInt("user_role")
                    , result.getString("userName")
                    , result.getString("password")
                    , result.getString("email")
            );


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
  public   UsersDAO(){

  }
}
