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

public class ValidateForbiddenRequests {
    private RequestSpecification request;
    private Response response;

    @Given("I have geolocation api without API Key")
    public void i_have_geolocation_api_without_API_Key() throws IOException {
        String reqPayload = JSONUtils.readJsonAsString(DataFilePaths.GET_LOCATION_USING_IP_REQ);
        request = APIUtils.createRequest(reqPayload, "");
    }

    @When("I submit the request to the API without the key")
    public void i_submit_the_request_to_the_API_without_the_key() {
        response = request.when().post(ConfigLoader.getProperty("api.endpoint"));
    }

    @Then("the response should be forbidden with status {int}")
    public void the_response_should_be_forbidden_with_status(int expectedStatusCode) throws IOException {
        String responseSchema = JSONUtils.readJsonAsString(DataFilePaths.GET_SCHEMA_403);
        assertEquals(expectedStatusCode, response.getStatusCode());
        assertTrue(JsonSchemaValidator.matchesJsonSchema(responseSchema).matches(response.getBody().asString()));
    }
}
