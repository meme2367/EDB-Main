package org.edb.main;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.edb.main.network.TempJsonConverter;
import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class JsonConvertTest {
//    public static void main(String[] args) {
//
//        convertConfigsForPostTest();
//
//    }

    @Test
    public void convertConfigsForPostTest(){
        Map<String,Map<String,String>> pluginConfigs= new HashMap<String,Map<String,String>>();

        Map<String,String> config1 = new HashMap<String,String>();
        config1.put("attribute1","1");
        config1.put("attribute2","5");

        Map<String,String> config2 = new HashMap<String,String>();
        config2.put("attribute3","[element1,element2]");

        pluginConfigs.put("config1",config1);
        pluginConfigs.put("config2",config2);

        JsonObject configJsonObject = new JsonObject();

        for(Map.Entry<String,Map<String,String>> singleConfigMap : pluginConfigs.entrySet()){
            JsonObject attributesJsonObject = new JsonObject();
            for (Map.Entry<String,String> singleAttribute : singleConfigMap.getValue().entrySet()) {

                attributesJsonObject.addProperty(singleAttribute.getKey(),singleAttribute.getValue());
            }

            System.out.println(attributesJsonObject.toString());
            configJsonObject.add(singleConfigMap.getKey(),attributesJsonObject);
        }

        System.out.println(configJsonObject.toString());
    }
}
