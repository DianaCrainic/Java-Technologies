package com.uaic.lab1;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Timestamp;
import java.util.Date;

@WebServlet("/query")
public class MyServlet extends HttpServlet {
    SyncOperations syncOperations = new SyncOperations();
    AsyncOperations asyncOperations = new AsyncOperations();
    ServerLog serverLog = new ServerLog();

    private StringBuilder syncGetContent(HttpServletRequest req) {
        StringBuilder syncFileContent;
        if (req.getHeader("User-Agent").equals("python-requests/2.26.0")) {
            syncFileContent = syncOperations.syncGetTextPage();
        } else {
            syncFileContent = syncOperations.syncGetHtmlPage();
        }
        return syncFileContent;
    }

    private StringBuilder getFileContent(HttpServletRequest req) {
        StringBuilder fileContent;
        if (req.getHeader("User-Agent").equals("python-requests/2.26.0")) {
            fileContent = asyncOperations.getTextPage();
        } else {
            fileContent = asyncOperations.getHtmlPage();
        }
        return fileContent;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String key = req.getParameter("key");
        String stringValue = req.getParameter("value");
        String stringMock = req.getParameter("mock");
        String stringSync = req.getParameter("sync");

        int value = Integer.parseInt(stringValue);
        boolean mock = false;
        if (stringMock != null) {
            mock = Boolean.parseBoolean(stringMock);
        }
        boolean sync = false;
        if (stringSync != null) {
            sync = Boolean.parseBoolean(stringSync);
        }
        PrintWriter writer = resp.getWriter();
        if (mock) {
            writer.println("Successful!\nMock is true!");
        } else {
            Date date = new Date();
            Timestamp timestamp = new Timestamp(date.getTime());
            if (sync) {
                syncOperations.syncWriteToFile(key, value, timestamp);
                StringBuilder syncFileContent = syncGetContent(req);
                writer.println(syncFileContent);
            } else {
                asyncOperations.writeToFile(key, value, timestamp);
                StringBuilder fileContent = getFileContent(req);
                writer.println(fileContent);
            }
        }
        String requestInformation = serverLog.writeServerLog(req);
        log(requestInformation);
        writer.flush();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doPost(req, resp);
    }
}