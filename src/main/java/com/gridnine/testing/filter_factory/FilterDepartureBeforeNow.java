package com.gridnine.testing.filter_factory;

import com.gridnine.testing.model.Flight;

import java.util.List;

public interface FilterDepartureBeforeNow {
    List<Flight> filterDepartureBeforeNow(List<Flight> flights);
}
