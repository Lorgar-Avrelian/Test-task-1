package com.gridnine.testing.service;

import com.gridnine.testing.model.Flight;

import java.util.List;

/**
 * Service for processing requests
 */
public interface FlightService {
    FlightService departureBeforeNow();

    FlightService arrivalDateBeforeDepartureDate();

    FlightService parkingMoreThen2Hours();

    List<Flight> filter();
}
