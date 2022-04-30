package com.example.flightsSystem.controllers;

import com.example.flightsSystem.facades.AdministratorFacade;
import com.example.flightsSystem.pojos.Adminstrators;
import com.example.flightsSystem.pojos.AirlineCompanies;
import com.example.flightsSystem.pojos.Customers;
import com.example.flightsSystem.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdministratorController {
@Autowired
    private AdministratorFacade facade;

    @Autowired
    private JwtUtil jwt;

    /**
     * returns the correct facade using jwt to get his id *
     * every method in the controller uses this method to confirm that only allowed user can use that method
     */
    private AdministratorFacade getAdminFacade(String token) throws Exception {
        if(jwt.isTokenExpired(token)) {
            throw new Exception("token is expired");
        }
        String username=jwt.extractUsername(token);
        int id=facade.getIdByUserName(username);
        facade.setId(id);
        return facade;
    }

    /**
    * returns all customers
     */
   @GetMapping("/customers")
    public List<Customers> getAll(@RequestHeader String token){
       try {
           return getAdminFacade(token).getAllCustomers();
       } catch (Exception e) {
           e.printStackTrace();
       }
return null;
   }
    /**
     * adding new customer
     */
    @PostMapping("/AddCustomer")
public void addCustomer(@RequestBody Customers customer, @RequestHeader String token){
        try {
            getAdminFacade(token).addCustomer(customer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * adding new admin
     * i would recommend not to use this method,because its not logical to add another admin
     */
    @PostMapping("/addAdmin")
    public void addAdmin(@RequestBody Adminstrators adminstrators,@RequestHeader String token){
        try {
            getAdminFacade(token).addAdministrator(adminstrators);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * adding new airline company
     */
    @PostMapping("/addAirline")
    public void addAirline(@RequestBody AirlineCompanies airlineCompanies,@RequestHeader String token){
        try {
            getAdminFacade(token).addAirline(airlineCompanies);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * removes airline, requests a whole airline Object, but it only needs the id,
     * if you send an object that's empty but has the correct id it would work
     */
    @DeleteMapping("/removeAirline")
    public void removeAirline(@RequestBody AirlineCompanies airlineCompanies,@RequestHeader String token){
        try {
            getAdminFacade(token).removeAirline(airlineCompanies);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * removes customer, requests a whole customer Object, but it only needs the id,
     * if you send an object that's empty but has the correct id it would work
     */
    @DeleteMapping("removeCustomer")
public void removeCustomer(@RequestBody Customers customers,@RequestHeader String token){
        try {
            getAdminFacade(token).removeCustomer(customers);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * removes admin, requests a whole admin Object, but it only needs the id,
     * if you send an object that's empty but has the correct id it would work
     * i would recommend not to use this method,because its not logical to remove an admin
     */
@DeleteMapping("/removeAdmin")
public void removeAdmin(@RequestBody Adminstrators adminstrators,@RequestHeader String token){
    try {
        getAdminFacade(token).removeAdministrator(adminstrators);
    } catch (Exception e) {
        e.printStackTrace();
    }
}
/**
* empty ctor
 */
public AdministratorController (){

}
}
