package org.edb.main;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ExternalService {
    private final StringProperty url;
    private final StringProperty name;

    public ExternalService(String  name, String url) {
        this.url = new SimpleStringProperty(url);
        this.name = new SimpleStringProperty(name);
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





}
