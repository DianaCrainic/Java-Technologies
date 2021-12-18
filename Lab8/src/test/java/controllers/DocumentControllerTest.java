package controllers;

import org.junit.Test;

import static io.restassured.RestAssured.when;


public class DocumentControllerTest {
    private static final String PATH = "http://localhost:8080/Lab7-1.0-SNAPSHOT/resources/documents";

    @Test
    public void getAlDocuments() {
        when().get(PATH).then().statusCode(200).contentType("application/json");
    }

    @Test
    public void getDocumentsForAuthorWithId2() {
        when().get(String.format("%s?authorId=%d", PATH, 5))
                .then().statusCode(200).contentType("application/json");
    }
}
