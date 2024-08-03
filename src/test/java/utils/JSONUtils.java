package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
}