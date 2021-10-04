package com.uaic.lab1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Timestamp;
import java.util.Date;
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
                if(String.valueOf((char)ch).equals("\n")){
                    fileContent.append("</li>").append("<li>");
                }
                fileContent.append((char)ch);
            }
            fileContent.append("</ul>");
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

    private String getHtmlPage() {
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
        return fileContent.toString();
    }

    private static String getClientIp(HttpServletRequest request) {
        String remoteAddress = "";
        if (request != null) {
            remoteAddress = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddress == null || "".equals(remoteAddress)) {
                remoteAddress = request.getRemoteAddr();
            }
        }
        return remoteAddress;
    }

    private void writeServerLog(HttpServletRequest req) {
        String requestInformation = "";
        requestInformation += "\nthe HTTP method used: " + req.getMethod() + '\n';
        requestInformation += "the IP-address of the client:" + getClientIp(req) + '\n';
        requestInformation += "the user-agent: " + req.getHeader("User-Agent") + '\n';
        requestInformation += "the client language(s): " + System.getProperty("user.language") + '\n';
        requestInformation += "the parameters of the request: ";
        Map<String, String[]> params = req.getParameterMap();
        for (String parameter : params.keySet()) {
            requestInformation += parameter + " | ";
        }
        // TODO cand am false pe un parametru, nu il ia in considerare in lista de parametri

        log(requestInformation);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String key = req.getParameter("key");
        String stringValue = req.getParameter("value");
        String stringMock = req.getParameter("mock");
        String stringSync = req.getParameter("sync");

        int value = Integer.parseInt(stringValue);
        boolean mock = stringMock != null;
        boolean sync = stringSync != null;

        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();

        if (mock) {
            writer.println("Everything works!");
        } else {
            Date date = new Date();
            Timestamp timestamp = new Timestamp(date.getTime());
            if (sync) {
                syncWriteToFile(key, value, timestamp);
                StringBuilder syncHtmlFileContent = syncGetHtmlPage();
                writer.println(syncHtmlFileContent);
            } else {
                writeToFile(key, value, timestamp);
                String htmlFileContent = getHtmlPage();
                writer.println(htmlFileContent);
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