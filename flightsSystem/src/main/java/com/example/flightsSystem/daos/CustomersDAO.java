package com.example.flightsSystem.daos;

import com.example.flightsSystem.pojos.Customers;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
@Component
public class CustomersDAO implements Dao<Customers> {
    Connection connection=Connections.getConnection();
    Statement statement=Connections.getStatement();

    @Override
    public Customers get(int id) {
        Customers customer = null;
        try {
            var result = statement.executeQuery
                    ("SELECT * FROM Customers WHERE id=" + id);
            result.next();
            customer = new Customers(result.getInt("Id")
                    , result.getInt("User_id")
                    , result.getString("First_name")
                    , result.getString("Last_name")
                    , result.getString("Adress")
                    , result.getString("Phone_No")
                    , result.getString("Credit_Card_No")

            );


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;

    }

    @Override
    public List<Customers> getAll() {
        Customers customer = null;
        List<Customers> customers = new ArrayList<>();
        try {
            var result = statement.executeQuery
                    ("SELECT * FROM Customers");
            while (result.next()) {
                customer = new Customers(result.getInt("Id")
                        , result.getInt("User_id")
                        , result.getString("First_name")
                        , result.getString("Last_name")
                        , result.getString("Adress")
                        , result.getString("Phone_No")
                        , result.getString("Credit_Card_No")

                );
                customers.add(customer);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }


    @Override
    public void add(Customers customers) {
        try {
            statement.executeUpdate("INSERT INTO Customers (First_name,Last_name,Adress,Phone_No,Credit_Card_No,User_Id) " +
                    "VALUES " +
                    "(\'" + customers.firstName + "\','" + customers.lastName + "','" + customers.address + "','" + customers.phoneNo + "','" + customers.creditCardNo +"',"+customers.userId+ ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void remove(Customers customers) {
        int id = customers.id;
        try {
            statement.executeUpdate("DELETE from Customers WHERE id = " + id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }



    @Override
    public void update(Customers customers) {
        try {
            statement.executeUpdate("UPDATE Customers SET first_name= \'" + customers.firstName + "\', last_name='" + customers.lastName + "'" +
                    ",Adress='" + customers.address + "', Phone_No =" + customers.phoneNo +   ",Credit_Card_No='" + customers.creditCardNo+ "'" +",user_id="+customers.userId+
                    "where id=" + customers.id + "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    ///stored producer
    public Customers getCustomerByUsername(String username) {
        Customers customer = null;
        try {
            var result = statement.executeQuery
                    ("SELECT * FROM get_customer_by_username(\'" + username+"')");
            result.next();
            customer = new Customers(result.getInt("Id")
                    , result.getInt("User_id")
                    , result.getString("First_name")
                    , result.getString("Last_name")
                    , result.getString("Adress")
                    , result.getString("Phone_No")
                    , result.getString("Credit_Card_No")

            );


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;

    }
}
