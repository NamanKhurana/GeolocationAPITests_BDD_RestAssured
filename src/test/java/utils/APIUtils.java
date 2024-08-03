package utils;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class APIUtils {
    private static final String baseUrl = ConfigLoader.getProperty("api.baseurl");
    private static final String apiKey = ConfigLoader.getProperty("api.key");

    public static RequestSpecification createRequest(String payload) {
        return RestAssured.given()
                .baseUri(baseUrl)
                .contentType("application/json")
                .queryParam("key", apiKey)
                .body(payload)
                .log().all();
    }

    public static RequestSpecification createRequest(String payload, String apiKey) {
        return RestAssured.given()
                .baseUri(baseUrl)
                .contentType("application/json")
                .queryParam("key", apiKey)
                .body(payload)
                .log().all();
    }
}
