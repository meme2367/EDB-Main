package org.edb.main.UI;

import javafx.beans.property.*;

public class FXExternalServiceDetail {
    private StringProperty if_achieve;
    private StringProperty name;
    private IntegerProperty idx;

    /*
    *  int if_achieve;
    int external_service_detail_idx;
    String name;*/

    public FXExternalServiceDetail(int idx, String  name, int if_achieve) {
        this.idx = new SimpleIntegerProperty(idx);
        this.name = new SimpleStringProperty(name);
        if(if_achieve == 1){
            this.if_achieve = new SimpleStringProperty("달성완료");
        }else{
            this.if_achieve = new SimpleStringProperty("달성해주세요");
        }

    }

    public StringProperty nameProperty(){
        return name;
    }

    public StringProperty ifachieveProperty(){
        return if_achieve;
    }

    public IntegerProperty idxProperty(){return idx;}

}
