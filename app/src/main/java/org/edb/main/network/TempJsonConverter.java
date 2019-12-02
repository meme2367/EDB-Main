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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class TempJsonConverter extends PluginConfigConverter {

    private JsonObject jsonObjectForPost;

    public JsonObject getJsonObjectForPost(){
        makeFormatForPost();
        return jsonObjectForPost;
    }

    @Override
    protected void makeFormatForPost() {
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
        jsonObjectForPost.add("configuration", convertConfigsForPost());
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
                    extractSpecificConfigsFromJson(entry.getKey(),(JsonObject)entry.getValue());
            }
        }
    }

    private void extractTargetProgramsFromJson(JsonObject targetProgramJsons) {
        Iterator<Map.Entry<String, JsonElement>> iterator
                = targetProgramJsons.entrySet().iterator();
        Map.Entry<String, JsonElement> entry;

        while(iterator.hasNext()){
            entry = iterator.next();
            String targetPath = removeDoubleQuotesFromString(entry.getValue().toString());

            TargetProgram tempTargetProgram = new TargetProgram(entry.getKey(),targetPath);
            targetPrograms.put(entry.getKey(),tempTargetProgram);
        }
    }

    private void extractTargetWebsitesFromJson(JsonObject targetWebsiteJsons) {
        JsonArray targetWebsiteArray = targetWebsiteJsons.getAsJsonArray();

        for (int i = 0; i < targetWebsiteArray.size(); i++) {
            String targetURL = removeDoubleQuotesFromString(targetWebsiteArray.get(i).toString());
            TargetWebsite tempTargetWebsite = new TargetWebsite(targetURL);
            targetWebsites.put(targetURL,tempTargetWebsite);
        }

    }

    private String removeDoubleQuotesFromString(String str){
        if(str.startsWith("\"")){
            return str.substring(1,str.length()-1);
        }
        else return str;
    }

    private void extractSpecificConfigsFromJson(String key, JsonObject singleConfigJsons) {

        Map<String,String> attributes = new HashMap<String,String>();

        Iterator<Map.Entry<String, JsonElement>> iterator
                = singleConfigJsons.entrySet().iterator();
        Map.Entry<String, JsonElement> entry;

        while(iterator.hasNext()){
            entry = iterator.next();
            String singleAttribute = removeDoubleQuotesFromString(entry.getValue().toString());

            attributes.put(entry.getKey(),singleAttribute);
        }
        pluginConfigs.put(key,attributes);
    }


    @Override
    public void setTargetPrograms(Map<String, TargetProgram> targetPrograms) {
        this.targetPrograms=targetPrograms;
    }

    @Override
    public void setTargetWebsites(Map<String, TargetWebsite> targetWebsites) {
        this.targetWebsites=targetWebsites;
    }

    @Override
    public Map<String, TargetProgram> getTargetPrograms() {
        return targetPrograms;
    }

    @Override
    public Map<String, TargetWebsite> getTargetWebsites() {
        return targetWebsites;
    }

    private JsonObject convertConfigsForPost(){
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
        return configJsonObject;
    }
}
