package com.example.flightsSystem.daos;

import com.example.flightsSystem.pojos.Tickets;
import com.example.flightsSystem.pojos.Tickets;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
@Component
public class TicketsDAO implements Dao<Tickets> {
    Connection connection=Connections.getConnection();
    Statement statement=Connections.getStatement();

    @Override
    public Tickets get(int id) {
        Tickets tickets= null;
        try {
            var result = statement.executeQuery
                    ("SELECT * FROM Tickets WHERE id=" + id);
            result.next();
            tickets = new Tickets(result.getInt("Id")
                    , result.getInt("Customer_Id")
                    , result.getInt("Flight_Id")

            );


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tickets;
    }

    @Override
    public List<Tickets> getAll() {
        Tickets ticket= null;
        List<Tickets> tickets = new ArrayList<>();
        try {
            var result = statement.executeQuery
                    ("SELECT * FROM Tickets");
            while (result.next()) {
                ticket = new Tickets(result.getInt("Id")
                        , result.getInt("Customer_Id")
                        , result.getInt("Flight_Id")

                );
                tickets.add(ticket);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tickets;    }

    @Override
    public void add(Tickets tickets) {
        try {
            statement.executeUpdate("INSERT INTO Tickets (flight_Id,Customer_Id) " +
                    "VALUES " +
                    "(" + tickets.flightId + "," + tickets.customerId +")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Tickets tickets) {
        int id = tickets.id;
        try {
            statement.executeUpdate("DELETE from Tickets WHERE id = " + id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(Tickets tickets) {
        try {
            statement.executeUpdate("UPDATE Tickets SET Flight_Id=" + tickets.flightId + ",Customer_Id =" + tickets.customerId +
                     "Where id =" + tickets.id + "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    ///stored producer

    public List<Tickets>  getTicketsByCustomer(int customerId) {
        Tickets ticket= null;
        List<Tickets> tickets = new ArrayList<>();
        try {
            var result = statement.executeQuery
                    ("SELECT * FROM get_tickets_by_customer(" + customerId+")");
            while (result.next()) {
                ticket = new Tickets(result.getInt("Id")
                        , result.getInt("Customer_Id")
                        , result.getInt("Flight_Id")

                );
                tickets.add(ticket);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tickets;    }
public TicketsDAO(){

}


}
