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

public class ValidateSuccessRequests {
    private RequestSpecification request;
    private Response response;

    @Given("I have geolocation api with empty request body")
    public void i_have_geolocation_api_with_empty_request_body() throws IOException {
        request = APIUtils.createRequest("{}");
    }

    @When("I submit the request to the API with the empty body")
    public void i_submit_the_request_to_the_API_with_the_empty_body() {
        response = request.when().post(ConfigLoader.getProperty("api.endpoint"));
    }

    @Then("the response should be based on client IP with status {int}")
    public void the_response_should_be_based_on_client_IP_with_status(int expectedStatusCode) throws IOException {
        String responseSchema = JSONUtils.readJsonAsString(DataFilePaths.GET_SCHEMA_200);
        assertEquals(expectedStatusCode, response.getStatusCode());
        assertTrue(JsonSchemaValidator.matchesJsonSchema(responseSchema).matches(response.getBody().asString()));
    }
}
