package com.uaic.lab2.controller;

import com.uaic.lab2.model.Device;
import com.uaic.lab2.model.DeviceFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeviceServlet", urlPatterns = {"/"})
public class DeviceServlet extends HttpServlet {

    private final DeviceFactory deviceFactory;

    public DeviceServlet() {
        this.deviceFactory = new DeviceFactory();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String category = req.getParameter("category");
        ServletContext servletContext = this.getServletContext();

        Device.CategoryType categoryType = null;
        String defaultCategory = null;
        if (category != null) {
            if (category.equals("INPUT")) {
                categoryType = Device.CategoryType.INPUT;
            } else {
                if (category.equals("OUTPUT")) {
                    categoryType = Device.CategoryType.OUTPUT;
                }
            }
        } else {
            defaultCategory = servletContext.getInitParameter("default_category");
            categoryType = Device.CategoryType.valueOf(defaultCategory);
        }

        String key = req.getParameter("key");
        String value = req.getParameter("value");

        String nextPage;
        if (categoryType != null && key != null && !key.isEmpty() && value != null && !value.isEmpty()) {
            nextPage = "/result.jsp";
            Device device = new Device(categoryType, key, value);
            deviceFactory.addDevice(device);
            req.setAttribute("devices", this.deviceFactory.getDevices());
        } else {
            nextPage = "/input.jsp";
        }
        getServletContext().getRequestDispatcher(nextPage).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
