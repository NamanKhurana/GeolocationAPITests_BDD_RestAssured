package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class JSONUtils {

    public static Map<String, Object> readJsonAsMap(String filePath) throws IOException {
        String jsonContent = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + filePath)));
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonContent, Map.class);
    }

    public static String readJsonAsString(String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + filePath)));
    }

    public static String updateCellDeviceFieldsInJSON(String reqPayload, int homeMobileCountryCode, int homeMobileNetworkCode, String radioType, String carrier) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(reqPayload);

        ObjectNode objectNode = (ObjectNode) rootNode;
        objectNode.put("homeMobileCountryCode", homeMobileCountryCode);
        objectNode.put("homeMobileNetworkCode", homeMobileNetworkCode);
        objectNode.put("radioType", radioType);
        objectNode.put("carrier", carrier);

        return mapper.writeValueAsString(rootNode);
    }

    public static String updateCellDeviceFieldsInJSON(String reqPayload, int homeMobileNetworkCode, int locationAreaCode, int cellId) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(reqPayload);
        ObjectNode objectNode = (ObjectNode) rootNode;
        objectNode.put("homeMobileNetworkCode", homeMobileNetworkCode);

        ArrayNode cellTowers = (ArrayNode) rootNode.path("cellTowers");
        for (JsonNode cellTower : cellTowers) {
            ObjectNode cellTowerObject = (ObjectNode) cellTower;
            cellTowerObject.put("mobileNetworkCode", homeMobileNetworkCode);
            cellTowerObject.put("locationAreaCode", locationAreaCode);
            cellTowerObject.put("cellId", cellId);
        }


        return mapper.writeValueAsString(rootNode);
    }

    public static String updateCellDeviceFieldsInJSON(String reqPayload, int homeMobileCountryCode,int homeMobileNetworkCode,int locationAreaCode,long newRadioCellId) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(reqPayload);

        ObjectNode objectNode = (ObjectNode) rootNode;
        objectNode.put("homeMobileCountryCode", homeMobileCountryCode);
        objectNode.put("homeMobileNetworkCode", homeMobileNetworkCode);

        ArrayNode cellTowers = (ArrayNode) rootNode.path("cellTowers");
        for (JsonNode cellTower : cellTowers) {
            ObjectNode cellTowerObject = (ObjectNode) cellTower;
            cellTowerObject.put("mobileCountryCode", homeMobileCountryCode);
            cellTowerObject.put("mobileNetworkCode", homeMobileNetworkCode);
            cellTowerObject.put("locationAreaCode", locationAreaCode);
            cellTowerObject.put("newRadioCellId", newRadioCellId);
        }

        return mapper.writeValueAsString(rootNode);
    }

    public static String updateCellDeviceFieldsInJSON(String reqPayload, String radioType, int homeMobileCountryCode, int homeMobileNetworkCode, int locationAreaCode, int cellId) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(reqPayload);

        ObjectNode objectNode = (ObjectNode) rootNode;
        objectNode.put("homeMobileCountryCode", homeMobileCountryCode);
        objectNode.put("homeMobileNetworkCode", homeMobileNetworkCode);
        objectNode.put("radioType", radioType);

        ArrayNode cellTowers = (ArrayNode) rootNode.path("cellTowers");
        for (JsonNode cellTower : cellTowers) {
            ObjectNode cellTowerObject = (ObjectNode) cellTower;
            cellTowerObject.put("mobileCountryCode", homeMobileCountryCode);
            cellTowerObject.put("mobileNetworkCode", homeMobileNetworkCode);
            cellTowerObject.put("locationAreaCode", locationAreaCode);
            cellTowerObject.put("cellId", cellId);
        }
        return mapper.writeValueAsString(rootNode);
    }
}