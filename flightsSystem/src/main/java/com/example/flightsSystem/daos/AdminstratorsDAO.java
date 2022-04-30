package com.example.flightsSystem.daos;

import com.example.flightsSystem.pojos.Adminstrators;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class AdminstratorsDAO implements Dao<Adminstrators> {
    Connection connection=Connections.getConnection();
    Statement statement=Connections.getStatement();

    @Override
    public Adminstrators get(int id) {
       Adminstrators adminstrator = null;
        try {
            var result = statement.executeQuery
                    ("SELECT * FROM Administrators WHERE id=" + id);
            result.next();
            adminstrator = new Adminstrators(result.getInt("id")
                    , result.getInt("user_id")
                    , result.getString("first_name")
                    , result.getString("last_name")
            );


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adminstrator;
    }

    @Override
    public List<Adminstrators> getAll() {
        Adminstrators adminstrator = null;
        List<Adminstrators> adminstrators = new ArrayList<>();
        try {
            var result = statement.executeQuery
                    ("SELECT * FROM Administrators");
            while (result.next()) {
                adminstrator = new Adminstrators(result.getInt("id")
                        , result.getInt("user_id")
                        , result.getString("first_name")
                        , result.getString("last_name")
                );
                adminstrators.add(adminstrator);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adminstrators;    }

    @Override
    public void add(Adminstrators adminstrators) {
        try {
            statement.executeUpdate("INSERT INTO Administrators (first_name,last_name,user_id) " +
                    "VALUES " +
                    "(\'" + adminstrators.firstName + "\','" + adminstrators.lastName + "'," + adminstrators.userId +")");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void remove(Adminstrators adminstrators) {
        int id = adminstrators.id;
        try {
            statement.executeUpdate("DELETE from Administrators WHERE id = " + id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    @Override
    public void update(Adminstrators adminstrators) {
        try {
            statement.executeUpdate("UPDATE Administrators SET first_name= \'" + adminstrators.firstName + "\', last_name='" + adminstrators.lastName + "'" +
                    ",user_id=" + adminstrators.userId + "Where id =" + adminstrators.id + "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    ///stored producer
    public Adminstrators getAdminByUsername(String username) {
        Adminstrators adminstrator = null;
        try {
            var result = statement.executeQuery
                    ("SELECT * FROM get_admin_by_username(\'" + username+"')");
            result.next();
            adminstrator = new Adminstrators(result.getInt("id")
                    , result.getInt("user_id")
                    , result.getString("first_name")
                    , result.getString("last_name")
            );


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adminstrator;
    }

    }

