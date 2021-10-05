package com.uaic.lab1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;

public class AsyncOperations {
    final String FILENAME = "repository.txt";
    final String FILE_PATH = "C:\\Users\\diana\\Desktop\\";

    public void writeToFile(String key, int value, Timestamp timestamp) {
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

    public StringBuilder getHtmlPage() {
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

    public StringBuilder getTextPage() {
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
}
