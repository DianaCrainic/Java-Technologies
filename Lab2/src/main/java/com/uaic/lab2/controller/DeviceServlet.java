package com.uaic.lab2.controller;

import com.uaic.lab2.model.CategoryType;
import com.uaic.lab2.model.Device;
import com.uaic.lab2.model.DeviceFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeviceServlet", urlPatterns = {"/"})
public class DeviceServlet extends HttpServlet {

    private final DeviceFactory deviceFactory;
    private static final String DEFAULT_CATEGORY = "default_category";
    private static final String INPUT_PAGE = "/input.jsp";
    private static final String RESULT_PAGE = "/result.jsp";

    public DeviceServlet() {
        this.deviceFactory = new DeviceFactory();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CategoryType categoryType = getCategory(req);
        String key = req.getParameter("key");
        String value = req.getParameter("value");
        String nextPage;

        if (categoryType != null && key != null && !key.isEmpty() && value != null && !value.isEmpty()) {
            nextPage = RESULT_PAGE;
            Device device = new Device(categoryType, key, value);
            deviceFactory.addDevice(device);
            req.setAttribute("devices", this.deviceFactory.getDevices());
            Cookie selectedCategoryCookie = getSelectedCategoryCookie(categoryType);
            resp.addCookie(selectedCategoryCookie);
        } else {
            nextPage = INPUT_PAGE;
        }
        getServletContext().getRequestDispatcher(nextPage).forward(req, resp);
    }

    private CategoryType getCategory(HttpServletRequest req) {
        String category = req.getParameter("category");
        CategoryType categoryType = null;
        if (category == null || category.equals("")) {
            category = (String) getServletContext().getAttribute(DEFAULT_CATEGORY);
        }
        if (category.equals("INPUT")) {
            categoryType = CategoryType.INPUT;
        } else {
            if (category.equals("OUTPUT")) {
                categoryType = CategoryType.OUTPUT;
            }
        }
        return categoryType;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    private Cookie getSelectedCategoryCookie(CategoryType categoryType) {
        Cookie selectedCategoryCookie = new Cookie("selectedCategoryCookie", categoryType.toString());
        selectedCategoryCookie.setMaxAge(30 * 60);
        return selectedCategoryCookie;
    }
}
