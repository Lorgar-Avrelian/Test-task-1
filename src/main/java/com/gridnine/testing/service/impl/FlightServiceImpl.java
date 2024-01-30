package com.gridnine.testing.service.impl;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Flights;
import com.gridnine.testing.service.FlightService;

import java.util.List;

public class FlightServiceImpl implements FlightService {
    @Override
    public List<Flight> beforeNow(Flights flights) {
        return flights.filter()
                      .departureBeforeNow()
                      .build();
    }

    @Override
    public List<Flight> arrivalDateBeforeDepartureDate(Flights flights) {
        return flights.filter()
                      .arrivalDateBeforeDepartureDate()
                      .build();
    }

    @Override
    public List<Flight> parkingMoreThen2Hours(Flights flights) {
        return flights.filter()
                      .parkingMoreThen2Hours()
                      .build();
    }
}
