Feature: Validate Edge Cases for Geolocation API

  Scenario Outline: Verify that the API can identify client's location when radioType is CDMA
    Given I have geolocation api with radioType as CDMA and cell fields as <homeMobileNetworkCode>, <locationAreaCode> and <cellId>
    When I submit the request to the API with these values
    Then the response should be success and match the expected schema

    Examples:
      | homeMobileNetworkCode | locationAreaCode | cellId |
      | 0                     | 65535            | 65535  |
      | 32767                 | 0                | 0      |

  Scenario Outline: Verify that the API can identify client's location when radioType is NR
    Given I have geolocation api with radioType as NR and cell fields as <homeMobileCountryCode>, <homeMobileNetworkCode>, <locationAreaCode> and <newRadioCellId>
    When I submit the request to the API with these values
    Then the response should be success and match the expected schema

    Examples:
      | homeMobileCountryCode | homeMobileNetworkCode | locationAreaCode | newRadioCellId |
      | 999                   | 0                     | 16777215         | 0              |
      | 0                     | 999                   | 0                | 68719476735    |

  Scenario Outline: Verify that the API can identify client's location when radioType is GSM, WCDMA or LTE
    Given I have geolocation api with radioType as "<radioType>" and cell fields as <homeMobileCountryCode>, <homeMobileNetworkCode>, <locationAreaCode> and <cellId>
    When I submit the request to the API with these values
    Then the response should be success and match the expected schema

    Examples:
      | radioType | homeMobileNetworkCode | homeMobileCountryCode | locationAreaCode |cellId     |
      | gsm       | 0                     | 999                   | 0                | 65535     |
      | gsm       | 999                   | 0                     | 65535            | 0         |
      | wcdma     | 0                     | 999                   | 0                | 268435455 |
      | wcdma     | 999                   | 0                     | 65535            | 0         |
      | lte       | 0                     | 999                   | 0                | 268435455 |
      | lte       | 999                   | 0                     | 65535            | 0         |

  Scenario: Verify that the API can identify client's location when radioType is not sent
    Given I have geolocation api without radioType field
    When I submit the request to the API without this field
    Then the response should be success and match the expected values


