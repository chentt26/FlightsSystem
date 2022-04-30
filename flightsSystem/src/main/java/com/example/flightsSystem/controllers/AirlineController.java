package com.example.flightsSystem.controllers;

import com.example.flightsSystem.facades.AdministratorFacade;
import com.example.flightsSystem.facades.AirlineFacade;
import com.example.flightsSystem.pojos.AirlineCompanies;
import com.example.flightsSystem.pojos.Flights;
import com.example.flightsSystem.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Airline")
public class AirlineController {
    @Autowired
    AirlineFacade facade;

    @Autowired
    private JwtUtil jwt;

    /**
     * returns the correct facade using jwt to get his id *
     * every method in the controller uses this method to confirm that only allowed user can use that method
     */
    private AirlineFacade getAirlineFacade(String token) throws Exception {
        if (jwt.isTokenExpired(token)) {
            throw new Exception("token is expired");
        }
        String username = jwt.extractUsername(token);
        int id = facade.getIdByUserName(username);
        facade.setId(id);
        return facade;
    }

    /**
     * updates airline,please make sure you have the correct id,
     * you can change anything else but the id
     */
    @PutMapping("/updateAirline")
    public void updateAirline(@RequestBody AirlineCompanies airlineCompanies, @RequestHeader String token) {
        try {
            getAirlineFacade(token).updateAirline(airlineCompanies);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * adding flight
     */
    @PostMapping("/AddFlight")
    public void addFlight(@RequestBody Flights flights, @RequestHeader String token) {
        try {
            getAirlineFacade(token).addFlight(flights);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * updates flight,please make sure you have the correct id,
     * you can change anything else but the id
     */
    @PutMapping("/updateFlight")
    public void updateFlight(@RequestBody Flights flights, @RequestHeader String token) {
        try {
            getAirlineFacade(token).updateFLight(flights);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * removes flight, requests a whole flight Object, but it only needs the id,
     * if you send an object that's empty but has the correct id it would work
     */
    @DeleteMapping("/removeFlight")
    public void removeFlight(@RequestBody Flights flights, @RequestHeader String token) {
        try {
            getAirlineFacade(token).removeFlight(flights);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * returns all flights to this specific airline by the airline id
     */
    public List<Flights> getMyFlights(@RequestHeader String token) {
        try {
            return getAirlineFacade(token).getMyFlights();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
