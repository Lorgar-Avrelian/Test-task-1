package com.gridnine.testing.filters;

import com.gridnine.testing.filter_factory.FilterDepartureBeforeNow;
import com.gridnine.testing.model.Flight;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class FilterDepartureBeforeNowImpl implements FilterDepartureBeforeNow {
    @Override
    public List<Flight> filterDepartureBeforeNow(List<Flight> flights) {
        LocalDateTime timeNow = LocalDateTime.now();
        return flights.stream()
                      .map(Flight::getSegments)
                      .map(segments -> {
                          for (int i = 0; i < segments.size(); i++) {
                              if (segments.get(i).getDepartureDate().isBefore(timeNow)) {
                                  segments.set(i, null);
                              }
                              segments = segments.stream().filter(Objects::nonNull).collect(Collectors.toList());
                          }
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
