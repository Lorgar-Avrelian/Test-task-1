package com.gridnine.testing.service.impl;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.service.FlightService;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Service implementation for processing requests
 */
public class FlightServiceImpl implements FlightService {
    private List<Flight> flights;

    public FlightServiceImpl(List<Flight> flights) {
        this.flights = flights;
    }

    /**
     * A method for getting {@link List} with {@link Flight} that departs in the past
     *
     * @return List<Flight>
     */
    @Override
    public FlightService departureBeforeNow() {
        LocalDateTime timeNow = LocalDateTime.now();
        this.flights = flights.stream()
                              .parallel()
                              .map(Flight::getSegments)
                              .map(segments -> {
                                  for (int i = 0; i < segments.size(); i++) {
                                      if (segments.get(i).getDepartureDate().isBefore(timeNow)) {
                                          segments.set(i, null);
                                      }
                                      segments = segments.stream().filter(Objects::nonNull).collect(Collectors.toList());
                                  }
                                  if (!segments.isEmpty()) {
                                      return new Flight(segments);
                                  } else {
                                      return null;
                                  }
                              })
                              .filter(Objects::nonNull)
                              .collect(Collectors.toList());
        return this;
    }

    /**
     * A method for getting {@link List} with {@link Flight} that departs before it arrives
     *
     * @return List<Flight>
     */
    @Override
    public FlightService arrivalDateBeforeDepartureDate() {
        this.flights = flights.stream()
//                              .parallel()
                              .map(Flight::getSegments)
                              .map(segments -> {
                                  for (int i = 0; i < segments.size(); i++) {
                                      if (segments.get(i).getDepartureDate().isAfter(segments.get(i).getArrivalDate())) {
                                          segments.set(i, null);
                                      }
                                  }
                                  segments = segments.stream().filter(Objects::nonNull).collect(Collectors.toList());
                                  if (!segments.isEmpty()) {
                                      return new Flight(segments);
                                  } else {
                                      return null;
                                  }
                              })
                              .filter(Objects::nonNull)
                              .collect(Collectors.toList());
        return this;
    }

    /**
     * A method for getting {@link List} with {@link Flight} with more than two hours ground time
     *
     * @return List<Flight>
     */
    @Override
    public FlightService parkingMoreThen2Hours() {
        this.flights = flights.stream()
                              .parallel()
                              .map(Flight::getSegments)
                              .map(segments -> {
                                  if (segments.size() > 1) {
                                      long parkingTime = 0;
                                      for (int i = 0; i < segments.size() - 1; i++) {
                                          parkingTime += ChronoUnit.MINUTES.between(segments.get(i).getArrivalDate(), segments.get(i + 1).getDepartureDate());
                                      }
                                      if (parkingTime <= 120) {
                                          return new Flight(segments);
                                      } else {
                                          return null;
                                      }
                                  }
                                  return new Flight(segments);
                              })
                              .filter(Objects::nonNull)
                              .collect(Collectors.toList());
        return this;
    }

    @Override
    public List<Flight> filter() {
        return flights;
    }
}
