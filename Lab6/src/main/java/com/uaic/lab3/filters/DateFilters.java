package com.uaic.lab3.filters;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
public class DateFilters {
    private Timestamp startDate;
    private Timestamp endDate;
    private boolean useStartDateFilter;
    private boolean useEndDateFilter;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = getTimestamp(startDate);
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = getTimestamp(endDate);
    }

    public boolean isUseStartDateFilter() {
        return useStartDateFilter;
    }

    public void setUseStartDateFilter(boolean useStartDateFilter) {
        this.useStartDateFilter = useStartDateFilter;
    }

    public boolean isUseEndDateFilter() {
        return useEndDateFilter;
    }

    public void setUseEndDateFilter(boolean useEndDateFilter) {
        this.useEndDateFilter = useEndDateFilter;
    }

    public static Timestamp getTimestamp(Date date) {
        return date == null ? null : new Timestamp(date.getTime());
    }
}
