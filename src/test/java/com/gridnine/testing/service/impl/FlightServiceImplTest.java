package com.gridnine.testing.service.impl;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Flights;
import com.gridnine.testing.model.Segment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

@ExtendWith(MockitoExtension.class)
class FlightServiceImplTest {
    static Flights flights;
    static Flight beforeNowDeparture;
    static Flight arrivalDateBeforeDepartureDate;
    static Flight parkingMoreThen2Hours;
    @InjectMocks
    FlightServiceImpl flightService;

    @BeforeEach
    void beforeEach() {
        beforeNowDeparture = new Flight(List.of(new Segment(LocalDateTime.now().minusDays(3), LocalDateTime.now().plusDays(3))));
        arrivalDateBeforeDepartureDate = new Flight(List.of(new Segment(LocalDateTime.now().plusDays(3), LocalDateTime.now().plusDays(3).minusHours(6))));
        parkingMoreThen2Hours = new Flight(List.of(new Segment(LocalDateTime.now().plusDays(3), LocalDateTime.now().plusDays(3).plusHours(2)),
                                                   new Segment(LocalDateTime.now().plusDays(3).plusHours(5), LocalDateTime.now().plusDays(3).plusHours(6))));
        flights = new Flights(List.of(beforeNowDeparture, arrivalDateBeforeDepartureDate, parkingMoreThen2Hours));
    }

    @Test
    void shouldReturnFlightWithDepartureBeforeNow() {
        // Returns beforeNowDeparture
        List<Flight> expextedList = List.of(beforeNowDeparture);
        List<Flight> actualList = flightService.beforeNow(flights);
        assertIterableEquals(expextedList, actualList);
        // Returns empty list
        flights = new Flights(List.of(arrivalDateBeforeDepartureDate, parkingMoreThen2Hours));
        expextedList = new ArrayList<>();
        actualList = flightService.beforeNow(flights);
        assertEquals(expextedList, actualList);
    }

    @Test
    void shouldReturnFlightWithArrivalDateBeforeDepartureDate() {
        // Returns arrivalDateBeforeDepartureDate
        List<Flight> expextedList = List.of(arrivalDateBeforeDepartureDate);
        List<Flight> actualList = flightService.arrivalDateBeforeDepartureDate(flights);
        assertIterableEquals(expextedList, actualList);
        // Returns empty list
        flights = new Flights(List.of(beforeNowDeparture, parkingMoreThen2Hours));
        expextedList = new ArrayList<>();
        actualList = flightService.arrivalDateBeforeDepartureDate(flights);
        assertEquals(expextedList, actualList);
    }

    @Test
    void shouldReturnFlightWithParkingMoreThen2Hours() {
        // Returns parkingMoreThen2Hours
        List<Flight> expextedList = List.of(parkingMoreThen2Hours);
        List<Flight> actualList = flightService.parkingMoreThen2Hours(flights);
        assertIterableEquals(expextedList, actualList);
        // Returns empty list
        flights = new Flights(List.of(beforeNowDeparture, arrivalDateBeforeDepartureDate));
        expextedList = new ArrayList<>();
        actualList = flightService.parkingMoreThen2Hours(flights);
        assertEquals(expextedList, actualList);
    }
}