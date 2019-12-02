package org.edb.main.network;

import com.google.gson.*;
import org.edb.main.model.InactivateCondition;
import org.edb.main.model.Time;
import org.edb.main.model.Object;

import java.awt.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonConverter {

    JsonParser jsonParser;

    JsonArray configArray;

    JsonObject jsonObj;

    ArrayList<Object> objectList = new ArrayList<>();
    ArrayList<InactivateCondition> inactivateConditionList = new ArrayList<>();
    public Time time = new Time();

    String makeStringResult = "";
    public JsonObject configJsonObject;


    JsonObject tmpJsonObject = new JsonObject();


    public JsonConverter() {
        jsonParser = new JsonParser();
        configJsonObject = new JsonObject();
    }

//{"object":{"object1":"Chrome","object2":"game.exe"},"inactivateCondition":{"condition1":"인강5개보기","condition2":"6시간동안인터넷금지"}}

    //{"TargetProgram":{"targetName1" : "targetPath1", "targetName2" : "targetPath2"}, "TargetWebsites":["targetURL1", "targetURL2"],
    //    "Config1" : {"configAttribute1" : "1", "configAttribute2" : "[arrayElement1, arrayElement2]"}}
    public void setConfiguration(String configuration) {

        jsonObj = (JsonObject) jsonParser.parse(configuration);

//        Key : TargetProgram, TargetWebsite, Config1
//        반복이 필요했나? 재귀만으로 충분히 해결 가능할듯?
        for (String key : jsonObj.keySet()) {
            convert(jsonObj, key);
        }

    }

//      전체 object를 통채로 전달, key = Config1
//    element 단위로 구분한다.
    private void convert(JsonObject jsonObject, String keyName) {
        Iterator<Map.Entry<String, JsonElement>> iterator
                = jsonObject.entrySet().iterator();
        Map.Entry<String, JsonElement> entry;

        int i = -1;

        while (iterator.hasNext()) {

            entry = iterator.next();
            JsonElement value = entry.getValue();
            String current = entry.getKey();
            ++i;      //time index 증가

//provides check for verifying if this element is a primitive or not.
            if (value.isJsonPrimitive()) {

                try {
                    //정규표현식 이용 {object1 : Chrome}, {object2:Game2} 구별
                    Pattern objectPattern = Pattern.compile("object*");
                    Matcher objectMatcher = objectPattern.matcher(current);


                    Pattern inactivatePattern = Pattern.compile("condition*");
                    Matcher inactivateMatcher = inactivatePattern.matcher(current);

                    if(inactivateMatcher.find()){

                        System.out.print("\nkeynametest3\n");
                        System.out.print(current.toString());

                        inactivateConditionList.add(new InactivateCondition(current.toString(), value.toString()));

                    }

                    if (objectMatcher.find()) {
                        System.out.print("\nkeynametest4\n");
                        System.out.print(current.toString());
                        objectList.add(new Object(current.toString(), value.toString()));


                    }



                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else if (value.isJsonObject()) {
                System.out.print("\nkeynametest2\n");
                System.out.print(current);//check

                convert(value.getAsJsonObject(), current);

            } else if (value.isJsonArray()) {

                JsonArray jsonArray = value.getAsJsonArray();
                JsonElement jsonElement;//check

                for (int z = 0; z < jsonArray.size(); z++) {
                    //break
                    System.out.print("\nlast test\n");
                    jsonElement = jsonArray.get(z);
                    convert(jsonElement.getAsJsonObject(), current);
                }


            } else {
//not  JsonArray
                System.out.print("\nkeynametest errror\n");
                System.out.print(current.toString());
            }


        }
    }


    public ArrayList<Object> getObjectList() {

        return objectList;
    }



    public void makeString(String key,JsonObject object) {


        tmpJsonObject.add(key,object);

//        configJsonObject.add("configuration",tmpJsonObject);
        //json으로만든결과를 string으로

        setConfigObject(tmpJsonObject);

    }

    private void setConfigObject(JsonObject tmpJsonObject) {
        configJsonObject.addProperty("configuration",tmpJsonObject.toString());

        getStringResult();
    }

    public String getStringResult() {

        makeStringResult = configJsonObject.toString();

        System.out.print("\ntest getStringResult time 2\n");
        System.out.print(configJsonObject.toString());

        return makeStringResult;
    }

    public Time getTime(){
        return time;
    }

    public void setTime(Time time){
        this.time = time;

    }

    public JsonObject getConfigJsonObject() {
        return configJsonObject;
    }

    public ArrayList<InactivateCondition> getInactivateConditionList() {
        return inactivateConditionList;
    }
}