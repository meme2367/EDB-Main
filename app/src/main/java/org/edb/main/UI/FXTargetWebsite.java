package org.edb.main.UI;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.edb.main.model.TargetWebsite;

public class FXTargetWebsite {
    private StringProperty targetURL;


    public FXTargetWebsite( String targetURL) {
        this.targetURL = new SimpleStringProperty(targetURL);

    }


    public StringProperty targetURLProperty(){
        return targetURL;
    }

    public TargetWebsite convertToTargetWebsite() {
        return new TargetWebsite(targetURL.get());
    }
}
