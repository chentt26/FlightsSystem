package com.example.flightsSystem.pojos;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;

@Component
public class Flights {
    public int id,airLineCompanyId,originCountryId,destinationCountryId,remainingTickets;
    public LocalDateTime departureTime,landingTime;

    public Flights(int airLineCompanyId, int originCountryId, int destinationCountryId, int remainingTickets, LocalDateTime departureTime, LocalDateTime landingTime) {
        this.airLineCompanyId = airLineCompanyId;
        this.originCountryId = originCountryId;
        this.destinationCountryId = destinationCountryId;
        this.remainingTickets = remainingTickets;
        this.departureTime = departureTime;
        this.landingTime = landingTime;
    }

    public Flights(int id, int airLineCompanyId, int originCountryId, int destinationCountryId, int remainingTickets, LocalDateTime departureTime, LocalDateTime landingTime) {
        this.id = id;
        this.airLineCompanyId = airLineCompanyId;
        this.originCountryId = originCountryId;
        this.destinationCountryId = destinationCountryId;
        this.remainingTickets = remainingTickets;
        this.departureTime = departureTime;
        this.landingTime = landingTime;
    }

    @Override
    public String toString() {
        return "Flights{" +
                "id=" + id +
                ", airLineCompanyId=" + airLineCompanyId +
                ", originCountryId=" + originCountryId +
                ", destinationCountryId=" + destinationCountryId +
                ", remainingTickets=" + remainingTickets +
                ", departureTime=" + departureTime +
                ", landingTime=" + landingTime +
                '}';
    }
    public Flights(){

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flights flights = (Flights) o;
        return id == flights.id && airLineCompanyId == flights.airLineCompanyId && originCountryId == flights.originCountryId && destinationCountryId == flights.destinationCountryId && remainingTickets == flights.remainingTickets && Objects.equals(departureTime, flights.departureTime) && Objects.equals(landingTime, flights.landingTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, airLineCompanyId, originCountryId, destinationCountryId, remainingTickets, departureTime, landingTime);
    }
}
