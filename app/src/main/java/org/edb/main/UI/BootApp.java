package org.edb.main.UI;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.edb.main.ServerResponseHandler;
import org.edb.main.UIEventHandler;
import org.edb.main.UIManipulator;
import org.edb.main.network.RestAPIRequester;

import java.io.IOException;

import static javafx.application.Platform.exit;

public class BootApp extends Application {

    public static Stage primaryStage;

    public static HBox rootLayout;

    public BootApp() {

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("EDB-Main");

        initComponentRoots();
        initRootLayout();
    }

    public void initComponentRoots(){
        FXManipulator fxManipulator= new FXManipulator();
        ServerResponseHandler serverResponseHandler=new ServerResponseHandler(fxManipulator);
        RestAPIRequester restAPIRequester = new RestAPIRequester(serverResponseHandler);
        serverResponseHandler.setServerRequester(restAPIRequester);
        UIEventHandler uiEventHandler=new UIEventHandler(restAPIRequester);
        uiEventHandler.setUiManipulator(fxManipulator);
        FXFactory.getInstance().init(uiEventHandler,fxManipulator);
    }

    public void initRootLayout() {
        FXFactory fxFactory= FXFactory.getInstance();
        Parent parent = null;
        try {
            parent =fxFactory.loadMainUI("/fxml/MainUI.fxml");
            Scene scene = new Scene(parent);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            exit();
        }
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }




}