package com.uaic.lab7.decorators;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.Date;

@Decorator
public abstract class CurrentTimeUtils implements ICurrentTimeUtils, Serializable {
    @Inject
    @Delegate
    @Any
    ICurrentTimeUtils currentTimeUtils;

    @Override
    public String getCurrentTime() {
        Date date = new Date();
        return "Current time: " + date.toString().substring(0, 10);
    }
}
