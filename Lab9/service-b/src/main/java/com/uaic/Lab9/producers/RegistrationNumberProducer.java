package com.uaic.Lab9.producers;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import java.util.Random;

@ApplicationScoped
public class RegistrationNumberProducer {
    @Produces
    @DocumentRegistrationNumber
    public Integer getRegistrationNumber() {
        return Math.abs(new Random().nextInt());
    }
}
