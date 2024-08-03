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

public class ValidateLocationUsingCellDeviceDataFields {

    private RequestSpecification request;
    private Response response;

    @Given("I have geolocation api with {int}, {int}, {string} and {string}")
    public void i_have_geolocation_api_with_and(int homeMobileCountryCode, int  homeMobileNetworkCode, String radioType, String carrier) throws IOException {
        String reqPayload = JSONUtils.readJsonAsString(DataFilePaths.GET_LOCATION_USING_CELL_TOWERS_AND_WIFI_REQ);
        reqPayload = JSONUtils.updateCellDeviceFieldsInJSON(reqPayload, homeMobileCountryCode, homeMobileNetworkCode, radioType, carrier);
        request = APIUtils.createRequest(reqPayload);
    }
    @When("I submit the request to the API with valid combinations")
    public void i_submit_the_request_to_the_api_with_valid_combinations() {
        response = request.when().post(ConfigLoader.getProperty("api.endpoint"));
    }
    @Then("the response should match the expected schema")
    public void the_response_should_match_the_expected_schema() throws IOException {
        String responseSchema = JSONUtils.readJsonAsString(DataFilePaths.GET_SCHEMA_200);
        assertEquals(200, response.getStatusCode());
        assertTrue(JsonSchemaValidator.matchesJsonSchema(responseSchema).matches(response.getBody().asString()));
    }

}
