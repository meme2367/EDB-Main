package org.edb.main.network;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.edb.main.PluginConfigConverter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class TempJsonConverter extends PluginConfigConverter {

    private JsonObject jsonObjectForPost;

    public JsonObject getJsonObjectForPost(){
        convert();
        return jsonObjectForPost;
    }

    @Override
    protected void convert() {
        jsonObjectForPost = new JsonObject();

        SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        if(startDate==null){
            startDate = new Date();
        }

        if(endDate==null){
            endDate = new Date();
        }

        jsonObjectForPost.addProperty("start_time",fm.format(startDate));
        jsonObjectForPost.addProperty("end_time",fm.format(endDate));
        jsonObjectForPost.addProperty("configuration",convertConfigs());
    }

    @Override
    public void convertStrConfigToMap(String configuration) {
        JsonObject jsonObject = (JsonObject)new JsonParser().parse(configuration);

        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            pluginConfigs.put(entry.getKey(),entry.getValue().toString());
        }
    }

    private String convertConfigs(){
        JsonObject configJsonObject = new JsonObject();

        for(Map.Entry<String,String> entry : pluginConfigs.entrySet()){
            configJsonObject.addProperty(entry.getKey(),entry.getValue());
        }

        return configJsonObject.toString();
    }
}
