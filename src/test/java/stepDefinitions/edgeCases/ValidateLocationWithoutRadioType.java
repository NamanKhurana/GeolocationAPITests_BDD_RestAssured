package stepDefinitions.edgeCases;

import com.jayway.jsonpath.JsonPath;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.APIUtils;
import utils.DataFilePaths;
import utils.JSONUtils;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ValidateLocationWithoutRadioType {
    private RequestSpecification request;
    private Response response;

    @Given("I have geolocation api without radioType field")
    public void i_have_geolocation_api_without_radio_type_field() throws IOException {
        String reqPayload = JSONUtils.readJsonAsString(DataFilePaths.GET_LOCATION_WITHOUT_RADIO_TYPE_REQ);
        request = APIUtils.createRequest(reqPayload);
    }

    @When("I submit the request to the API without this field")
    public void i_submit_the_request_to_the_api_without_this_field() {
        response = request.when().post("/geolocation/v1/geolocate");
    }

    @Then("the response should be success and match the expected values")
    public void the_response_should_be_success_and_match_the_expected_values() throws IOException {
        String expectedResponseJson = JSONUtils.readJsonAsString(DataFilePaths.GET_LOCATION_WITHOUT_RADIO_TYPE_RES);
        assertEquals(200, response.getStatusCode());

        Double expectedLat = JsonPath.read(expectedResponseJson, "$.location.lat");
        Double expectedLng = JsonPath.read(expectedResponseJson, "$.location.lng");
        Double actualLat = response.jsonPath().getDouble("location.lat");
        Double actualLng = response.jsonPath().getDouble("location.lng");
        Double accuracy = response.jsonPath().getDouble("accuracy");

        assertEquals(expectedLat, actualLat);
        assertEquals(expectedLng, actualLng);
        assertNotNull(accuracy);
    }

}
