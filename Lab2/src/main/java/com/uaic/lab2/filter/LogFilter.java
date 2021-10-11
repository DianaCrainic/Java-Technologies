package com.uaic.lab2.filter;

import javax.servlet.*;
import javax.servlet.Filter;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

@WebFilter(urlPatterns = {"/*"})
public class LogFilter implements Filter {
    public FilterConfig filterConfig = null;

    @Override
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        String requestInformation = "";
        requestInformation += "\nthe HTTP method used: " + request.getMethod() + '\n';
        String ipAddress = getIPAddress(request);
        requestInformation += "the IP-address of the client: " + ipAddress + '\n';
        requestInformation += "the user-agent: " + request.getHeader("User-Agent") + '\n';
        StringBuilder languages = getLanguages(request);
        requestInformation += "the client language(s): " + languages + '\n';
        requestInformation += "the parameters of the request: ";
        StringBuilder parameters = getParameters(request);
        requestInformation += parameters;
        System.out.println(requestInformation);
        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {
        this.filterConfig = null;
    }

    private String getIPAddress(HttpServletRequest req) {
        String ipAddress = req.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = req.getRemoteAddr();
        }
        return ipAddress;
    }

    private StringBuilder getParameters(HttpServletRequest req) {
        StringBuilder parametersOfRequest = new StringBuilder();
        Map<String, String[]> params = req.getParameterMap();
        for (String parameter : params.keySet()) {
            parametersOfRequest.append(parameter).append(" | ");
        }
        return parametersOfRequest;
    }

    private StringBuilder getLanguages(HttpServletRequest req) {
        StringBuilder clientLanguages = new StringBuilder();
        Enumeration<Locale> languages = req.getLocales();
        while (languages.hasMoreElements()) {
            Locale language = languages.nextElement();
            clientLanguages.append(language).append(" | ");
        }
        return clientLanguages;
    }
}
