package com.gridnine.testing.filter_factory;

import com.gridnine.testing.model.Flight;

import java.util.List;

public interface FilterParkingMoreThen2Hours {
    List<Flight> filterParkingMoreThen2Hours(List<Flight> flights);
}
