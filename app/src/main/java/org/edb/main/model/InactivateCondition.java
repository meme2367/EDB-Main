package org.edb.main.model;

public class InactivateCondition {
    String InactivateCondition_name;
    String InactivateCondition_value;

    public InactivateCondition(String InactivateCondition_name,String InactivateCondition_value) {
        this.InactivateCondition_name = InactivateCondition_name;
        this.InactivateCondition_value = InactivateCondition_value;
    }

    public String getInactivateCondition_name() {
        return InactivateCondition_name;
    }

    public String getInactivateCondition_value() {
        return InactivateCondition_value;
    }
}
