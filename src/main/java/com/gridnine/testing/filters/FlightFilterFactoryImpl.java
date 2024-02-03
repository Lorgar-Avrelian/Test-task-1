package com.gridnine.testing.filters;

import com.gridnine.testing.filter_factory.FilterArrivalDateBeforeDepartureDate;
import com.gridnine.testing.filter_factory.FilterDepartureBeforeNow;
import com.gridnine.testing.filter_factory.FilterParkingMoreThen2Hours;
import com.gridnine.testing.filter_factory.FlightFilterFactory;

public class FlightFilterFactoryImpl implements FlightFilterFactory {

    @Override
    public FilterDepartureBeforeNow getDepartureBeforeNowFilter() {
        return new FilterDepartureBeforeNowImpl();
    }

    @Override
    public FilterArrivalDateBeforeDepartureDate getArrivalDateBeforeDepartureDateFilter() {
        return new FilterArrivalDateBeforeDepartureDateImpl();
    }

    @Override
    public FilterParkingMoreThen2Hours getParkingMoreThen2HoursFilter() {
        return new FilterParkingMoreThen2HoursImpl();
    }
}
