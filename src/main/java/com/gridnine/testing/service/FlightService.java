package com.gridnine.testing.service;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Flights;

import java.util.List;

public interface FlightService {
    List<Flight> beforeNow(Flights flights);

    List<Flight> arrivalDateBeforeDepartureDate(Flights flights);

    List<Flight> parkingMoreThen2Hours(Flights flights);
}
