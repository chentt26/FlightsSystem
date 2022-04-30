package com.example.flightsSystem.daos;

import com.example.flightsSystem.pojos.Countries;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
@Component
public class CountriesDAO implements Dao<Countries> {
    Connection connection=Connections.getConnection();
    Statement statement=Connections.getStatement();

    @Override
    public Countries get(int id) {
        Countries country=null;
        try {
            var result = statement.executeQuery
                    ("SELECT * FROM Countries WHERE id=" + id);
            result.next();
            country=new Countries(result.getInt("id"),
                    result.getString("Name")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return country;
    }

    @Override
    public List<Countries> getAll() {
        Countries country=null;
        List<Countries>countries=new ArrayList<>();
        try {
            var result = statement.executeQuery
                    ("SELECT * FROM Countries" );
            while (result.next()) {
                country=new Countries(result.getInt("id"),
                        result.getString("Name")
                );
                countries.add(country);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return countries;    }

    @Override
    public void add(Countries countries) {
        try {
            statement.executeUpdate("INSERT INTO Countries (Name) " +
                    "VALUES " +
                    "(\'" + countries.name + "\')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Countries countries) {
        int id=countries.id;
        try {
            statement.executeUpdate("DELETE from Countries WHERE id = " + id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(Countries countries) {
        try {
            statement.executeUpdate("UPDATE Countries SET Name=\'" + countries.name + "\'"+"where id=" + countries.id + "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
