package controllers;

import io.restassured.response.Response;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;


public class BookControllerTest {
    private static final String PATH = "http://localhost:9081/api/v1/books";

    @Test
    public void getAlBooks() {
        when().get(PATH).then().statusCode(200).contentType("application/json");
    }

    @Test
    public void getTheMostProfitableBooksForLibrary() {
        Integer libraryId = 1;

        when().get(String.format("%s?libraryId=%d", PATH, libraryId)).then().statusCode(200).contentType("application/json");
    }

    @Test
    public void getAllBooksWithAuthorId1() {
        Integer authorId = 1;
        when().get(String.format("%s?authorId=%d", PATH, authorId))
                .then().statusCode(200).contentType("application/json");
    }

    @Test
    public void getAllBooksWithNonExistingId() {
        Integer authorId = 100;
        when().get(String.format("%s?authorId=%d", PATH, authorId))
                .then().toString().isEmpty();
    }

    @Test
    public void createBookWithNoSpecifiedContent() {
        when().post(PATH).then().statusCode(500);
    }

    @Test
    public void createBook() {
        String requestBody = "{\n" +
                "  \"title\": \"Book\",\n" +
                "  \"review\": 2,\n" +
                "  \"price\": 60,\n" +
                "  \"authorId\": 2 \n}";

        Response response = given().header("Content-type", "application/json")
                .body(requestBody).when().post(PATH).then()
                .extract().response();
        Assertions.assertEquals(201, response.statusCode());
    }

    @Test
    public void updateBook() {
        String requestBody = "{\n" +
                "  \"title\": \"Book7\",\n" +
                "  \"review\": 2,\n" +
                "  \"price\": 20 \n}";
        Integer id = 7;

        Response response = given().header("Content-type", "application/json")
                .body(requestBody).when().put(PATH + "/" + id).then()
                .extract().response();
        Assertions.assertEquals(204, response.statusCode());
    }

//    @Test
    public void deleteABook() {
        Integer id = 9;
        Response response = given().header("Content-type", "application/json")
                .when().delete(PATH + "/" + id).then()
                .extract().response();
        Assertions.assertEquals(204, response.statusCode());
    }
}
