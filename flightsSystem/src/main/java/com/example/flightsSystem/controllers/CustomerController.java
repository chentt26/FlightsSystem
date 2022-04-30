package com.example.flightsSystem.controllers;

import com.example.flightsSystem.facades.AirlineFacade;
import com.example.flightsSystem.facades.AnonymousFacade;
import com.example.flightsSystem.facades.CustomerFacade;
import com.example.flightsSystem.pojos.Customers;
import com.example.flightsSystem.pojos.Tickets;
import com.example.flightsSystem.pojos.Users;
import com.example.flightsSystem.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Customer")
public class CustomerController {
    @Autowired
    CustomerFacade facade;
    @Autowired
    private JwtUtil jwt;
    private String jwtToken;
   @Autowired
    AnonymousFacade anonymousFacade = new AnonymousFacade();

    /**
     * returns the correct facade using jwt to get his id *
     * every method in the controller uses this method to confirm that only allowed user can use that method
     */
    private CustomerFacade getCustomerFacade(String token) throws Exception {
        if(jwt.isTokenExpired(token)) {
            throw new Exception("token is expired");
        }
        String username=jwt.extractUsername(token);
        int id=facade.getIdByUserName(username);
        facade.setId(id);
        return facade;
    }
    /**
     * updates customer,please make sure you have the correct id,
     * you can change anything else but the id
     */
    @PutMapping("/update")
    public void updateCustomer(@RequestBody Customers customers, @RequestHeader String token) {
        try {
            getCustomerFacade(token).updateCustomer(customers);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    /**
     * adds new ticket
     */
    @PostMapping("/ticket")
    public void AddTicket(@RequestBody Tickets tickets, @RequestHeader String token) {
        try {
            getCustomerFacade(token).addTicket(tickets);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * removes ticket, requests a whole ticket Object, but it only needs the id,
     * if you send an object that's empty but has the correct id it would work
     */
    @DeleteMapping("/remove")
    public void deleteTicket(@RequestBody Tickets tickets, @RequestHeader String token) {
        try {
            getCustomerFacade(token).removeTicket(tickets);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * returns all tickets to this specific customer by the customer id
     */
    @GetMapping("/getMyTickets")
    public List<Tickets> getMyTickets(@RequestHeader String token) {
        try {
            return getCustomerFacade(token).getMyTickets();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
