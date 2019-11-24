package org.edb.main.UI;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.edb.main.UIEventHandler;

import java.io.IOException;

public class FXFactory {
    private UIEventHandler uiEventHandler;
    private FXManipulator fxManipulator;
    private static FXFactory fxFactory;

    public static synchronized FXFactory getInstance(){
        if(fxFactory==null){
            fxFactory=new FXFactory();
        }
        return fxFactory;
    }

    private FXFactory() { }

    public void init(UIEventHandler uiEventHandler, FXManipulator fxManipulator){
        this.uiEventHandler=uiEventHandler;
        this.fxManipulator=fxManipulator;
    }

    public Parent loadMainUI(String path)throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(BootApp.class.getResource(path));
        Parent parent =loader.load();

        loader.<MainUIController>getController().setUiEventHandler(uiEventHandler);
        fxManipulator.setMainUIController(loader.getController());

        return parent;
    }

    public Parent loadLoginUI(String path)throws Exception{

        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
        Parent parent = loader.load();

        loader.<LoginDialogController>getController().setUiEventHandler(uiEventHandler);
        fxManipulator.setLoginDialogController(loader.getController());

        return parent;

    }
}
