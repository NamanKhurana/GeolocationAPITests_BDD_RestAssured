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

public class ValidateBadRequests {
    private RequestSpecification request;
    private Response response;

    @Given("I have geolocation api with invalid API Key")
    public void i_have_geolocation_api_with_invalid_api_key() throws IOException {
        String reqPayload = JSONUtils.readJsonAsString(DataFilePaths.GET_LOCATION_USING_IP_REQ);
        request = APIUtils.createRequest(reqPayload, "invalidKey");
    }

    @When("I submit the request to the API with the invalid key")
    public void i_submit_the_request_to_the_api_with_the_invalid_key() {
        response = request.when().post(ConfigLoader.getProperty("api.endpoint"));
    }

    @Given("I have geolocation api with invalid data fields")
    public void  i_have_geolocation_api_with_invalid_data_fields() throws IOException {
        String reqPayload = JSONUtils.readJsonAsString(DataFilePaths.GET_LOCATION_USING_CELL_DATA_FIELDS_INVALID_REQ);
        request = APIUtils.createRequest(reqPayload);
    }

    @When("I submit the request to the API with the invalid fields")
    public void i_submit_the_request_to_the_API_with_the_invalid_fields() {
        response = request.when().post(ConfigLoader.getProperty("api.endpoint"));
    }

    @Given("I have geolocation api without mac address")
    public void i_have_geolocation_api_without_mac_address() throws IOException {
        String reqPayload = JSONUtils.readJsonAsString(DataFilePaths.GET_LOCATION_WITHOUT_MAC_ADDRESS_INVALID_REQ);
        request = APIUtils.createRequest(reqPayload);
    }

    @When("I submit the request to the API without the address")
    public void i_submit_the_request_to_the_API_without_the_address() {
        response = request.when().post(ConfigLoader.getProperty("api.endpoint"));
    }

    @Given("I have geolocation api with some required fields missing in cell tower object")
    public void i_have_geolocation_api_with_some_required_fields_missing_in_cell_tower_object() throws IOException {
        String reqPayload = JSONUtils.readJsonAsString(DataFilePaths.GET_LOCATION_USING_CELL_TOWERS_INVALID_REQ);
        request = APIUtils.createRequest(reqPayload);
    }

    @When("I submit the request to the API without the required fields")
    public void i_submit_the_request_to_the_API_without_the_required_fields() {
        response = request.when().post(ConfigLoader.getProperty("api.endpoint"));
    }

    @Then("the response should be bad request with status {int}")
    public void the_response_should_be_bad_request_with_status(int expectedStatusCode) throws IOException {
        String responseSchema = JSONUtils.readJsonAsString(DataFilePaths.GET_SCHEMA_400);
        assertEquals(expectedStatusCode, response.getStatusCode());
        response.prettyPrint();
        assertTrue(JsonSchemaValidator.matchesJsonSchema(responseSchema).matches(response.getBody().asString()));
    }

    @Then("the response should be bad request with status {int} and matching schema")
    public void the_response_should_be_bad_request_with_status_and_matching_schema(int expectedStatusCode) throws IOException {
        String responseSchema = JSONUtils.readJsonAsString(DataFilePaths.GET_SCHEMA_400_1);
        assertEquals(expectedStatusCode, response.getStatusCode());
        assertTrue(JsonSchemaValidator.matchesJsonSchema(responseSchema).matches(response.getBody().asString()));
    }
}
