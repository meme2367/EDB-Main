package org.edb.main;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.edb.main.network.TempJsonConverter;

import java.util.Iterator;
import java.util.Map;

public class JsonConvertTest {
    public static void main(String[] args) {
        String configuration = "{\"TargetProgram\":{\"targetName1\" : \"targetPath1\", \"targetName2\" : \"targetPath2\"}, \"TargetWebsites\":[\"targetURL1\", \"targetURL2\"],\"Config1\" : {\"configAttribute1\" : \"1\", \"configAttribute2\" : [\"arrayElement1\", \"arrayElement2\"]}}";

//        String configuration = "{\"TargetProgram\":{}, \"TargetWebsites\":[\"targetURL1\", \"targetURL2\"],\"Config1\" : {\"configAttribute1\" : \"1\", \"configAttribute2\" : [\"arrayElement1\", \"arrayElement2\"]}}";

        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = (JsonObject)jsonParser.parse(configuration);
        for (String key :
                jsonObject.keySet()) {
            System.out.println(key);
        }

        Iterator<Map.Entry<String, JsonElement>> iterator
                = jsonObject.entrySet().iterator();
        Map.Entry<String, JsonElement> entry;


        while(iterator.hasNext()){
            entry=iterator.next();
            switch(entry.getKey()) {
                case "TargetProgram" :
//                    만약 비어있으면 어떻게 처리되나?
                    System.out.println(((JsonObject)entry.getValue()).toString());
                    break;
                case "TargetWebiste" :
//                    extractTargetWebsitesFromJson();
                    break;
                default:
//                    extractSpecificConfigsFromJson();
            }
        }

        TempJsonConverter jsonConverter = new TempJsonConverter();
        jsonConverter.convertStrConfigs(configuration);
    }
}
