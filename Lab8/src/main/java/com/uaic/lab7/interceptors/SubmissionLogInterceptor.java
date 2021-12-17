package com.uaic.lab7.interceptors;

import com.uaic.lab7.dtos.CreateDocumentDto;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@SubmissionLog
@Interceptor
public class SubmissionLogInterceptor implements Serializable {
    @AroundInvoke
    public Object logMethodEntry(InvocationContext invocationContext) throws Exception {
        String pathFile = "D:\\All\\[Facultate]\\[Master]\\Java\\Java-Technologies\\Lab8\\src\\main\\resources\\output\\logging.txt";
        CreateDocumentDto createDocumentDto = (CreateDocumentDto) invocationContext.getParameters()[0];
        String name = createDocumentDto.getName();
        String submission = String.format("Document: %s \n", name);
        Files.write(Paths.get(pathFile), submission.getBytes(), StandardOpenOption.APPEND);
        return invocationContext.proceed();
    }
}
