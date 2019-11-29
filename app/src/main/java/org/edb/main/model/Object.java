package org.edb.main.model;

public class Object {
    String object_name;
    String object_value;

    public Object(String object_name,String object_value) {
        this.object_name = object_name;
        this.object_value = object_value;
    }

    public Object(){}

    public String getObject_name() {
        return object_name;
    }

    public String getObject_value() {
        return object_value;
    }
}