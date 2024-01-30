package com.gridnine.testing.model;

import com.gridnine.testing.model.filter_builder.FlightFilterBuilder;

import java.util.List;
import java.util.Objects;

public class Flights {
    List<Flight> flights;

    public Flights(List<Flight> flights) {
        this.flights = flights;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flights flights1 = (Flights) o;
        return Objects.equals(flights, flights1.flights);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flights);
    }

    @Override
    public String toString() {
        return "Flights{" +
                "flights=" + flights +
                '}';
    }

    public FlightFilterBuilder filter() {
        return new FlightFilterBuilder(flights);
    }
}
