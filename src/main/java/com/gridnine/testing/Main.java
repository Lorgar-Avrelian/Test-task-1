package com.gridnine.testing;

import com.gridnine.testing.filter_factory.FlightFilterFactory;
import com.gridnine.testing.filters.FlightFilterFactoryImpl;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Flights;
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
    }

    private static void departureBeforeNow() {
        Flights flights = new Flights(FlightBuilder.createFlights());
        FlightFilterFactory filterFactory = new FlightFilterFactoryImpl();
        List<Flight> sortedFlights = filterFactory.getDepartureBeforeNowFilter().filterDepartureBeforeNow(flights.getFlights());
        System.out.println(sortedFlights);
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }

    private static void arrivalDateBeforeDepartureDate() {
        Flights flights = new Flights(FlightBuilder.createFlights());
        FlightFilterFactory filterFactory = new FlightFilterFactoryImpl();
        List<Flight> sortedFlights = filterFactory.getArrivalDateBeforeDepartureDateFilter().filterArrivalDateBeforeDepartureDate(flights.getFlights());
        System.out.println(sortedFlights);
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }

    private static void parkingMoreThen2Hours() {
        Flights flights = new Flights(FlightBuilder.createFlights());
        FlightFilterFactory filterFactory = new FlightFilterFactoryImpl();
        List<Flight> sortedFlights = filterFactory.getParkingMoreThen2HoursFilter().filterParkingMoreThen2Hours(flights.getFlights());
        System.out.println(sortedFlights);
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }
}