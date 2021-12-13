package com.uaic.lab7.beans;

import com.uaic.lab7.decorators.ICurrentTimeUtils;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class CurrentTimeBean implements Serializable {
    @Inject
    private ICurrentTimeUtils currentTimeUtils;

    public String getCurrentTime() {
        return currentTimeUtils.getCurrentTime();
    }
}