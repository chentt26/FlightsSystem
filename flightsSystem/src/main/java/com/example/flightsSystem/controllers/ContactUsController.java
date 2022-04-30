package com.example.flightsSystem.controllers;

import com.example.flightsSystem.entites.ContactUs;
import com.example.flightsSystem.services.ContactUsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ContactUs")
public class ContactUsController {
  @Autowired
   ContactUsService service;
    /**
     * adding new contact us
     */
    @PostMapping("/add")
    public void addContactUs(@RequestBody ContactUs contactUs){
        service.addContactUs(contactUs);
    }
    /**
     * updates flight,please make sure you have the correct id,
     * you can change anything else but the id
     */
    @PutMapping("/update")
    public void updateContactUs(@RequestBody ContactUs contactUs){
        service.updateContact(contactUs);
    }
    /**
     * removes contact us, requests a whole contact us Object, but it only needs the id,
     * if you send an object that's empty but has the correct id it would work
     */
    @DeleteMapping("/delete{id}")
    public void deleteContactUs(@PathVariable int id){
     service.deleteContactUs(id);
    }
    /**
     * returns one contact us object
     */
    @GetMapping("/one{id}")
    public ContactUs getOne(@PathVariable int id){
        return service.getOne(id);
    }
    /**
     * returns all contact us objects
     */
    @GetMapping("/getAll")
    public List<ContactUs> getAll(){
        return service.getAll();
    }
}
