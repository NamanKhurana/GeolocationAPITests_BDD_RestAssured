package utils;

public class DataFilePaths {

    // TestData
    public static final String GET_LOCATION_USING_IP_REQ = "/src/test/resources/testData/positiveCases/getLocationUsingIP.json";
    public static final String GET_LOCATION_USING_CELL_TOWERS_AND_WIFI_REQ = "/src/test/resources/testData/positiveCases/getLocationUsingCellTowersAndWifi.json";
    public static final String GET_LOCATION_USING_WIFI_ACCESS_POINTS_REQ = "/src/test/resources/testData/positiveCases/getLocationUsingWifiAccessPoints.json";
    public static final String GET_LOCATION_USING_CELL_TOWERS_REQ = "/src/test/resources/testData/positiveCases/getLocationUsingCellTowers.json";

    // Invalid TestData
    public static final String GET_LOCATION_USING_CELL_DATA_FIELDS_INVALID_REQ = "/src/test/resources/testData/negativeCases/getLocationUsingCellDataFields_INVALID.json";
    public static final String GET_LOCATION_USING_CELL_TOWERS_INVALID_REQ = "/src/test/resources/testData/negativeCases/getLocationUsingCellTowers_INVALID.json";
    public static final String GET_LOCATION_USING_WIFI_ACCESS_POINTS_INVALID_REQ = "/src/test/resources/testData/negativeCases/getLocationUsingWifiAccessPoint_INVALID.json";
    public static final String GET_LOCATION_WITHOUT_MAC_ADDRESS_INVALID_REQ = "/src/test/resources/testData/negativeCases/getLocationWithoutMacAddress_INVALID.json";

    // ResponseData
    public static final String GET_LOCATION_USING_IP_RES = "/src/test/resources/responseData/positiveCases/getLocationUsingIP.json";
    public static final String GET_LOCATION_USING_CELL_TOWERS_AND_WIFI_RES = "/src/test/resources/responseData/positiveCases/getLocationUsingCellTowersAndWifi.json";
    public static final String GET_LOCATION_USING_WIFI_ACCESS_POINTS_RES = "/src/test/resources/responseData/positiveCases/getLocationUsingWifiAccessPoints.json";
    public static final String GET_LOCATION_USING_CELL_TOWERS_RES = "/src/test/resources/responseData/positiveCases/getLocationUsingCellTowers.json";

    // Valid Response Schema
    public static final String GET_SCHEMA_200 = "/src/test/resources/responseData/schemas/responseSchema_200.json";
    public static final String GET_SCHEMA_400 = "/src/test/resources/responseData/schemas/responseSchema_400.json";
    public static final String GET_SCHEMA_400_1 = "/src/test/resources/responseData/schemas/responseSchema_400_1.json";
    public static final String GET_SCHEMA_403 = "/src/test/resources/responseData/schemas/responseSchema_403.json";
    public static final String GET_SCHEMA_404 = "/src/test/resources/responseData/schemas/responseSchema_404.json";
}
