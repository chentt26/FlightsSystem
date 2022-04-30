package com.example.flightsSystem.pojos;

import org.springframework.stereotype.Component;

import java.util.Objects;
@Component
public class AirlineCompanies {
   public int id,countryId,userId;
    public String name;

    public AirlineCompanies(int countryId, int userId, String name) {
        this.countryId = countryId;
        this.userId = userId;
        this.name = name;
    }

    public AirlineCompanies(int id, int countryId, int userId, String name) {
        this.id = id;
        this.countryId = countryId;
        this.userId = userId;
        this.name = name;
    }

    @Override
    public String toString() {
        return "AirlineCompanies{" +
                "id=" + id +
                ", countryId=" + countryId +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AirlineCompanies that = (AirlineCompanies) o;
        return id == that.id && countryId == that.countryId && userId == that.userId && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, countryId, userId, name);
    }
    public AirlineCompanies(){

    }
}
