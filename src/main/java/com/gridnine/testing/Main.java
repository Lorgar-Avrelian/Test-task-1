package com.gridnine.testing;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Flights;
import com.gridnine.testing.service.FlightService;
import com.gridnine.testing.service.impl.FlightServiceImpl;
import com.gridnine.testing.test_factory.FlightBuilder;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Obtaining test samples
        System.out.println(FlightBuilder.createFlights());
        System.out.println("========================================================================================");
        // Task solution
        // Filtering flights departing in the past
        departureBeforeNow();
        // Filtering flights that departs before it arrives
        arrivalDateBeforeDepartureDate();
        // Filtering flights with more than two hours ground time
        parkingMoreThen2Hours();
        // Filtering flights departing in the past, that departs before it arrives and with more than two hours ground time
        departureBeforeNowAndArrivalDateBeforeDepartureDateAndParkingMoreThen2Hours();
    }

    private static void departureBeforeNow() {
        Flights flights = new Flights(FlightBuilder.createFlights());
        FlightService flightService = new FlightServiceImpl(flights.getFlights());
        List<Flight> sortedFlights = flightService
                .departureBeforeNow()
                .filter();
        System.out.println(sortedFlights);
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }

    private static void arrivalDateBeforeDepartureDate() {
        Flights flights = new Flights(FlightBuilder.createFlights());
        FlightService flightService = new FlightServiceImpl(flights.getFlights());
        List<Flight> sortedFlights = flightService
                .arrivalDateBeforeDepartureDate()
                .filter();
        System.out.println(sortedFlights);
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }

    private static void parkingMoreThen2Hours() {
        Flights flights = new Flights(FlightBuilder.createFlights());
        FlightService flightService = new FlightServiceImpl(flights.getFlights());
        List<Flight> sortedFlights = flightService
                .parkingMoreThen2Hours()
                .filter();
        System.out.println(sortedFlights);
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }

    private static void departureBeforeNowAndArrivalDateBeforeDepartureDateAndParkingMoreThen2Hours() {
        Flights flights = new Flights(FlightBuilder.createFlights());
        FlightService flightService = new FlightServiceImpl(flights.getFlights());
        List<Flight> sortedFlights = flightService
                .departureBeforeNow()
                .arrivalDateBeforeDepartureDate()
                .parkingMoreThen2Hours()
                .filter();
        System.out.println(sortedFlights);
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }
}