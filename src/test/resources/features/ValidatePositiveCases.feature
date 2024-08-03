Feature: Validate Positive Cases for Geolocation API

  Scenario: Verify that the API can identify client's location using only the IP address.
    Given I have geolocation api with client's IP address
    When I submit the request to the API with the IP address
    Then the response should match the expected values based on the address

  Scenario: Verify that the API can identify client's location using cell towers and wifi access points.
    Given I have geolocation api with cell towers and wifi access points
    When I submit the request to the API with the towers and wifi access points
    Then the response should match the expected values based on towers and wifi access points

  Scenario Outline: Verify that the API can identify client's location based on valid combinations of cell device data fields
    Given I have geolocation api with <homeMobileCountryCode>, <homeMobileNetworkCode>, "<radioType>" and "<carrier>"
    When I submit the request to the API with valid combinations
    Then the response should match the expected schema

    Examples:
      | homeMobileCountryCode | homeMobileNetworkCode | radioType | carrier |
      | 500                   | 900                   | gsm       | Verizon |
      | 100                   | 300                   | wcdma     | AT&T    |
      | 201                   | 999                   | lte       | Vodafone|

  Scenario: Verify that the API can identify client's location based on multiple wifi access points
    Given I have geolocation api with multiple wifi access points
    When I submit the request to the API with the access points
    Then the response should match the expected schema with the required values

  Scenario: Verify that the API can identify client's location based on cell towers
    Given I have geolocation api with cell towers having all the fields (including optional fields)
    When I submit the request to the API with the cell towers
    Then the response should match the expected schema and the desired values

