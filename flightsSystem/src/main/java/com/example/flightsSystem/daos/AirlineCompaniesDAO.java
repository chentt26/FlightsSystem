package com.example.flightsSystem.daos;

import com.example.flightsSystem.pojos.AirlineCompanies;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
@Component
public class AirlineCompaniesDAO implements Dao<AirlineCompanies> {
    Connection connection=Connections.getConnection();
    Statement statement=Connections.getStatement();

    @Override
    public AirlineCompanies get(int id) {
        AirlineCompanies airlineCompany= null;
        try {
            var result = statement.executeQuery
                    ("SELECT * FROM Airline_Companies WHERE id=" + id);
            result.next();
            airlineCompany = new AirlineCompanies(result.getInt("id")
                    , result.getInt("country_id")
                    , result.getInt("user_id")
                    , result.getString("Name")
            );


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return airlineCompany;
    }

    @Override
    public List<AirlineCompanies> getAll() {
        AirlineCompanies airlineCompany= null;
        List<AirlineCompanies> airlineCompanies = new ArrayList<>();
        try {
            var result = statement.executeQuery
                    ("SELECT * FROM Airline_Companies");
            while (result.next()) {
                airlineCompany = new AirlineCompanies(result.getInt("id")
                        , result.getInt("country_id")
                        , result.getInt("user_id")
                        , result.getString("Name")
                );
                airlineCompanies.add(airlineCompany);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return airlineCompanies;    }

    @Override
    public void add(AirlineCompanies airlineCompanies) {
        try {
            statement.executeUpdate("INSERT INTO Airline_Companies (Name,Country_Id,User_Id) " +
                    "VALUES " +
                    "(\'" + airlineCompanies.name + "\'," + airlineCompanies.countryId + "," + airlineCompanies.userId +")");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void remove(AirlineCompanies airlineCompanies) {
        int id = airlineCompanies.id;
        try {
            statement.executeUpdate("DELETE from Airline_Companies WHERE id = " + id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(AirlineCompanies airlineCompanies) {
        try {
            statement.executeUpdate("UPDATE Airline_Companies SET Name= \'" + airlineCompanies.name + "\', Country_Id=" + airlineCompanies.countryId +
                    ",user_id=" + airlineCompanies.userId + "Where id =" + airlineCompanies.id + "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    ///stored producer
    public List<AirlineCompanies> getAirlinesByCountry(int country_id){
        AirlineCompanies airlineCompany= null;
        List<AirlineCompanies> airlineCompanies = new ArrayList<>();
        try {
            var result = statement.executeQuery
                    ("SELECT * FROM Airline_Companies WHERE country_id="+country_id);
            while (result.next()) {
                airlineCompany = new AirlineCompanies(result.getInt("id")
                        , result.getInt("country_id")
                        , result.getInt("user_id")
                        , result.getString("Name")
                );
                airlineCompanies.add(airlineCompany);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return airlineCompanies;    }

    ///stored producer
    public AirlineCompanies getAirlineByUserName(String username) {
        AirlineCompanies airlineCompany= null;
        try {
            var result = statement.executeQuery
                    ("SELECT * FROM get_airline_by_username(\'" + username+"')");
            result.next();
            airlineCompany = new AirlineCompanies(result.getInt("id")
                    , result.getInt("country_id")
                    , result.getInt("user_id")
                    , result.getString("Name")
            );


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return airlineCompany;
    }

}

