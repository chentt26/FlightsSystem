package com.example.flightsSystem.facades;

import com.example.flightsSystem.pojos.AirlineCompanies;
import com.example.flightsSystem.pojos.Countries;
import com.example.flightsSystem.pojos.Flights;
import com.example.flightsSystem.pojos.Users;

import java.time.LocalDateTime;
import java.util.List;

public interface BasicFacade {

    List<Flights> getAllFlights();
    Flights getFlightsById(int id) throws Exception;
    List <Flights> getFlightsByParameters(int originCountryId, int destinationCountryId, LocalDateTime dateTime) throws Exception;
    List<AirlineCompanies> getAllAirlines();
    AirlineCompanies getAirlineById(int id) throws Exception;
   List<Countries> getAllCountries();
   Countries getCountryById(int id) throws Exception;
   void createNewUser(Users user);

}
