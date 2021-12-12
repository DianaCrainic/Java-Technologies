package com.uaic.lab7.interceptors;

import com.uaic.lab7.entities.TimeFrame;
import com.uaic.lab7.exceptions.InvalidTimeFrameException;
import com.uaic.lab7.repositories.TimeFrameRepository;

import javax.ejb.EJB;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@ValidTimeFrame
@Interceptor
public class ValidTimeFrameInterceptor implements Serializable {
    @EJB
    private TimeFrameRepository timeFrameRepository;

    @AroundInvoke
    public Object checkTimeFrame(InvocationContext invocationContext) throws Exception {
        List<TimeFrame> timeFrames = timeFrameRepository.getAll();
        boolean validTimeFrame = timeFrames.stream()
                .anyMatch(timeFrame -> timeFrame.getStartDate().before(new Date()) && timeFrame.getEndDate().after(new Date()));
        if (!validTimeFrame) {
            throw new InvalidTimeFrameException();
        }
        return invocationContext.proceed();
    }
}
