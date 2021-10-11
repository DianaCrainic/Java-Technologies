package com.uaic.lab2.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener()
public class AppListener implements ServletContextListener {
    private static long startupTime = 0L;

    /* Application Startup Event */
    public void contextInitialized(ServletContextEvent ce) {
        startupTime = System.currentTimeMillis();
    }
}
