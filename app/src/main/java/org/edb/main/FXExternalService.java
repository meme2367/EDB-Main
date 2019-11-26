package org.edb.main;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class FXExternalService {
    private final StringProperty url;
    private final StringProperty name;
    private final IntegerProperty idx;

    public FXExternalService(String  name, String url, int idx) {
        this.url = new SimpleStringProperty(url);
        this.name = new SimpleStringProperty(name);
        this.idx = new SimpleIntegerProperty(idx);
    }

    public String getUrl(){ return url.get(); }

    public String getName() {
        return name.get();
    }


    public void setName(String name) {
        this.name.set(name);
    }

    public void setUrl(String url) {
        this.url.set(url);
    }

    public StringProperty nameProperty(){
        return name;
    }

    public StringProperty urlProperty(){
        return url;
    }


    public int getIdx() {return idx.get(); }

    @Override
    public String toString() {
        return "FXExternalService{" +
                "url=" + url +
                ", name=" + name +
                ", idx=" + idx +
                '}';
    }
}
