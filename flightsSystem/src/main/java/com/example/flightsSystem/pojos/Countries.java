package com.example.flightsSystem.pojos;


import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class Countries {
   public int id;
   public   String name;

    public Countries(String name) {
        this.name = name;
    }

    public Countries(int id, String name) {
        this.id = id;
        this.name = name;
    }
public Countries(){

}
    @Override
    public String toString() {
        return "Countries{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Countries countries = (Countries) o;
        return id == countries.id && Objects.equals(name, countries.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
