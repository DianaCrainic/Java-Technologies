package com.uaic.lab7.decorators;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class TimeUtils implements ICurrentTimeUtils, Serializable {

    @Override
    public String getCurrentTime() {
        return "Current Time";
    }
}
