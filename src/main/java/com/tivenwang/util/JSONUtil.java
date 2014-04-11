/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tivenwang.util;

import com.tivenwang.config.GlobalConfig;

import java.io.IOException;
import java.text.SimpleDateFormat;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
/**
 *
 * @author
 */
public class JSONUtil {

    private static SimpleDateFormat dateFormat = new SimpleDateFormat(GlobalConfig.DEFAULT_DATE_FORMAT);
    private static ObjectMapper objectMapper;
    

    static {
        objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(dateFormat);
    }

    public static JsonNode getJsonObjectByString(String json)
            throws JsonMappingException, JsonParseException, IOException {
        JsonNode df = null;
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES,true);
        df = mapper.readValue(json, JsonNode.class);
        return df;
    }

    /**
     * POJO to JSON string with Jackson lib.
     *
     * @param value
     * @return
     * @throws IOException 
     * @throws JsonMappingException 
     * @throws JsonGenerationException 
     */
    public static String entityToJSONString(Object value) throws JsonGenerationException, JsonMappingException, IOException {
    	return objectMapper.writeValueAsString(value);
    }

    public static <T extends Object> T jsonStringToObject(String json, Class<T> valueType) {
        T object = null;
        try {
            object = objectMapper.readValue(json, valueType);
        } catch (Exception e) {
            e.printStackTrace();
            object = null;
        }
        return object;
    }

    public static void main(String args[]) throws IOException {
        String jsonString = "{\"result\":false,\"resultId\":1000,\"resultMSG\":\"系统错误\"}";
        JsonNode jsonNode = getJsonObjectByString(jsonString);
        System.out.println(jsonNode.get("resultMSG").getIntValue());
    }
}
