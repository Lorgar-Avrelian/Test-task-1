package com.gridnine.testing.model;

import com.gridnine.testing.model.filter_builder.FlightFilterBuilder;

import java.util.List;

public class Flights {
    List<Flight> flights;

    public Flights(List<Flight> flights) {
        this.flights = flights;
    }

    public List<Flight> getFlights() {
        return flights;
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
