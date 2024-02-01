package com.gridnine.testing.model;

import java.util.List;
import java.util.Objects;

/**
 * Bean that represents {@link List} of {@link Flight}
 */
public class Flights {
    private List<Flight> flights;

    public Flights(List<Flight> flights) {
        this.flights = flights;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
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
}
