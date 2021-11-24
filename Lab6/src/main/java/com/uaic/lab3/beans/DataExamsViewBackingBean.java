package com.uaic.lab3.beans;

import com.uaic.lab3.entities.Exam;
import com.uaic.lab3.filters.DateFilters;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class DataExamsViewBackingBean extends DataViewBackingBean<Exam> {
    protected DateFilters dateFilters = new DateFilters();
}
