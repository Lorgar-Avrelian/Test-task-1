package com.gridnine.testing.model.filter_builder;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Flights;
import com.gridnine.testing.model.Segment;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * Filter class for entity of {@link Flights}
 */
public class FlightFilterBuilder {
    List<Flight> flights;

    public FlightFilterBuilder(List<Flight> flights) {
        this.flights = flights;
    }

    /**
     * A method for getting {@link List} with {@link Flight} that departs in the past
     *
     * @return List<Flight>
     */
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

    /**
     * A method for getting {@link List} with {@link Flight} that departs before it arrives
     *
     * @return List<Flight>
     */
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

    /**
     * A method for getting {@link List} with {@link Flight} with more than two hours ground time
     *
     * @return List<Flight>
     */
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

    /**
     * A method for getting sorted {@link List} with {@link Flight}
     *
     * @return List<Flight>
     */
    public List<Flight> build() {
        return flights;
    }
}
