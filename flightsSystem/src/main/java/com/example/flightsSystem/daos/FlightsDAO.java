package com.example.flightsSystem.daos;

import com.example.flightsSystem.pojos.Flights;
import com.example.flightsSystem.pojos.Customers;
import com.example.flightsSystem.pojos.Tickets;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Component
public class FlightsDAO implements Dao<Flights> {
    Connection connection = Connections.getConnection();
    Statement statement = Connections.getStatement();

    @Override
    public Flights get(int id) {
        Flights flight = null;
        try {
            var result = statement.executeQuery
                    ("SELECT * FROM Flights WHERE id=" + id);
            result.next();
            LocalDateTime departureTime, landingTime;
            departureTime = result.getTimestamp("Departure_Time").toLocalDateTime();
            landingTime = result.getTimestamp("Landing_Time").toLocalDateTime();
            flight = new Flights(result.getInt("Id")
                    , result.getInt("Airline_Company_Id")
                    , result.getInt("Origin_Country_Id")
                    , result.getInt("Destination_Country_Id")
                    , result.getInt("Remaining_Tickets")
                    , departureTime
                    , landingTime
            );


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flight;
    }

    @Override
    public List<Flights> getAll() {
        Flights flight = null;
        List<Flights> flights = new ArrayList<>();
        try {
            var result = statement.executeQuery
                    ("SELECT * FROM Flights");
            while (result.next()) {
                LocalDateTime departureTime, landingTime;
                departureTime = result.getTimestamp("Departure_Time").toLocalDateTime();
                landingTime = result.getTimestamp("Landing_Time").toLocalDateTime();
                flight = new Flights(result.getInt("Id")
                        , result.getInt("Airline_Company_Id")
                        , result.getInt("Origin_Country_Id")
                        , result.getInt("Destination_Country_Id")
                        , result.getInt("Remaining_Tickets")
                        , departureTime
                        , landingTime
                );

                flights.add(flight);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flights;
    }


    @Override
    public void add(Flights flights) {
        LocalDateTime departureTime = flights.departureTime, landingTime = flights.landingTime;
        Timestamp dt = Timestamp.valueOf(departureTime);
        Timestamp lt = Timestamp.valueOf(landingTime);
        try {
            statement.executeUpdate("INSERT INTO Flights (Airline_Company_Id,Origin_Country_Id,Destination_Country_Id,Remaining_Tickets,Departure_Time,Landing_Time) " +
                    "VALUES " +
                    "(" + flights.airLineCompanyId + "," + flights.originCountryId + "," + flights.destinationCountryId + "," + flights.remainingTickets + ",'" + dt + "','" + lt + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Flights flights) {
        int id = flights.id;
        try {
            statement.executeUpdate("DELETE from Flights WHERE id = " + id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(Flights flights) {
        try {
            statement.executeUpdate("UPDATE Flights SET Airline_Company_Id= " + flights.airLineCompanyId + ", Origin_Country_Id=" + flights.originCountryId + ", Destination_Country_Id=" + flights.destinationCountryId +
                    ",Remaining_Tickets=" + flights.remainingTickets + ", Departure_Time ='" + flights.departureTime + "',Landing_Time='" + flights.landingTime + "'" +
                    "where id=" + flights.id + "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Flights> getFLightsByOriginCountryId(int country_id) {
        Flights flight = null;
        List<Flights> flights = new ArrayList<>();
        try {
            var result = statement.executeQuery
                    ("SELECT * FROM Flights WHERE origin_country_id=" + country_id);
            while (result.next()) {
                LocalDateTime departureTime, landingTime;
                departureTime = result.getTimestamp("Departure_Time").toLocalDateTime();
                landingTime = result.getTimestamp("Landing_Time").toLocalDateTime();
                flight = new Flights(result.getInt("Id")
                        , result.getInt("Airline_Company_Id")
                        , result.getInt("Origin_Country_Id")
                        , result.getInt("Destination_Country_Id")
                        , result.getInt("Remaining_Tickets")
                        , departureTime
                        , landingTime
                );

                flights.add(flight);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flights;
    }

    public List<Flights> getFLightsByDestinationCountryId(int country_id) {
        Flights flight = null;
        List<Flights> flights = new ArrayList<>();
        try {
            var result = statement.executeQuery
                    ("SELECT * FROM Flights WHERE destination_country_id=" + country_id);
            while (result.next()) {
                LocalDateTime departureTime, landingTime;
                departureTime = result.getTimestamp("Departure_Time").toLocalDateTime();
                landingTime = result.getTimestamp("Landing_Time").toLocalDateTime();
                flight = new Flights(result.getInt("Id")
                        , result.getInt("Airline_Company_Id")
                        , result.getInt("Origin_Country_Id")
                        , result.getInt("Destination_Country_Id")
                        , result.getInt("Remaining_Tickets")
                        , departureTime
                        , landingTime
                );

                flights.add(flight);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flights;
    }

    public List<Flights> getFlightsByDepartureDate(Date date) {
        Flights flight = null;
        List<Flights> flights = new ArrayList<>();
        List<Flights> dateFlights = new ArrayList<>();

        flights=getAll();
        for (int i = 0; i < flights.size(); i++) {
            flight=flights.get(i);
            Date date1=new Date(flight.departureTime.getYear(),flight.departureTime.getMonthValue(),flight.departureTime.getDayOfMonth());
            if ( date1.equals(date))
                dateFlights.add(flight);
        }
        return dateFlights;
    }
    public List<Flights> getFlightsByLandingDate(Date date) {
        Flights flight = null;
        List<Flights> flights = new ArrayList<>();
        List<Flights> dateFlights = new ArrayList<>();

        flights=getAll();
        for (int i = 0; i < flights.size(); i++) {
            flight=flights.get(i);
            Date date1=new Date(flight.landingTime.getYear(),flight.landingTime.getMonthValue(),flight.landingTime.getDayOfMonth());
            if ( date1.equals(date))
                dateFlights.add(flight);
        }
        return dateFlights;
    }

    public List<Flights> getFlightsByCustomer(Customers customer) {
        Tickets ticket = null;
        Flights flight = null;
        List<Flights> flights = new ArrayList<>();
        int id=customer.id;
        try {
            var result = statement.executeQuery
                    ("SELECT * FROM Tickets WHERE customer_id="+id);
          result.next() ;
                ticket = new Tickets(result.getInt("Id")
                        , result.getInt("Customer_Id")
                        , result.getInt("Flight_Id")

                );
                flight = get(ticket.flightId);
                flights.add(flight);


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flights;

    }
    ///stored producer
    public List<Flights> getFlightsByParameters(int originCountryId,int destinationCountryId,LocalDateTime time) {
        Flights flight = null;
        List<Flights> flights = new ArrayList<>();
        try {
            var result = statement.executeQuery
                    ("SELECT * FROM get_flights_by_parameters("+originCountryId+","+destinationCountryId+",'"+time+"')");
            while (result.next()) {
                LocalDateTime departureTime, landingTime;
                departureTime = result.getTimestamp("Departure_Time").toLocalDateTime();
                landingTime = result.getTimestamp("Landing_Time").toLocalDateTime();
                flight = new Flights(result.getInt("Id")
                        , result.getInt("Airline_Company_Id")
                        , result.getInt("Origin_Country_Id")
                        , result.getInt("Destination_Country_Id")
                        , result.getInt("Remaining_Tickets")
                        , departureTime
                        , landingTime
                );

                flights.add(flight);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flights;
    }
    ///stored producer
    public List<Flights> getFlightsByAirlineId(int airlineId) {
        Flights flight = null;
        List<Flights> flights = new ArrayList<>();
        try {
            var result = statement.executeQuery
                    ("SELECT * FROM get_flights_by_airline_id ("+airlineId+")");
            while (result.next()) {
                LocalDateTime departureTime, landingTime;
                departureTime = result.getTimestamp("Departure_Time").toLocalDateTime();
                landingTime = result.getTimestamp("Landing_Time").toLocalDateTime();
                flight = new Flights(result.getInt("Id")
                        , result.getInt("Airline_Company_Id")
                        , result.getInt("Origin_Country_Id")
                        , result.getInt("Destination_Country_Id")
                        , result.getInt("Remaining_Tickets")
                        , departureTime
                        , landingTime
                );

                flights.add(flight);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flights;
    }

    ///stored producer
    public List<Flights> getDepartureFlights(int countryId) {
        Flights flight = null;
        List<Flights> flights = new ArrayList<>();
        try {
            var result = statement.executeQuery
                    ("SELECT * FROM get_departure_flights("+countryId+")");
            while (result.next()) {
                LocalDateTime departureTime, landingTime;
                departureTime = result.getTimestamp("Departure_Time").toLocalDateTime();
                landingTime = result.getTimestamp("Landing_Time").toLocalDateTime();
                flight = new Flights(result.getInt("Id")
                        , result.getInt("Airline_Company_Id")
                        , result.getInt("Origin_Country_Id")
                        , result.getInt("Destination_Country_Id")
                        , result.getInt("Remaining_Tickets")
                        , departureTime
                        , landingTime
                );

                flights.add(flight);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flights;
    }
    ///stored producer
    public List<Flights> getArrivalFlights(int countryId) {
        Flights flight = null;
        List<Flights> flights = new ArrayList<>();
        try {
            var result = statement.executeQuery
                    ("SELECT * FROM get_arrival_flights("+countryId+")");
            while (result.next()) {
                LocalDateTime departureTime, landingTime;
                departureTime = result.getTimestamp("Departure_Time").toLocalDateTime();
                landingTime = result.getTimestamp("Landing_Time").toLocalDateTime();
                flight = new Flights(result.getInt("Id")
                        , result.getInt("Airline_Company_Id")
                        , result.getInt("Origin_Country_Id")
                        , result.getInt("Destination_Country_Id")
                        , result.getInt("Remaining_Tickets")
                        , departureTime
                        , landingTime
                );

                flights.add(flight);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flights;
    }

}