package stepDefinitions.negativeCases;

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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ValidateNotFoundRequests {
    private RequestSpecification request;
    private Response response;

    @Given("I have geolocation api with one wifi access point")
    public void i_have_geolocation_api_with_one_wifi_access_point() throws IOException {
        String reqPayload = JSONUtils.readJsonAsString(DataFilePaths.GET_LOCATION_USING_WIFI_ACCESS_POINTS_INVALID_REQ);
        request = APIUtils.createRequest(reqPayload);
    }

    @When("I submit the request to the API with the access point")
    public void i_submit_the_request_to_the_API_with_the_access_point() {
        response = request.when().post(ConfigLoader.getProperty("api.endpoint"));
    }

    @Then("the response should be not found with status {int}")
    public void the_response_should_be_not_found_with_status(int expectedStatusCode) throws IOException {
        String responseSchema = JSONUtils.readJsonAsString(DataFilePaths.GET_SCHEMA_404);
        assertEquals(expectedStatusCode, response.getStatusCode());
        assertTrue(JsonSchemaValidator.matchesJsonSchema(responseSchema).matches(response.getBody().asString()));
    }

}
