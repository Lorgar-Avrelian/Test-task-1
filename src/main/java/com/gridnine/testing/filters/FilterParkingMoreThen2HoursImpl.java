package com.gridnine.testing.filters;

import com.gridnine.testing.filter_factory.FilterParkingMoreThen2Hours;
import com.gridnine.testing.model.Flight;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class FilterParkingMoreThen2HoursImpl  implements FilterParkingMoreThen2Hours {
    @Override
    public List<Flight> filterParkingMoreThen2Hours(List<Flight> flights) {
        return flights.stream()
                      .map(Flight::getSegments)
                      .map(segments -> {
                          if (segments.size() > 1) {
                              long parkingTime = 0;
                              for (int i = 0; i < segments.size() - 1; i++) {
                                  parkingTime += ChronoUnit.MINUTES.between(segments.get(i).getArrivalDate(), segments.get(i + 1).getDepartureDate());
                              }
                              if (parkingTime <= 120) {
                                  return new Flight(segments);
                              } else {
                                  return null;
                              }
                          }
                          return new Flight(segments);
                      })
                      .filter(Objects::nonNull)
                      .collect(Collectors.toList());
    }
}
