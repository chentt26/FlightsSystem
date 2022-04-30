package com.example.flightsSystem.controllers;

import com.example.flightsSystem.facades.AnonymousFacade;
import com.example.flightsSystem.pojos.AirlineCompanies;
import com.example.flightsSystem.pojos.Countries;
import com.example.flightsSystem.pojos.Flights;
import com.example.flightsSystem.pojos.Users;
import com.example.flightsSystem.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/Anonymous")
public class AnonController {
    @Autowired
    AnonymousFacade facade = new AnonymousFacade();
    @Autowired
    JwtUtil jwt;

    /**
     * returns all flights
     */
    @GetMapping("/getAll")
    public List<Flights> getAll() {
        return facade.getAllFlights();
    }

    /**
     * returns one flight ,by flight id
     */
    @GetMapping("/getOneFlight{id}")
    public Flights getOneFlight(@PathVariable int id) {
        try {
            Flights flights = facade.getFlightsById(id);
            return flights;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * returns  flights ,by specific parameters
     */
    @GetMapping("/parameters{originCountryId}{destinationCountryId}{dateTime}")
    public List<Flights> getFlightsByParameters(@PathVariable int originCountryId, @PathVariable int destinationCountryId, @PathVariable LocalDateTime dateTime) {
        try {
            List<Flights> flights = facade.getFlightsByParameters(originCountryId, destinationCountryId, dateTime);
            return flights;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * returns all airlines
     */
    @GetMapping("/getAirlines")
    public List<AirlineCompanies> getAllAirlines() {
        return facade.getAllAirlines();
    }

    /**
     * returns one airline ,by airline id
     */
    @GetMapping("/getOneAirline{id}")
    public AirlineCompanies getOneAirline(@PathVariable int id) {
        try {
            AirlineCompanies airlineCompanies = facade.getAirlineById(id);
            return airlineCompanies;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * returns all countries
     */
    @GetMapping("/getCountries")
    public List<Countries> getAllCountries() {
        return facade.getAllCountries();
    }

    /**
     * returns one country ,by country id
     */
    @GetMapping("/getOneCountry{id}")
    public Countries getOneCountry(@PathVariable int id) {
        try {
            Countries countries = facade.getCountryById(id);
            return countries;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * adding new user
     */
    @PostMapping("/addUser")
    public void addUser(@RequestBody Users users) {
        facade.createNewUser(users);
    }

    /**
     * a method that you need to use after every! login to get your token,
     * if we had frontend to this project we wont need this method
     */
    @GetMapping("/valid")
    private String generateToken() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName(); /// returns username from the login page
        Users user;
        String token = jwt.getTokenByUsername(userName);///returns null if token dosent exists/expired
        if (token == null) {
            user = facade.getUserByUserName(userName);
            token = jwt.generateToken(new JwtUtil.UserDetails ///creates new token if token does not exists
                    (user.id
                            , user.userName
                            , facade
                            .getRoleByNumber(user.userRole)));
        }
        System.out.println(token);
        return token;
    }
}