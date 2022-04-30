package com.example.flightsSystem.facades;

import com.example.flightsSystem.daos.AdminstratorsDAO;
import com.example.flightsSystem.daos.AirlineCompaniesDAO;
import com.example.flightsSystem.daos.CustomersDAO;
import com.example.flightsSystem.pojos.Adminstrators;
import com.example.flightsSystem.pojos.AirlineCompanies;
import com.example.flightsSystem.pojos.Customers;
import com.example.flightsSystem.pojos.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministratorFacade extends AnonymousFacade {
    @Autowired
    private CustomersDAO customersDAO;
    @Autowired
    private AirlineCompaniesDAO airlineDAO;
    @Autowired
    private AdminstratorsDAO adminstratorsDAO;

    private Token token;
    private int id;

    //returns all customers
    public List<Customers> getAllCustomers() {
        return customersDAO.getAll();
    }

    ///adding airline
    public void addAirline(AirlineCompanies airline) throws Exception {
        if (airline.countryId <= 0 || airline.userId <= 0)
            throw new Exception("you cant add negative value to one of the ids");
        airlineDAO.add(airline);
    }

    ///adding customer
    public void addCustomer(Customers customer) throws Exception {
        if (customer.userId <= 0)
            throw new Exception("you cant add negative value to one of the ids");

        customersDAO.add(customer);

    }

    ///adding admin
    public void addAdministrator(Adminstrators adminstrator) throws Exception {
        if (adminstrator.userId <= 0)
            throw new Exception("you cant add negative value to one of the ids");

        adminstratorsDAO.add(adminstrator);
    }

    ///removing airline
    public void removeAirline(AirlineCompanies airline) {
        airlineDAO.remove(airline);
    }
    ///removing customer

    public void removeCustomer(Customers customers) {
        customersDAO.remove(customers);
    }
    ///removing customer

    public void removeAdministrator(Adminstrators adminstrator) {
        adminstratorsDAO.remove(adminstrator);
    }

    public AdministratorFacade(Token token) {
        this.token = token;
    }

    public AdministratorFacade() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdByUserName(String username){
        return adminstratorsDAO.getAdminByUsername(username).id;
    }
}
