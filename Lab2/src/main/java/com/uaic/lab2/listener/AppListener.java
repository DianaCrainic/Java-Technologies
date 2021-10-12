package com.uaic.lab2.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener()
public class AppListener implements ServletContextListener {
    private static final String DEFAULT_CATEGORY = "default_category";

    public void contextInitialized(ServletContextEvent ce) {
        ServletContext servletContext = ce.getServletContext();
        String defaultCategory = servletContext.getInitParameter(DEFAULT_CATEGORY);
        servletContext.setAttribute(DEFAULT_CATEGORY, defaultCategory);
    }
}
