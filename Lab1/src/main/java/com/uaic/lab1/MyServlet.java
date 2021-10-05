package com.uaic.lab1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

@WebServlet("/query")
public class MyServlet extends HttpServlet {
    final String FILENAME = "repository.txt";
    final String FILE_PATH = "C:\\Users\\diana\\Desktop\\";

    private void syncWriteToFile(String key, int value, Timestamp timestamp) {
        FileOutputStream fout;
        FileDescriptor fd;
        File file;
        try {
            file = new File(FILE_PATH + FILENAME);
            fout = new FileOutputStream(file);
            fd = fout.getFD();
            FileOutputStream fout2 = new FileOutputStream(fd);
            for (int i = 0; i < value; ++i) {
                String line = key + '\n';
                fout2.write(line.getBytes());
            }
            fd.sync();
            fout2.write(String.valueOf(timestamp).getBytes());
            System.out.println("Successful");
            fout2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private StringBuilder syncGetHtmlPage() {
        FileInputStream fin;
        File file;
        StringBuilder fileContent = new StringBuilder("<h1>Content of file:</h1><ul><li>");
        try {
            file = new File(FILE_PATH + FILENAME);
            fin = new FileInputStream(file);
            int ch;
            while ((ch = fin.read()) != -1) {
                if (String.valueOf((char) ch).equals("\n")) {
                    fileContent.append("</li>").append("<li>");
                }
                fileContent.append((char) ch);
            }
            fileContent.append("</ul>");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileContent;
    }

    private StringBuilder syncGetTextPage() {
        FileInputStream fin;
        File file;
        StringBuilder fileContent = new StringBuilder("Content of file: \n");
        try {
            file = new File(FILE_PATH + FILENAME);
            fin = new FileInputStream(file);
            int ch;
            while ((ch = fin.read()) != -1) {
                fileContent.append((char) ch);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileContent;
    }

    private void writeToFile(String key, int value, Timestamp timestamp) {
        try {
            FileWriter fileWriter = new FileWriter(FILE_PATH + FILENAME);
            for (int i = 0; i < value; ++i) {
                fileWriter.write(key + '\n');
            }
            fileWriter.write(String.valueOf(timestamp));
            fileWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private StringBuilder getHtmlPage() {
        StringBuilder fileContent = new StringBuilder("<h1>Content of file:</h1><ul>");
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader(FILE_PATH + FILENAME));
            String line = bufferedReader.readLine();
            while (line != null) {
                fileContent.append("<li>").append(line).append("</li>");
                line = bufferedReader.readLine();
            }
            fileContent.append("</ul>");
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileContent;
    }

    private StringBuilder getTextPage() {
        StringBuilder fileContent = new StringBuilder("Content of file:\n");
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader(FILE_PATH + FILENAME));
            String line = bufferedReader.readLine();
            while (line != null) {
                fileContent.append(line).append("\n");
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileContent;
    }

    private void writeServerLog(HttpServletRequest req) {
        String requestInformation = "";
        requestInformation += "\nthe HTTP method used: " + req.getMethod() + '\n';
        requestInformation += "the IP-address of the client:" + req.getRemoteAddr() + '\n';
        requestInformation += "the user-agent: " + req.getHeader("User-Agent") + '\n';
        StringBuilder languages = getLanguages(req);
        requestInformation += "the client language(s): " + languages + '\n';
        requestInformation += "the parameters of the request: ";
        StringBuilder parameters = getParameters(req);
        requestInformation += parameters;
        log(requestInformation);
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();

        if (mock) {
            writer.println("Everything works!");
        } else {
            Date date = new Date();
            Timestamp timestamp = new Timestamp(date.getTime());
            if (sync) {
                syncWriteToFile(key, value, timestamp);
                StringBuilder syncFileContent;
                if (req.getHeader("User-Agent").equals("python-requests/2.26.0")) {
                    syncFileContent = syncGetTextPage();
                } else {
                    syncFileContent = syncGetHtmlPage();
                }
                writer.println(syncFileContent);
            } else {
                writeToFile(key, value, timestamp);
                StringBuilder fileContent;
                if (req.getHeader("User-Agent").equals("python-requests/2.26.0")) {
                    fileContent = getTextPage();
                } else {
                    fileContent = getHtmlPage();
                }
                writer.println(fileContent);
                writer.println("Mock is false, sync is also false");
            }
        }
        writeServerLog(req);
        writer.flush();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}