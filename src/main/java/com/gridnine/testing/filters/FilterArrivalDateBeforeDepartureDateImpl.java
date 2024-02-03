package com.gridnine.testing.filters;

import com.gridnine.testing.filter_factory.FilterArrivalDateBeforeDepartureDate;
import com.gridnine.testing.model.Flight;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class FilterArrivalDateBeforeDepartureDateImpl  implements FilterArrivalDateBeforeDepartureDate {
    @Override
    public List<Flight> filterArrivalDateBeforeDepartureDate(List<Flight> flights) {
        return flights.stream()
                      .map(Flight::getSegments)
                      .map(segments -> {
                          for (int i = 0; i < segments.size(); i++) {
                              if (segments.get(i).getDepartureDate().isAfter(segments.get(i).getArrivalDate())) {
                                  segments.set(i, null);
                              }
                          }
                          segments = segments.stream().filter(Objects::nonNull).collect(Collectors.toList());
                          if (!segments.isEmpty()) {
                              return new Flight(segments);
                          } else {
                              return null;
                          }
                      })
                      .filter(Objects::nonNull)
                      .collect(Collectors.toList());
    }
}
