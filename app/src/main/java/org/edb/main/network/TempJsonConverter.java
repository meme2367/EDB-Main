package org.edb.main.network;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.edb.main.PluginConfigConverter;
import org.edb.main.model.TargetProgram;
import org.edb.main.model.TargetWebsite;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

public class TempJsonConverter extends PluginConfigConverter {

    private JsonObject jsonObjectForPost;

    public JsonObject getJsonObjectForPost(){
        convertForPost();
        return jsonObjectForPost;
    }

    @Override
    protected void convertForPost() {
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
    public void convertStrConfigs(String configuration) {
        JsonObject jsonObject = (JsonObject)new JsonParser().parse(configuration);

        Iterator<Map.Entry<String, JsonElement>> iterator
                = jsonObject.entrySet().iterator();
        Map.Entry<String, JsonElement> entry;

        while(iterator.hasNext()){
            entry=iterator.next();
            switch(entry.getKey()) {
                case "TargetProgram" :
                    extractTargetProgramsFromJson((JsonObject)entry.getValue());
                    break;
                case "TargetWebiste" :
                    extractTargetWebsitesFromJson((JsonObject)entry.getValue());
                    break;
                default:
                    extractSpecificConfigsFromJson();
            }
        }
    }

    private void extractTargetProgramsFromJson(JsonObject targetProgramJsons) {
        Iterator<Map.Entry<String, JsonElement>> iterator
                = targetProgramJsons.entrySet().iterator();
        Map.Entry<String, JsonElement> entry;

        while(iterator.hasNext()){
            entry = iterator.next();
            String targetPath = removeDobuleQuotesFromString(entry.getValue().toString());

            TargetProgram tempTargetProgram = new TargetProgram(entry.getKey(),targetPath);
            targetPrograms.put(entry.getKey(),tempTargetProgram);
        }
    }

    private void extractTargetWebsitesFromJson(JsonObject targetWebsiteJsons) {
        JsonArray targetWebsiteArray = targetWebsiteJsons.getAsJsonArray();

        for (int i = 0; i < targetWebsiteArray.size(); i++) {
            String targetURL = removeDobuleQuotesFromString(targetWebsiteArray.get(i).toString());
            TargetWebsite tempTargetWebsite = new TargetWebsite(targetURL);
            targetWebsites.put(targetURL,tempTargetWebsite);
        }

    }

    private String removeDobuleQuotesFromString(String str){
        return str.substring(1,str.length()-1);
    }

    private void extractSpecificConfigsFromJson() {

    }


//    TODO setTargetPrograms
    @Override
    public void setTargetPrograms(Map<String, TargetProgram> targetPrograms) {

    }

//    TODO setTargetWebsites
    @Override
    public void setTargetWebsites(Map<String, TargetWebsite> targetWebsites) {

    }

//    TODO getTargetPrograms
    @Override
    public Map<String, TargetProgram> getTargetPrograms() {
        return null;
    }

//    TODO getTargetWebsites
    @Override
    public Map<String, TargetWebsite> getTargetWebsites() {
        return null;
    }

    private String convertConfigs(){
        JsonObject configJsonObject = new JsonObject();

        for(Map.Entry<String,String> entry : pluginConfigs.entrySet()){
            configJsonObject.addProperty(entry.getKey(),entry.getValue());
        }

        return configJsonObject.toString();
    }
}
