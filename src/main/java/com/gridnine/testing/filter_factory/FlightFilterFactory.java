package com.gridnine.testing.filter_factory;

public interface FlightFilterFactory {
    FilterDepartureBeforeNow getDepartureBeforeNowFilter();

    FilterArrivalDateBeforeDepartureDate getArrivalDateBeforeDepartureDateFilter();

    FilterParkingMoreThen2Hours getParkingMoreThen2HoursFilter();
}
