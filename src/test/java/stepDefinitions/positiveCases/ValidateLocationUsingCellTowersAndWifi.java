package stepDefinitions.positiveCases;

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

public class ValidateLocationUsingCellTowersAndWifi {

    private RequestSpecification request;
    private Response response;

    @Given("I have geolocation api with cell towers and wifi access points")
    public void i_have_geolocation_api_with_cell_towers_and_wifi_access_points() throws IOException {
        String reqPayload = JSONUtils.readJsonAsString(DataFilePaths.GET_LOCATION_USING_CELL_TOWERS_AND_WIFI_REQ);
        request = APIUtils.createRequest(reqPayload);
    }

    @When("I submit the request to the API with the towers and wifi access points")
    public void i_submit_the_request_to_the_api_with_the_towers_and_wifi_access_points() {
        response = request.when().post("/geolocation/v1/geolocate");
    }

    @Then("the response should match the expected values based on towers and wifi access points")
    public void the_response_should_match_the_expected_values_based_on_towers_and_wifi_access_points() throws IOException {
        String expectedResponseJson = JSONUtils.readJsonAsString(DataFilePaths.GET_LOCATION_USING_CELL_TOWERS_AND_WIFI_RES);
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
