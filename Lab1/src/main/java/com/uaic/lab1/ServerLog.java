package com.uaic.lab1;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

public class ServerLog {
    public String writeServerLog(HttpServletRequest req) {
        String requestInformation = "";
        requestInformation += "\nthe HTTP method used: " + req.getMethod() + '\n';
        String ipAddress = getIPAddress(req);
        requestInformation += "the IP-address of the client: " + ipAddress + '\n';
        requestInformation += "the user-agent: " + req.getHeader("User-Agent") + '\n';
        StringBuilder languages = getLanguages(req);
        requestInformation += "the client language(s): " + languages + '\n';
        requestInformation += "the parameters of the request: ";
        StringBuilder parameters = getParameters(req);
        requestInformation += parameters;
        return requestInformation;
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
