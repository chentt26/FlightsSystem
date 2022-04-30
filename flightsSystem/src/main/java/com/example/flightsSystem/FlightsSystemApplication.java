package com.example.flightsSystem;

import com.example.flightsSystem.daos.TicketsDAO;
import com.example.flightsSystem.facades.CustomerFacade;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class FlightsSystemApplication {

	public static void main(String[] args) {
		ApplicationContext context =SpringApplication.run(FlightsSystemApplication.class, args);

		CustomerFacade facade=context.getBean(CustomerFacade.class);
		facade.setId(1);
	System.out.println(facade.getMyTickets());	}

}
