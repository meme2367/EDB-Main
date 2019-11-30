package org.edb.main.UI;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class FXTargetWebsite {
    private StringProperty targetURL;


    public FXTargetWebsite(String  targetName, String targetPath) {
        this.targetURL = new SimpleStringProperty(targetName);

    }

    public StringProperty targetURLProperty(){
        return targetURL;
    }

}
