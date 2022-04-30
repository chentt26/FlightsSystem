package com.example.flightsSystem.services;

import com.example.flightsSystem.entites.ContactUs;
import com.example.flightsSystem.repos.ContactUsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactUsService {
    @Autowired
    ContactUsRepo repo;

    public void addContactUs(ContactUs contactUs){
     repo.save(contactUs);
    }
    public void deleteContactUs(int id){
        repo.deleteById(id);
    }
    public ContactUs getOne(int id){
return repo.findById(id).orElse(null);
    }
    public List<ContactUs> getAll(){
        return (List<ContactUs>) repo.findAll();
    }
    public void updateContact(ContactUs contactUs){
        repo.save(contactUs);
    }
}
