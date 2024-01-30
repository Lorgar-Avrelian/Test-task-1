package com.gridnine.testing.service.impl;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Flights;
import com.gridnine.testing.service.FlightService;

import java.util.List;

/**
 * Service implementation for processing requests
 */
public class FlightServiceImpl implements FlightService {
    /**
     * A method for getting {@link List} with {@link Flight} that departs in the past
     *
     * @param flights
     * @return List<Flight>
     */
    @Override
    public List<Flight> beforeNow(Flights flights) {
        return flights.filter()
                      .departureBeforeNow()
                      .build();
    }

    /**
     * A method for getting {@link List} with {@link Flight} that departs before it arrives
     *
     * @param flights
     * @return List<Flight>
     */
    @Override
    public List<Flight> arrivalDateBeforeDepartureDate(Flights flights) {
        return flights.filter()
                      .arrivalDateBeforeDepartureDate()
                      .build();
    }

    /**
     * A method for getting {@link List} with {@link Flight} with more than two hours ground time
     *
     * @param flights
     * @return List<Flight>
     */
    @Override
    public List<Flight> parkingMoreThen2Hours(Flights flights) {
        return flights.filter()
                      .parkingMoreThen2Hours()
                      .build();
    }
}
