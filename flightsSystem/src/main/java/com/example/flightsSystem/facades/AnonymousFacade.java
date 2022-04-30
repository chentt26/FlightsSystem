package com.example.flightsSystem.facades;

import com.example.flightsSystem.daos.*;
import com.example.flightsSystem.pojos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AnonymousFacade implements BasicFacade {
    @Autowired
    private FlightsDAO flightsDAO;
    @Autowired
    private AirlineCompaniesDAO airlineDAO;
    @Autowired
    private CountriesDAO countriesDAO;
    @Autowired
    private CustomersDAO customersDAO;
    @Autowired
    private AdminstratorsDAO adminstratorsDAO;
    @Autowired
    private UsersDAO usersDAO;
    int id;

    ///returns all flights
    @Override
    public List<Flights> getAllFlights() {
        return flightsDAO.getAll();
    }

    ///returns flight by id
    @Override
    public Flights getFlightsById(int id) throws Exception {
        if (id <= 0)
            throw new Exception("you cant add negative value to one of the ids");

        return flightsDAO.get(id);
    }

    @Override
    public List<Flights> getFlightsByParameters(int originCountryId, int destinationCountryId, LocalDateTime dateTime) throws Exception {
        if (originCountryId <= 0 || destinationCountryId <= 0)
            throw new Exception("you cant add negative value to one of the ids");

        return flightsDAO.getFlightsByParameters(originCountryId, destinationCountryId, dateTime);
    }

    ///returns all airlines
    @Override
    public List<AirlineCompanies> getAllAirlines() {
        return airlineDAO.getAll();
    }

    ///returns airline by id
    @Override
    public AirlineCompanies getAirlineById(int id) throws Exception {
        if (id <= 0)
            throw new Exception("you cant add negative value to one of the ids");
        return airlineDAO.get(id);
    }

    ///returns all countries
    @Override
    public List<Countries> getAllCountries() {
        return countriesDAO.getAll();
    }

    ///return country by id
    @Override
    public Countries getCountryById(int id) throws Exception {
        if (id <= 0)
            throw new Exception("you cant add negative value to one of the ids");
        return countriesDAO.get(id);
    }

    ///creates new user in the db
    @Override
    public void createNewUser(Users user) {
        usersDAO.add(user);
    }

    public Users getUserByUserName(String username){
      return   usersDAO.getUserByUsername(username);
    }

    public String getRoleByNumber(int num){
        String role;
        if (num == 1)
            role = "administrator";
        if (num == 2)
            role = "airline company";
        else
            role = "customer";
        return role;
    }

    public AnonymousFacade() {

    }
}
