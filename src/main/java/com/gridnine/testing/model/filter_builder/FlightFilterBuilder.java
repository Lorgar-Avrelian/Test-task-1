package com.gridnine.testing.model.filter_builder;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class FlightFilterBuilder {
    List<Flight> flights;

    public FlightFilterBuilder(List<Flight> flights) {
        this.flights = flights;
    }

    public FlightFilterBuilder departureBeforeNow() {
        LocalDateTime timeNow = LocalDateTime.now();
        this.flights = flights.stream()
                              .parallel()
                              .map(Flight::getSegments)
                              .map(segments -> {
                                  List<Segment> segmentsList = segments.stream()
                                                                       .filter(segment -> segment.getDepartureDate().isBefore(timeNow))
                                                                       .toList();
                                  if (!segmentsList.isEmpty()) {
                                      return new Flight(segmentsList);
                                  } else {
                                      return null;
                                  }
                              })
                              .filter(Objects::nonNull)
                              .toList();
        return this;
    }

    public FlightFilterBuilder arrivalDateBeforeDepartureDate() {
        this.flights = flights.stream()
                              .parallel()
                              .map(Flight::getSegments)
                              .map(segments -> {
                                  List<Segment> segmentsList = segments.stream()
                                                                       .filter(segment -> segment.getArrivalDate().isBefore(segment.getDepartureDate()))
                                                                       .toList();
                                  if (!segmentsList.isEmpty()) {
                                      return new Flight(segmentsList);
                                  } else {
                                      return null;
                                  }
                              })
                              .filter(Objects::nonNull)
                              .toList();
        return this;
    }

    public FlightFilterBuilder parkingMoreThen2Hours() {
        this.flights = flights.stream()
                              .parallel()
                              .map(Flight::getSegments)
                              .map(segments -> {
                                  List<Segment> segmentsList = segments.stream()
                                                                       .toList();
                                  List<Segment> filteredSegmentList = null;
                                  if (segmentsList.size() > 1) {
                                      for (int i = 0; i < segmentsList.size() - 1; i++) {
                                          if (segmentsList.get(i + 1).getDepartureDate().minusHours(2).isAfter(segmentsList.get(i).getArrivalDate())) {
                                              filteredSegmentList = segmentsList;
                                              break;
                                          }
                                      }
                                  }
                                  if (filteredSegmentList != null) {
                                      return new Flight(filteredSegmentList);
                                  } else {
                                      return null;
                                  }
                              })
                              .filter(Objects::nonNull)
                              .toList();
        return this;
    }

    public List<Flight> build() {
        return flights;
    }
}
