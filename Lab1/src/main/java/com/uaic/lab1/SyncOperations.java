package com.uaic.lab1;

import java.io.*;
import java.sql.Timestamp;

public class SyncOperations {
    final String FILENAME = "repository.txt";
    final String FILE_PATH = "C:\\Users\\diana\\Desktop\\";

    public synchronized void syncWriteToFile(String key, int value, Timestamp timestamp) {
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

    public synchronized StringBuilder syncGetHtmlPage() {
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

    public synchronized StringBuilder syncGetTextPage() {
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
}
