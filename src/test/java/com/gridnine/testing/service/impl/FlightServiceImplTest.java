package com.gridnine.testing.service.impl;

import com.gridnine.testing.filter_factory.FlightFilterFactory;
import com.gridnine.testing.filters.FlightFilterFactoryImpl;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Flights;
import com.gridnine.testing.model.Segment;
import com.gridnine.testing.test_factory.FlightBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class FlightServiceImplTest {
    static Flights flights;
    FlightFilterFactory filterFactory;

    @BeforeEach
    void beforeEach() {
        flights = new Flights(FlightBuilder.createFlights());
        filterFactory = new FlightFilterFactoryImpl();
    }

    @Test
    void shouldReturnFlightWithoutDepartureBeforeNow() {
        // Returns departureBeforeNow
        List<Flight> expextedList = Arrays.asList(
                new Flight(List.of(new Segment(LocalDateTime.now().plusDays(3), LocalDateTime.now().plusDays(3).plusHours(2)))),
                new Flight(List.of(new Segment(LocalDateTime.now().plusDays(3), LocalDateTime.now().plusDays(3).plusHours(2)), new Segment(LocalDateTime.now().plusDays(3).plusHours(3), LocalDateTime.now().plusDays(3).plusHours(5)))),
                new Flight(List.of(new Segment(LocalDateTime.now().plusDays(3), LocalDateTime.now().plusDays(3).minusHours(6)))),
                new Flight(List.of(new Segment(LocalDateTime.now().plusDays(3), LocalDateTime.now().plusDays(3).plusHours(2)), new Segment(LocalDateTime.now().plusDays(3).plusHours(5), LocalDateTime.now().plusDays(3).plusHours(6)))),
                new Flight(List.of(new Segment(LocalDateTime.now().plusDays(3), LocalDateTime.now().plusDays(3).plusHours(2)), new Segment(LocalDateTime.now().plusDays(3).plusHours(3), LocalDateTime.now().plusDays(3).plusHours(4)), new Segment(LocalDateTime.now().plusDays(3).plusHours(6), LocalDateTime.now().plusDays(3).plusHours(7)))));
        List<Flight> actualList = filterFactory.getDepartureBeforeNowFilter().filterDepartureBeforeNow(flights.getFlights());
        assertEquals(actualList.toString(), expextedList.toString());
    }

    @Test
    void shouldReturnFlightWithoutArrivalDateBeforeDepartureDate() {
        // Returns arrivalDateBeforeDepartureDate
        List<Flight> expextedList = Arrays.asList(
                new Flight(List.of(new Segment(LocalDateTime.now().plusDays(3), LocalDateTime.now().plusDays(3).plusHours(2)))),
                new Flight(List.of(new Segment(LocalDateTime.now().plusDays(3), LocalDateTime.now().plusDays(3).plusHours(2)), new Segment(LocalDateTime.now().plusDays(3).plusHours(3), LocalDateTime.now().plusDays(3).plusHours(5)))),
                new Flight(List.of(new Segment(LocalDateTime.now().plusDays(3).minusDays(6), LocalDateTime.now().plusDays(3)))),
                new Flight(List.of(new Segment(LocalDateTime.now().plusDays(3), LocalDateTime.now().plusDays(3).plusHours(2)), new Segment(LocalDateTime.now().plusDays(3).plusHours(5), LocalDateTime.now().plusDays(3).plusHours(6)))),
                new Flight(List.of(new Segment(LocalDateTime.now().plusDays(3), LocalDateTime.now().plusDays(3).plusHours(2)), new Segment(LocalDateTime.now().plusDays(3).plusHours(3), LocalDateTime.now().plusDays(3).plusHours(4)), new Segment(LocalDateTime.now().plusDays(3).plusHours(6), LocalDateTime.now().plusDays(3).plusHours(7)))));
        List<Flight> actualList = filterFactory.getArrivalDateBeforeDepartureDateFilter().filterArrivalDateBeforeDepartureDate(flights.getFlights());
        assertEquals(actualList.toString(), expextedList.toString());
    }

    @Test
    void shouldReturnFlightWithoutParkingMoreThen2Hours() {
        // Returns parkingMoreThen2Hours
        List<Flight> expextedList = Arrays.asList(
                new Flight(List.of(new Segment(LocalDateTime.now().plusDays(3), LocalDateTime.now().plusDays(3).plusHours(2)))),
                new Flight(List.of(new Segment(LocalDateTime.now().plusDays(3), LocalDateTime.now().plusDays(3).plusHours(2)), new Segment(LocalDateTime.now().plusDays(3).plusHours(3), LocalDateTime.now().plusDays(3).plusHours(5)))),
                new Flight(List.of(new Segment(LocalDateTime.now().plusDays(3).minusDays(6), LocalDateTime.now().plusDays(3)))),
                new Flight(List.of(new Segment(LocalDateTime.now().plusDays(3), LocalDateTime.now().plusDays(3).minusHours(6)))));
        List<Flight> actualList = filterFactory.getParkingMoreThen2HoursFilter().filterParkingMoreThen2Hours(flights.getFlights());
        assertEquals(actualList.toString(), expextedList.toString());
    }
}