package stepDefinitions.edgeCases;

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

public class ValidateLocationByRadioTypeBoundaryValues {
    private RequestSpecification request;
    private Response response;

    @Given("I have geolocation api with radioType as CDMA and cell fields as {int}, {int} and {int}")
    public void i_have_geolocation_api_with_radio_type_as_cdma_and_cell_fields_as_and(int homeMobileNetworkCode, int locationAreaCode, int cellId) throws IOException {
        String reqPayload = JSONUtils.readJsonAsString(DataFilePaths.GET_LOCATION_WITH_CDMA_RADIO_TYPE_REQ);
        reqPayload = JSONUtils.updateCellDeviceFieldsInJSON(reqPayload, homeMobileNetworkCode, locationAreaCode, cellId);
        request = APIUtils.createRequest(reqPayload);
    }

    @Given("I have geolocation api with radioType as NR and cell fields as {int}, {int}, {int} and {long}")
    public void i_have_geolocation_api_with_radio_type_as_NR_and_cell_fields_as_and(int homeMobileCountryCode, int homeMobileNetworkCode, int locationAreaCode, long newRadioCellId) throws IOException {
        String reqPayload = JSONUtils.readJsonAsString(DataFilePaths.GET_LOCATION_WITH_NR_RADIO_TYPE_REQ);
        reqPayload = JSONUtils.updateCellDeviceFieldsInJSON(reqPayload, homeMobileCountryCode, homeMobileNetworkCode, locationAreaCode, newRadioCellId);
        request = APIUtils.createRequest(reqPayload);
    }

    @Given("I have geolocation api with radioType as {string} and cell fields as {int}, {int}, {int} and {int}")
    public void i_have_geolocation_api_with_radio_type_as_and_cell_fields_as_and(String radioType, int homeMobileNetworkCode, int homeMobileCountryCode, int locationAreaCode, int cellId) throws IOException {
        String reqPayload = JSONUtils.readJsonAsString(DataFilePaths.GET_LOCATION_WITH_MULTIPLE_RADIO_TYPES_REQ);
        reqPayload = JSONUtils.updateCellDeviceFieldsInJSON(reqPayload, radioType, homeMobileCountryCode, homeMobileNetworkCode, locationAreaCode, cellId);
        request = APIUtils.createRequest(reqPayload);
    }

    @When("^I submit the request to the API with these values$")
    public void i_submit_the_request_to_the_API_with_these_values() {
        response = request.when().post(ConfigLoader.getProperty("api.endpoint"));
    }

    @Then("^the response should be success and match the expected schema$")
    public void the_response_should_be_success_and_match_the_expected_schema() throws IOException {
        String responseSchema = JSONUtils.readJsonAsString(DataFilePaths.GET_SCHEMA_200);
        assertEquals(200, response.getStatusCode());
        assertTrue(JsonSchemaValidator.matchesJsonSchema(responseSchema).matches(response.getBody().asString()));
    }
}
