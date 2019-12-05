package org.edb.main.UI;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.edb.main.model.TargetProgram;

public class FXTargetProgram {
    private StringProperty targetName;
    private StringProperty targetPath;


    public FXTargetProgram( String  targetName, String targetPath) {

        this.targetName = new SimpleStringProperty(targetName);
        this.targetPath = new SimpleStringProperty(targetPath);

    }

    public FXTargetProgram(TargetProgram targetProgram){
        this.targetName = new SimpleStringProperty(targetProgram.getTargetName());
        this.targetPath = new SimpleStringProperty(targetProgram.getTargetPath());
    }

    public StringProperty targetNameProperty(){
        return targetName;
    }

    public StringProperty targetPathProperty(){
        return targetPath;
    }

    public TargetProgram convertToTargetProgram(){
        return new TargetProgram(targetName.get(), targetPath.get());
    }

}
