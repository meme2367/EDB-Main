package org.edb.main.network;

import com.google.gson.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonConverter {

    JsonParser jsonParser;

    JsonArray configArray;

    JsonObject jsonObj;

    // RestAPIRequester에서 getter를 이용하여 사용가능.

    ArrayList<Object> objectList = new ArrayList<>();
    ArrayList<Time> timeList = new ArrayList<Time>();
    Time time = new Time();


    public JsonConverter() {
        jsonParser = new JsonParser();
    }


    public void setConfiguration(String configuration) {

        //configuration에 key가 있기에 JsonParser 를 configuration으로 먼저 파싱한다.
        jsonObj = (JsonObject) jsonParser.parse(configuration);
        configArray = new JsonArray();
        configArray.add(jsonObj.getAsJsonObject("configuration"));
        jsonObj = (JsonObject) configArray.get(0);

        for (String key : jsonObj.keySet()) {
            convert(jsonObj, "config");
        }

    }





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
                    if (objectMatcher.find()) {
                        objectList.add(new Object(current.toString(),value.toString()));


                    } else if (keyName.equals("time")) {
                        time.setTime(value.toString());

                        //timeList에 중복시간 저장안되도록.
                        if (!timeList.contains(time)) {
                            timeList.add(i, time);
                        } else {
                            //중복
                            break;
                        }

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
                //추가해야함.
                System.out.print("\nkeynametest errror\n");
                System.out.print(current.toString());
            }


        }
    }


    public ArrayList<Object> getObject() {

        return objectList;
    }

    public ArrayList<Time> getTime() {

        return timeList;
    }

    class Time {
        String start_time;
        String end_time;
        boolean isStart = false;

        public void setStartTime(String start_time) {
            this.start_time = start_time;
        }

        public void setTime(String start_or_end_time) {
            if (!isStart) {//false
                isStart = true;
                setStartTime(start_or_end_time);
                return;
            } else {//true
                setEndTime(start_or_end_time);
                isStart = false;

                return;
            }

        }

        public void setEndTime(String end_time) {

            this.end_time = end_time;
        }

        public String getStartTime() {

            return start_time;
        }

        public String getEnd_time() {
            return end_time;
        }
    }

}

class Object {
    String object_name;
    String object_value;

    public Object(String object_name,String object_value) {
        this.object_name = object_name;
        this.object_value = object_value;
    }

    public String getObject_name() {
        return object_name;
    }

    public String getObject_value() {
        return object_value;
    }
}