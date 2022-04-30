package com.example.flightsSystem.facades;

import com.example.flightsSystem.daos.CustomersDAO;
import com.example.flightsSystem.daos.TicketsDAO;
import com.example.flightsSystem.pojos.Customers;
import com.example.flightsSystem.pojos.Tickets;
import com.example.flightsSystem.pojos.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerFacade extends AnonymousFacade {
    @Autowired
    private CustomersDAO customersDAO;
    @Autowired
    private TicketsDAO ticketsDAO;
    private Token token;
    private int id;

    ///update customer,validates ids
    public void updateCustomer(Customers customer) throws Exception {
        if (customer.userId <= 0)
            throw new Exception("you cant add negative value to one of the ids");
        if (customer.id != id)
            throw new Exception("you cant update other customer");

        customersDAO.update(customer);
    }

    ///adding ticket,validate ids
    public void addTicket(Tickets ticket) throws Exception {
        System.out.println("id "+ id+ " customer id "+ ticket.customerId);
        if (ticket.flightId <= 0)
            throw new Exception("you cant add negative value to one of the ids");
        if (ticket.customerId != id)
            throw new Exception("you cant purchase ticket for another customer");

        ticketsDAO.add(ticket);
    }
    ///removing ticket,validate ids

    public void removeTicket(Tickets ticket) throws Exception {
        if (ticket.customerId != id)
            throw new Exception("you cant remove ticket for another customer");

        ticketsDAO.remove(ticket);
    }

    ///returns this customer tickets
    public List<Tickets> getMyTickets() {
        return ticketsDAO.getTicketsByCustomer(id);
    }

    public CustomerFacade(Token token) {
        this.token = token;
    }

    public CustomerFacade() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdByUserName(String username){
       return customersDAO.getCustomerByUsername(username).id;
    }
}
