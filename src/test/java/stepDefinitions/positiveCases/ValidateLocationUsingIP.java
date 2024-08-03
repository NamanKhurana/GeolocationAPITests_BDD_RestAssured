package stepDefinitions.positiveCases;

import com.jayway.jsonpath.JsonPath;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.APIUtils;
import utils.ConfigLoader;
import utils.DataFilePaths;
import utils.JSONUtils;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ValidateLocationUsingIP {

    private RequestSpecification request;
    private Response response;

    @Given("I have geolocation api with client's IP address")
    public void i_have_geolocation_api_with_client_s_ip_address() throws IOException {
        String reqPayload = JSONUtils.readJsonAsString(DataFilePaths.GET_LOCATION_USING_IP_REQ);
        request = APIUtils.createRequest(reqPayload);
    }

    @When("I submit the request to the API with the IP address")
    public void i_submit_the_request_to_the_api_with_the_ip_address() {
        response = request.when().post(ConfigLoader.getProperty("api.endpoint"));
    }

    @Then("the response should match the expected values based on the address")
    public void the_response_should_match_the_expected_values_based_on_the_address() throws IOException {
        String expectedResponseJson = JSONUtils.readJsonAsString(DataFilePaths.GET_LOCATION_USING_IP_RES);
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
