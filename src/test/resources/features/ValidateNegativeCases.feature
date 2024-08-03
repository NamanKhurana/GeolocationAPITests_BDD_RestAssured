Feature: Validate Negative Cases for Geolocation API

  Scenario: Verify API response with invalid API Key
    Given I have geolocation api with invalid API Key
    When I submit the request to the API with the invalid key
    Then the response should be bad request with status 400

  Scenario: Verify API response without API Key
    Given I have geolocation api without API Key
    When I submit the request to the API without the key
    Then the response should be forbidden with status 403

  Scenario: Verify API response when request body is empty
    Given I have geolocation api with empty request body
    When I submit the request to the API with the empty body
    Then the response should be based on client IP with status 200

  Scenario: Verify API response when cell device data fields are invalid
    Given I have geolocation api with invalid data fields
    When I submit the request to the API with the invalid fields
    Then the response should be bad request with status 400 and matching schema

  Scenario: Verify API response when single wifi access point is sent in request
    Given I have geolocation api with one wifi access point
    When I submit the request to the API with the access point
    Then the response should be not found with status 404

  Scenario: Verify API response when mac address is not in wifi access point
    Given I have geolocation api without mac address
    When I submit the request to the API without the address
    Then the response should be bad request with status 400

  Scenario: Verify API response when some required fields are not sent with cell tower
    Given I have geolocation api with some required fields missing in cell tower object
    When I submit the request to the API without the required fields
    Then the response should be bad request with status 400