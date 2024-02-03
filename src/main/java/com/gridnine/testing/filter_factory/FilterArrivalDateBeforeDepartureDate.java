package com.gridnine.testing.filter_factory;

import com.gridnine.testing.model.Flight;

import java.util.List;

public interface FilterArrivalDateBeforeDepartureDate {
    List<Flight> filterArrivalDateBeforeDepartureDate(List<Flight> flights);
}
