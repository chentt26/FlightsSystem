package com.example.flightsSystem;

import com.example.flightsSystem.daos.AirlineCompaniesDAO;
import com.example.flightsSystem.daos.CountriesDAO;
import com.example.flightsSystem.daos.CustomersDAO;
import com.example.flightsSystem.daos.FlightsDAO;
import com.example.flightsSystem.pojos.AirlineCompanies;
import com.example.flightsSystem.pojos.Countries;
import com.example.flightsSystem.pojos.Customers;
import com.example.flightsSystem.pojos.Flights;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@SpringBootTest
class FlightsSystemApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void getAllFlightsTest(){
		CountriesDAO countriesDAO=new CountriesDAO();
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest
				.newBuilder(URI.create("http://localhost:8080/Anonymous/getCountries"))
				.build();

		HttpResponse<String> response=null;

		try {
			response =client.send(request,HttpResponse.BodyHandlers.ofString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}


		GsonBuilder builder = new GsonBuilder();
		builder.setPrettyPrinting();
		Gson gson = builder.create();

		var expected =
				gson.fromJson(response.body(),Countries[].class);
		var current = countriesDAO.getAll();

		Assert.assertEquals(current.get(1),expected[1]);
	}
@Test
	void getAirlineById(){
	AirlineCompaniesDAO dao=new AirlineCompaniesDAO();
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest
				.newBuilder(URI.create("http://localhost:8080/Anonymous/getOneAirline2"))
				.build();

		HttpResponse<String> response=null;

		try {
			response =client.send(request,HttpResponse.BodyHandlers.ofString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}


		GsonBuilder builder = new GsonBuilder();
		builder.setPrettyPrinting();
		Gson gson = builder.create();

		var expected =
				gson.fromJson(response.body(), AirlineCompanies.class);
		var current                   = dao.get(2);

		Assert.assertEquals(current,expected);
	}

}
