package stepDefinitions.positiveCases;

import com.jayway.jsonpath.JsonPath;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.APIUtils;
import utils.ConfigLoader;
import utils.DataFilePaths;
import utils.JSONUtils;

import java.io.IOException;

import static org.junit.Assert.*;

public class ValidateLocationUsingWifiAccessPoints {
    private RequestSpecification request;
    private Response response;

    @Given("I have geolocation api with multiple wifi access points")
    public void i_have_geolocation_api_with_multiple_wifi_access_points() throws IOException {
        String reqPayload = JSONUtils.readJsonAsString(DataFilePaths.GET_LOCATION_USING_WIFI_ACCESS_POINTS_REQ);
        request = APIUtils.createRequest(reqPayload);
    }

    @When("I submit the request to the API with the access points")
    public void i_submit_the_request_to_the_api_with_the_access_points() {
        response = request.when().post(ConfigLoader.getProperty("api.endpoint"));
    }

    @Then("the response should match the expected schema with the required values")
    public void the_response_should_match_the_expected_schema_with_the_required_values() throws IOException {
        String expectedResponseJson = JSONUtils.readJsonAsString(DataFilePaths.GET_LOCATION_USING_WIFI_ACCESS_POINTS_RES);

        Double expectedLat = JsonPath.read(expectedResponseJson, "$.location.lat");
        Double expectedLng = JsonPath.read(expectedResponseJson, "$.location.lng");
        Double actualLat = response.jsonPath().getDouble("location.lat");
        Double actualLng = response.jsonPath().getDouble("location.lng");
        Double accuracy = response.jsonPath().getDouble("accuracy");

        assertEquals(200, response.getStatusCode());
        assertEquals(expectedLat, actualLat);
        assertEquals(expectedLng, actualLng);
        assertNotNull(accuracy);

        String responseSchema = JSONUtils.readJsonAsString(DataFilePaths.GET_SCHEMA_200);
        assertTrue(JsonSchemaValidator.matchesJsonSchema(responseSchema).matches(response.getBody().asString()));
    }
}